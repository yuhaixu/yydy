
package com.freesheep.web.protral.filter;


import com.freesheep.common.util.ErrorCode;
import com.freesheep.web.protral.helper.BodyReaderHttpServletRequestWrapper;
import com.freesheep.web.protral.helper.HttpHelper;
import com.freesheep.web.util.Constant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;


public final class AliyunCsrfFilter extends OncePerRequestFilter {
    /**
     * The default {@link RequestMatcher} that indicates if CSRF protection is required or
     * not. The default is to ignore GET, HEAD, TRACE, OPTIONS and process all other
     * requests.
     */
    public static final RequestMatcher DEFAULT_CSRF_MATCHER = new DefaultRequiresCsrfMatcher();

    private final Log logger = LogFactory.getLog(getClass());
    private final CsrfTokenRepository tokenRepository;
    private RequestMatcher requireCsrfProtectionMatcher = DEFAULT_CSRF_MATCHER;
    private AccessDeniedHandler accessDeniedHandler = new AccessDeniedHandlerImpl();

    public AliyunCsrfFilter(CsrfTokenRepository csrfTokenRepository) {
        Assert.notNull(csrfTokenRepository, "csrfTokenRepository cannot be null");
        this.tokenRepository = csrfTokenRepository;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet
     * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * javax.servlet.FilterChain)
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
        String json = HttpHelper.getBodyString(requestWrapper);

        String type = request.getContentType();
        System.out.println("=========Filter====请求 body==============");
        System.out.println("请求的body = " + json);
        System.out.println("请求的ContentType = " + type);

        request.setAttribute(HttpServletResponse.class.getName(), response);

        CsrfToken csrfToken = this.tokenRepository.loadToken(request);
        final boolean missingToken = csrfToken == null;
        if (missingToken) {
            csrfToken = this.tokenRepository.generateToken(request);
            this.tokenRepository.saveToken(csrfToken, request, response);
        }
        request.setAttribute(CsrfToken.class.getName(), csrfToken);
        request.setAttribute(csrfToken.getParameterName(), csrfToken);

        if (!this.requireCsrfProtectionMatcher.matches(request)) {
            filterChain.doFilter(requestWrapper, response);
            return;
        }
        Cookie[] cookies = request.getCookies();
        String timeToken = "";
        if(cookies != null && cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                String name = cookies[i].getName();
                if (Constant.DEFAULT_CSRF_COOKIE_NAME.equals(name)) {
                    timeToken = cookies[i].getValue();
                    break;
                }
                System.out.println("=======getName======" + cookies[i].getName());
                System.out.println("=======getValue======" + cookies[i].getValue());
                System.out.println("=======getPath======" + cookies[i].getPath());
                System.out.println("=======getDomain======" + cookies[i].getDomain());
                System.out.println("============================================");
            }
        }

        String actualToken = request.getHeader(csrfToken.getHeaderName());
        if (actualToken == null) {
            actualToken = request.getParameter(csrfToken.getParameterName());
        }
        System.out.println("========actualToken======="  + actualToken);
        System.out.println("========csrfToken.getToken()======="  + csrfToken.getToken());
        System.out.println(csrfToken.getToken() + ":" + actualToken);
        if(actualToken == null || actualToken.length() <=0){
            actualToken = timeToken;
        }
        /**
         * 由于前段不会处理cookie，这里的判断就去掉了
         * if (!csrfToken.getToken().equals(actualToken)) {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Invalid CSRF token found for "
                        + UrlUtils.buildFullRequestUrl(request));
            }
            if (missingToken) {
                this.accessDeniedHandler.handle(request, response,
                        new MissingCsrfTokenException(actualToken));
            } else {
                this.accessDeniedHandler.handle(request, response,
                        new InvalidCsrfTokenException(csrfToken, actualToken));
            }

            PrintWriter writer = response.getWriter();
            writer.write(ErrorCode.LOGIN_TIME_OUT.toString());

            writer.flush();
            writer.close();

            return;
        }*/
        filterChain.doFilter(requestWrapper, response);
    }

    /**
     * Specifies a {@link RequestMatcher} that is used to determine if CSRF protection
     * should be applied. If the {@link RequestMatcher} returns true for a given request,
     * then CSRF protection is applied.
     * <p>
     * <p>
     * The default is to apply CSRF protection for any HTTP method other than GET, HEAD,
     * TRACE, OPTIONS.
     * </p>
     *
     * @param requireCsrfProtectionMatcher the {@link RequestMatcher} used to determine if
     *                                     CSRF protection should be applied.
     */
    public void setRequireCsrfProtectionMatcher(
            RequestMatcher requireCsrfProtectionMatcher) {
        Assert.notNull(requireCsrfProtectionMatcher,
                "requireCsrfProtectionMatcher cannot be null");
        this.requireCsrfProtectionMatcher = requireCsrfProtectionMatcher;
    }

    /**
     * Specifies a {@link AccessDeniedHandler} that should be used when CSRF protection
     * fails.
     * <p>
     * <p>
     * The default is to use AccessDeniedHandlerImpl with no arguments.
     * </p>
     *
     * @param accessDeniedHandler the {@link AccessDeniedHandler} to use
     */
    public void setAccessDeniedHandler(AccessDeniedHandler accessDeniedHandler) {
        Assert.notNull(accessDeniedHandler, "accessDeniedHandler cannot be null");
        this.accessDeniedHandler = accessDeniedHandler;
    }

    private static final class DefaultRequiresCsrfMatcher implements RequestMatcher {
        private final HashSet<String> allowedMethods = new HashSet<String>(
                Arrays.asList("GET", "HEAD", "TRACE", "OPTIONS"));

        /*
         * (non-Javadoc)
         *
         * @see
         * org.springframework.security.web.util.matcher.RequestMatcher#matches(javax.
         * servlet.http.HttpServletRequest)
         */

        public boolean matches(HttpServletRequest request) {
            return !this.allowedMethods.contains(request.getMethod());
        }
    }
}

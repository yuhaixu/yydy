package com.freesheep.web.config;

public interface AliPayTestConfig {
    // 商户appid
    String APPID = "2016080700190535";
    // 私钥 pkcs8格式的
    String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCqoXhFZsKpUkXmphQR7smvZPwOw" +
            "+XpYa5Jy4NOX11cp5mqAMdotzTCrcMCvYnxpUuXd3EOGXMhcvG3HXqqcBRpa1guBh8LNVjnv16edPsCSFWoSZqbaaL4mZSh+8bILkUVLql8q6IYzkDm0slKecJsOvdYNgJckukoH7kSO+dl6kMw5CejdmwjkxKore57Bv6nffAk0H79DNrOsI0XePThMhtKuFY8ODC49NEHoDLcBUk1Zq5sj04k4zjJJQzYjVteSB8SKZ91rT0a0nFeuOlXlnsn8GGUHlvKMEa4VIJQnhJJl8pLmrq4NsesWAfYrIF4ZIZnc18La/KQGw3cd/f9AgMBAAECggEAB7OOCCPhOV87RHPOiBitBbubHNd+sw/zm+URoiHvxtTRvLiYR4OtX/QTzzXa2gtJ5CZB9g70AVh8zHUKKlMwuKldq4YfNJRN1PfwP1qsUagOJbBcyBL2hh34dW81w6dC+vfGMphUkqYMi96Ls07Xlab7rwGRDbibyXHVGpuVkCX1m1Aa2/P2tWj05zyq6EM3i55VYB39wd4NI8tESXd6nNmphS0ikx9rSaNnjpOARVkSwjoLHd99tcAHWDRqA6yCuLzVREdFdnQk5Y/LJnomxSiNbY1ZMBFMV91NMYFvyE1i9yR7jFPm1503FME2cbyNg+UUVO9vKrTJntAHUaXcSQKBgQDyaeJ1yQZBuJ2wEm3Hk1aVA+//vUenStfbZ5B4c9EaYOjqzE1j0evsUQh5AGDQnWSDKH7fbjPC2r5MYwfNZzjostn/GXOTqDu1vIoHw0i1QdKR3JzUu4VGjZjgBirFzsffjHieDSi1xBcXpBsphACvqSNss71MSDys2+Ej3+60MwKBgQC0MaesTzPfhimhkywPoWdipJp9FmkBP/v2wwA5nfbGkTmUS0ytgMzWZv90Eg+JorsS9oHqWVmtG5sMksrWiubZB+YTabE41GauKngfrKJ29Rwi7JQ4xmGom9JVUS2bH5fy/vGXJLPdh/AHGVkmxfVykkZyUqDGPpoKXmds3tXzDwKBgCtekDp8bZcifWu70FItZQch/jUl2wTuGfJR9AHozpdGkUU4qfva2becw8RKMggAsLPaceBtaetJkVQ/P20bM45GOUQvfIMFTTF1eshqwr5S+WZJLvHMXsrBmIEz+UEMfqvbCNVWC1uBqpP7MzTZPfova3wOKCbWoaxXZk8xyBYLAoGBAIb+tEkoxQnubv33TgkfuF50RrDzuxMOpmtFx8NijmBQqDxq9TT+y3bMjxCb/zvzyAb62nWBHufLSKtwxblZMnzhqAJfKkATECHm+ztvj/9ivgOEDb9DECLLKhZ0zJ/sWV/Yk5HSE1Yh4zbOTLX5lFqTL2eyc3RRrgGanA06HDsNAoGBAOvTS9k3ub2wOcHSn+/wSt+BS36V7ZB+Fyuz0l96XxYNGAhHVAMjamV3PZ+Tyw1eTqxJV0JL/bPUfI2ucDTEqIDzLVwiIYsl7FzLIspVui7XU+8yELuZNfgJnDZXKYQIWNFZhJkOn+mtgAnhoRpnmDDukGo8QMlybKQZRByGQT7U";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    String NOTIFY_URL = "http://yuhaixu.tpddns.cn:12345/alipay/alipay_notify";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    String RETURN_URL = "http://yuhaixu.tpddns.cn:12345/alipay/return_url";
    // 请求网关地址
    String GATEWAY = "https://openapi.alipaydev.com/gateway.do";
    // 编码
    String CHARSET = "UTF-8";
    // 返回格式
    String FORMAT = "json";
    // 支付宝公钥
    String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1JHJLd7RCkXOLrRsCKN+zhRLb/+nqS5DeV04M8H6AsNUZ1A6LXksGrytWrnZUjSr3xqYG0+ziZ+bmOip4PLQOot7341K/0uv1HNkLfTjPgCI6pO3b5dX/5diGBBK+4GPg1pA6hLeZNmNtV0jOnhWlxrsBWe6NOF0e1gjgqLwhf2YAb18VXw9LU8kfCDvcpYLSDq0MNp2JXXH3PL6Aja0GP3/LVXre/xh8sup3tJIsYBINYQr8ZxhtYq/0f2l47SIeOL8KbTY8GR9Zk2L2vKu8n68rXvcbMbVRKG1oLx9wcH2YcYa4M5GNRDXHHt1vnNCFfaH0j39s5KwBMTwa6DwIQIDAQAB";
    // RSA2
    String SIGNTYPE = "RSA2";
    String REDIRECT_URL = "http://www.xueli001.org/sheeps/paybank.html";
}

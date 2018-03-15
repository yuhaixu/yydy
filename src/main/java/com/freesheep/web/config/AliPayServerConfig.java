package com.freesheep.web.config;

public interface AliPayServerConfig {
    // 商户appid
    String APPID = "2017112100073971";
    // 私钥 pkcs8格式的
    String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDzWy64Ph8v/+667nI+204YL6rTjDOANcnICQl3YgvxjbRfLI23XxsFEhGdYY5SV7ehl" +
            "/mZyQzxm0F8yu1ojaQW35h5OPr8FMOkCMafyRjlqk8hDP7c3A3JpkEZZ2AhMPfrPVrmDVxBPfuvpPwaUKUpILbMqPaLhhL0D8bE88CuCN5B086ciBVgU0SEDja/lam2Om2Mv6EF/WFIpNtv5YKuDs8P+EYixkCarZVk7EpullBVYgW1Av0D2dejg0SAuRjH6vy3VqB7FGMK9TPJB1yq+QgGGcQgkhc56VlP8C/oug6bicQH48bB5uqbIqqoZ0xDkylSgAkQoC58w8PdM7BZAgMBAAECggEALf/6YNE9S/Fwytzvs3n41RGNlNdXlNCWB73NkEd0QoCqq6LcVuEfVmIH+lvKbz4tc9oqUCt0yuayBkjFf5HmhnxVcfSXXLcAFC2kfeehUShUNMckFdNyt785Zvfnb961vj2mxP2ZhquUu6rQDJlZ3uBULXNyxwTc6E1jIS+CKGMeYmWyYZCKcsAnvcWqhkDZydYabFACGMkFu1c2QdzWW4VPuUsVmtdgRpxlCBXLRHrGgw4sLb0yfO5tw0yLZye3iYmu50i2/jFisg2xu1v7AG9uFuJud6Eo4Mgea0HoCoastzCv56S0vp/5p0Q5Y03Pqh07dljFAuL9KgyzXH3uAQKBgQD8IplH7BHKCeuTy8iwl+BflzsvS9frjrNpNW9fFO5GKw9p5hiC9IVyaaJP06Se5s4/nFjQwPAiTVYuWqkocLz8bgO1yd+GfBMorjIEeSuyQZ/m8Hgi/X1B6B2rumRfCJ9SYzcytXpXc8fNZNsstUctG+rB8iPP2cdO5jSPq4JjMQKBgQD3FiJfhf8uFx2oVM0/JrqpX46nbjxNteGZa803uYxaA5/a5rIS6IzJ71ChpDvHwNuYFUVmTbtlCvl6yyj+pDapWCRv0xq4+Z/Aso5efUk5kjcFWGkPxuwnXIVZsk4qY1A7epZeOxbEIPlVGzXfKxpwfpkpY76aWPxqmhNENKRFqQKBgQCgssSTLIjiBpSZLGcK9qHRCyhzqplBzz/WLHKBXWvIWSWHz1mMDaEIHP+QdUoU5PJLZLDOzEnA8x2cibgKyGALW2sN8E6A/o+PfAbxBvGsuVra7CUF2B5/ZRuUGkZis7In5oiFz1jcaDTOWhu/UQK+Y26qklP0486+IMcbUqrV4QKBgDfsUZFPMadL4hGESAQADwVgtFfe0lVAUjQ4OvVe0I5a/aY8ypcO/TaDLLu5ATK/yrPMNK3n7/75keeh7cBciGahpXWN0SGGlgq2HxTxxE38cumJzhWdDzwFa8UWXyym17TulPP06b0wPV5uzy2Ygt5tONsuUSh79X1nK+8m/vspAoGBAMeap5AgrrU9uEbmn7nH2vBvihisjOD9J0C1Ui4GC+t8ICGZX5jxOYMYq9L7DlLrvezgudql1X344hKR9Q/1yUbXY/luMdQ6I7jki+XfCaPHR79Ti+pJGSGy/4NbQBEkg70zxhHCtP5sKfVBB91yyr/wMRQdt86XaAw3P928P1Fa";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    String NOTIFY_URL = "http://60.205.220.219:8080/alipay/alipay_notify";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    String RETURN_URL = "http://60.205.220.219:8080/alipay/return_url";
    // 请求网关地址
    String GATEWAY = "https://openapi.alipay.com/gateway.do";
    // 编码
    String CHARSET = "UTF-8";
    // 返回格式
    String FORMAT = "json";
    // 支付宝公钥
    String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArTM4HgA/+G7cgS3OB7LhRj+sGXLhS1velhYSNyi9d8dJXlyJ+imY33CM4wOBA5l3pZZBYDl+LTs6q+rgUkkMaK8LNIfml7lJkOAfTQ+IbyoN6bjSqC4BnYIkbm2huYttYXJrZQwVFTx2ETfN/NyLvkcXAa6njMtr2FHoKswgg/7YmLymFACFxr4Ino51CNxBBB589qHdKA/h5KhtVg70hyGUdGFve3wbpXO/lQqb0XCz0IS965bynd3u2DoMmSNizpdXR4lIIVURGj8ipUDq8F3wgG3WJRYRs9L3NGf86tAokdjKf0xUIMqI+g2OMTcTMps59obFY4P7zdLaX/y1iwIDAQAB";
    // RSA2
    String SIGNTYPE = "RSA2";
    // 重定向地址
    String REDIRECT_URL = "http://www.xueli001.org/sheeps/paybank.html";
}

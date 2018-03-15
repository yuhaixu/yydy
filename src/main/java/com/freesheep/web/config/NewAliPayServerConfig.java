package com.freesheep.web.config;

public interface NewAliPayServerConfig {
    // 商户appid
    String APPID = "2018012502069855";
    // 私钥 pkcs8格式的
    String APP_PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDzPyzehTRXCbK7I1dw7ypFJgAoddqnL1BUbcquMyf9fqhsl7WPSGGY1xxbDqyJGfaXGyC2U42iMnxVPG26+x+ttixhcJqBL88xcrEeAVrrjhNoQLalRdWolwqNF5d8wCXvTay5e/k9IqB4q9SPR3Opk2pKGAP6ogZwvCgfD8PCvj4Y8FSsknCjwAXZsJ1tyRmVYxHM+9RvypmmNX4XewZ7/X2NHuCxCZ8dxeeUHrU85f20PYqJhHp0BFOuDs218JC88rch7c0Zt4kG7bksm+P0XgfIOmtgpiCH5VF5MGd2Ir4Ua+jJVPSXFZEcefcWD8ea8/bZVT/E0MBwj4fVfTTZAgMBAAECggEANgamFJZJVbuVsvLajP7RyKH0Dwh+E/vyP45TIfXCwA7NobDgZn6RDr1YUvEjaSAXIOCpl1Vfc9L6woO1a34zPwXjPZEg/+h6gGUU6bErXH4l2VXmOzcTiWpisYu33cFfq5L49gmPBgEW2zZu85PPIK7ZQLOszpIGFCv2TU/XDv17OVeucUe6dFxZ0gBmxuCb7xE1L8Q6z6yB+xnqOFzZqdpAfz2oOzaPzCK3wvDzsQWgGCC9/AHwCLlI29jsbBPwSbrq5odhBg17LU81/F6oo3VLCfiWCj7Jf/L4ql1VcucSXPNg8KC6Mh28BWiewvg1u4qu3UODUC8yCBWKB5XkAQKBgQD8zv9KZStUCX4eOclyRf51Lyf1fdbRmisEvbOQMYibfV9pwZMfDLgmXQujdqQV5TdD9a6xIex2JEUaoutqPO/TINJB5XJ3YTONLdg5nEz6o5YJVnSviYmsWJOALypmbqu6/5MVbFz+/wzIxFdbiFKMdEmNV5ufZN+hSKkQ7PWZIQKBgQD2UUbwp6wg9m+F3lV2TQRZabpwE+LEb8Uecoi62usi6OGSL51aWq8fpME7gN6cxnWDH2ny/56O/jB09gCPolycKizmDPTD3LOEoLFDbDU3XZ8Bw4ZHXOaAKpkR/Jq5nurIwwAeEtydQYGaxfBOG0ypCmDI5qJUr0BdtXMyFlQMuQKBgQC2BQvGMsVZgmQtuwmRn1ewY71vgaUz0L58cWFhxmhHG6Og72DQEEZeSMvia7+EjD6YTcHzgytPfJnMrdS17x31c0Ivc9ZQocmOMp3mDOVX6IRUQM1OvcZEMpowxsTOb4xx3SW3XQgwCM7ZlpQXMTXOLsPuYk+IGgbxhvFDYHkMQQKBgQCTD4OjOvhx1bBP5KZxsxndqFo/gB/r2C+ANjGcHgg1F4VVmkgAIhRceClGNXKzd2nBl4fSJPuts+WKbzV2jCFuu1+DGkkohyRHEwnCj2BWZfcBPpN1F+omq0gojQBzwoW6x3HSgsBkwy92EKegenwSkLeoOsrAdMGhMSxNwzb7QQKBgQC6HJRgwxxM7VQsFtrgwBEVH1IAJT8P4RmIJLXRF60/PkreAj2O8/AN9mb1JCVKbdHD1RHY3A+0/L16Lfr4EY+4Dh36DijBbjHnExruB1pqNu7zL0+HY+zdOcIdzkbI00ft/Ky2WKVjmhkYnve2ned+plM7x9kPc0QcM9ewHkq/Jg==";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    String NOTIFY_URL = "http://60.205.220.219:8080/new_alipay/alipay_notify";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    String RETURN_URL = "http://60.205.220.219:8080/new_alipay/return_url";
    // 请求网关地址
    String GATEWAY = "https://openapi.alipay.com/gateway.do";
    // 编码
    String CHARSET = "UTF-8";
    // 返回格式
    String FORMAT = "json";
    // 支付宝公钥
    String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA8z8s3oU0VwmyuyNXcO8qRSYAKHXapy9QVG3KrjMn/X6obJe1j0hhmNccWw6siRn2lxsgtlONojJ8VTxtuvsfrbYsYXCagS/PMXKxHgFa644TaEC2pUXVqJcKjReXfMAl702suXv5PSKgeKvUj0dzqZNqShgD+qIGcLwoHw/Dwr4+GPBUrJJwo8AF2bCdbckZlWMRzPvUb8qZpjV+F3sGe/19jR7gsQmfHcXnlB61POX9tD2KiYR6dARTrg7NtfCQvPK3Ie3NGbeJBu25LJvj9F4HyDprYKYgh+VReTBndiK+FGvoyVT0lxWRHHn3Fg/HmvP22VU/xNDAcI+H1X002QIDAQAB";
    // RSA2
    String SIGNTYPE = "RSA2";
    // 重定向地址
    String REDIRECT_URL = "http://www.xueli001.org/sheeps/paybank.html";
}

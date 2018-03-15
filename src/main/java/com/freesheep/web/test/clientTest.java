package com.freesheep.web.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class clientTest {

    public static void main(String args[]) throws Exception {
        // 要连接的服务端IP地址和端口
        String host = "60.205.220.219";
        int port = 7777;
        // 与服务端建立连接
        Socket socket = new Socket(host, port);
        Socket socket1 = new Socket(host, port);
        // 建立连接后获得输出流
        OutputStream outputStream = socket.getOutputStream();
        OutputStream outputStream1 = socket.getOutputStream();
        String message="[SG*2716340281*0002*LK,testinfo]";
        socket.getOutputStream().write(message.getBytes("UTF-8"));
        socket1.getOutputStream().write(message.getBytes("UTF-8"));
        socket.shutdownOutput();
        socket1.shutdownOutput();

        BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String str =  buf.readLine();
        BufferedReader buf1 = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
        String str1=  buf1.readLine();
        System.out.println("server info = " + str);
        System.out.println("server info = " + str1);

        buf.close();
        buf1.close();
        outputStream.close();
        outputStream1.close();
        socket.close();
        socket1.close();
    }

}

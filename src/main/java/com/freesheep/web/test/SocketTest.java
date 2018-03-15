package com.freesheep.web.test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SocketTest {

    static Socket client = null;
    static boolean f = true;

    public static void main(String[] args) throws Exception {
        //服务端在20006端口监听客户端请求的TCP连接
        ServerSocket server = new ServerSocket(7777);

        while (f) {
            //等待客户端的连接，如果没有获取连接
            client = server.accept();
            System.out.println("与客户端连接成功！");
            //为每个客户端连接开启一个线程
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //获取Socket的输出流，用来向客户端发送数据
                        PrintStream out = new PrintStream(client.getOutputStream());
                        //获取Socket的输入流，用来接收从客户端发送过来的数据
                        BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
                        boolean flag = true;
                        while (flag) {
                            //接收从客户端发送过来的数据
                            String str = buf.readLine();
                            if (str == null || "".equals(str)) {
                                flag = false;
                            } else {
                                if ("bye".equals(str)) {
                                    flag = false;
                                    f = false;
                                } else {
                                    //将接收到的字符串前面加上echo，发送到对应的客户端
                                    System.out.println("device data:" + str.toString());
                                    boolean readFlag = true;
                                    int start = str.indexOf("[");
                                    int end = str.indexOf("]");
                                    while (readFlag) {
                                        String dataStr = str.substring(start + 1, end);
                                        System.out.println(dataStr);
                                        String[] datArr = dataStr.split("\\*");
                                        if (datArr.length >= 4) {
                                            String dataFlag = datArr[3];
                                            if (dataFlag != null && dataFlag.length() > 0 && dataFlag.startsWith("LK")) {
                                                System.out.println("================link stay===============");
                                                System.out.println(getTime());
                                                StringBuilder returnStr = new StringBuilder();
                                                returnStr.append("[");
                                                returnStr.append("SG");
                                                returnStr.append("*");
                                                returnStr.append(datArr[1]);
                                                returnStr.append("*");
                                                returnStr.append("0002");
                                                returnStr.append("*");
                                                returnStr.append("LK");
                                                returnStr.append("]");
                                                System.out.println("return data = " + returnStr);
                                                out.write(returnStr.toString().getBytes());
                                                out.flush();
                                            } else if(dataFlag != null && dataFlag.length() > 0 || dataFlag.startsWith("UD2")){
                                                int s = dataStr.indexOf(",");
                                                String gpsStr = dataStr.substring(s + 1);
                                                String[] gpsArr = gpsStr.split(",");
                                                String date = gpsArr[0];
                                                String time = gpsArr[1];
                                                String isLocation = gpsArr[2];
                                                String latitude = gpsArr[3];
                                                String longitude = gpsArr[5];
                                                String deviceCode = datArr[1];
                                                String json = "{\"device_num\":\""+ deviceCode +"\",\"device_date\":\""+ date +"\"," +
                                                        "\"device_time\":\""+ time +"\",\"longitude\":\""+ longitude +"\"," +
                                                        "\"latitude\":\""+ latitude +"\",\"is_location\":\""+ isLocation +"\"}";

                                                File file = new File("/home/data.txt");
                                                if(!file.exists()) file.createNewFile();

                                                BufferedWriter bw = null;
                                                try {
                                                    bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
                                                    bw.write(getTime());
                                                    bw.write("\r\n");
                                                    bw.write(json);
                                                    bw.write("\r\n");
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                } finally {
                                                    try {
                                                        if(out != null){
                                                            bw.close();
                                                        }
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                }


                                            }
                                        }
                                        str = str.substring(end + 1);
                                        start = str.indexOf("[");
                                        end = str.indexOf("]");
                                        if (start < 0) readFlag = false;
                                    }

                                }
                            }
                        }
                        out.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        System.out.println("close server");
        server.close();
    }

    public static String getTime(){
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dataTime = format.format(d);
        return dataTime;
    }


}

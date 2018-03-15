package com.freesheep.web.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class SheepTest {

    public static void main(String[] args){
        test();
    }

    public static void test(){
        int startId = 100;
        int endId = 200;
        StringBuilder sb = new StringBuilder();
        sb.append("insert into fictitious_device_info(device_id,device_longitude,device_latitude,step,data_time,create_time, modify_time)\n");
        sb.append("values\n");
        String[] loArr = {"11649","11650","11651","11652","11653"};
        String[] laArr = {"4914","4915","4916","4917","4918"};
        for(int i = startId; i <= endId; i++){
            int loP = (int)(1+Math.random()*5) - 1;
            String time = "2018-03-14 18:10:00";
            for (int j = 0; j < 6; j++){
                int position = (int)(1+Math.random()*5) - 1;
                // 11652.28248  4916.69398
                int p = (int)((Math.random()*9+1)*10000);
                sb.append("(");
                sb.append(i);
                sb.append(",\'");
                sb.append(loArr[loP] + "." + String.valueOf(p));
                sb.append("\',\'");
                sb.append(laArr[position] + "." + String.valueOf(p));
                sb.append("\',");
                sb.append(getRandom(400,600));
                sb.append(",\'");
                sb.append(time);
                sb.append("\',\'");
                sb.append(time);
                sb.append("\',\'");
                sb.append(time);
                sb.append("\'),\n");
                time = getOneHoursAgoTime(time, -2);
            }
        }
        System.out.println(sb.toString());
    }

    public static int getRandom(int min, int max){
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }


    public static String getOneHoursAgoTime (String time, int  houre) {
        String oneHoursAgoTime =  "" ;
        SimpleDateFormat sj = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sj.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance ();
        cal.setTime(date);
        cal.add(Calendar.HOUR, houre ) ; //把时间设置为当前时间-1小时，同理，也可以设置其他时间
        oneHoursAgoTime =  sj.format(cal.getTime());//获取到完整的时间
        return  oneHoursAgoTime;
    }

}

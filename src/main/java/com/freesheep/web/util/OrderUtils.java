package com.freesheep.web.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderUtils {

    public static String previousDateTime;
    public static int previousNineNum = 0;


    public static String getOrder(){
        StringBuilder order = new StringBuilder();
        Date dateS = new Date();
        String date = new SimpleDateFormat("yyyyMMdd").format(dateS);
        String time = new SimpleDateFormat("HHmmssSSS").format(dateS);
        order.append(date);
        order.append(getThreeInt());
        order.append(time);
        int nineNum = getNine();
        if((date + time).equals(previousDateTime) && previousNineNum > 0){
            nineNum = previousNineNum + 1;
        }
        order.append(nineNum);
        previousDateTime = date + time;
        previousNineNum = nineNum;
        return order.toString();
    }

    private static int getNine(){
        int max=900000000;
        int min=100000000;
        Random random = new Random();
        return random.nextInt(max)%(max-min+1) + min;
    }

    private static int getThreeInt(){
        int max=999;
        int min=100;
        Random random = new Random();
        int second = random.nextInt(max)%(max-min+1) + min;
        return second;
    }

}

package com.freesheep.web.util;

import java.util.Random;

public class CouponUtils {

    private static String[] bigArr = {"A","B","C","D","E","F","J","H","I","G","K","L","M","N","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private static String[] smallArr = {"a","b","c","d","e","f","j","h","i","g","m","n","p","q","r","s","t","u","v","w","x","y","z"};
    private static String[] digitArr = {"1","2","3","4","5","6","7","8","9"};

    public static String getCoupon(){
        StringBuilder coupon = new StringBuilder();
        for (int i = 0; i < 20; i++){
            int arrPosition = getRandomInt(3);
            String data = "";
            if(arrPosition == 0){
                int dataPosition = getRandomInt(bigArr.length);
                data = bigArr[dataPosition];
            } else if(arrPosition == 1){
                int dataPosition = getRandomInt(smallArr.length);
                data = smallArr[dataPosition];
            } else {
                int dataPosition = getRandomInt(digitArr.length);
                data = digitArr[dataPosition];
            }
            coupon.append(data);
        }
        return coupon.toString();
    }

    private static int getRandomInt(int max){
        int min=0;
        Random random = new Random();
        int second = random.nextInt(max)%(max-min+1) + min;
        return second;
    }

}

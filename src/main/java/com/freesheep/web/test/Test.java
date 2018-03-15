package com.freesheep.web.test;


import com.freesheep.biz.jiuBean.*;
import com.freesheep.common.util.DigestUtils;
import com.freesheep.web.manager.SchedulerManager;
import com.freesheep.web.util.DateUtils;
import com.freesheep.web.util.JiuUtils;
import com.thoughtworks.xstream.XStream;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    public static void main(String[] args) {

//        String s = "1poc1213213213213";
//        System.out.println(Arrays.toString(s.split("poc")));

//        parse();

        test126();

    }

    public static void test126() {
        boolean b = true;
        for (int i = 0; i < 10; i++) {
            if (i == 2 && b) {
               b = false;
               i--;
               System.out.println("continue");
               continue;
            }
            System.out.println(i);
        }
    }

    public static void test125() {
        String str = "[3G*2716340281*000A*LK,9,0,100]" +
                "[3G*2716340281*0120*UD2,050318,112644,V,22.651375,N,114.0082617,E,0.00,0.0,0.0,0,78,100,9,0,00000000," +
                "7,1,460,0,9724,4213,138,9724,4642,129,9724,4522,125,9724,4042,124,9724,4032,123,9724,4211,123,9724,4503," +
                "123,3,301,c0:61:18:0e:5f:3c,-90,H3C_501,ac:74:09:e0:1f:6c,-91,360WiFi-E43F49,a4:56:02:e4:3f:49,-91,61.6]";
        boolean readFlag = true;
        int start = str.indexOf("[");
        int end = str.indexOf("]");
        while (readFlag) {
            String dataStr = str.substring(start + 1, end);
            System.out.println(dataStr);
            String[] datArr = dataStr.split("\\*");
            if (datArr.length >= 4) {
                String dataFlag = datArr[3];
                if (dataFlag != null && dataFlag.length() > 0 || dataFlag.startsWith("LK")) {
                    System.out.println("================link stay===============");
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
                }
            }
            str = str.substring(end + 1);
            start = str.indexOf("[");
            end = str.indexOf("]");
            if (start < 0) readFlag = false;
        }
    }

    public static void test124() {
        String content = "1234\r\n" +
                "ddddd";
        System.out.println(content);
        content = content.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
        System.out.println(content);
    }

    public static void test123() {
        String body = "<?xml version=\"1.0\" encoding=\"utf-8\"?><request" +
                "><deliveryOrder><totalOrderLines>1</totalOrderLines><deliveryOrderCode>20180207124112831782892008976</deliveryOrd" +
                "erCode><deliveryOrderId>U310001494431</deliveryOrderId><warehouseCode>B01</warehouseCode><orderType>PTCK</orderTy" +
                "pe><status>DELIVERED</status><outBizCode>U310001494431</outBizCode><confirmType>0</confirmType><logisticsCode>JIU" +
                "YE</logisticsCode><logisticsName>JY</logisticsName><expressCode>JY0005330465</expressCode><orderConfirmTime>2018-" +
                "02-07 15:33:23</orderConfirmTime></deliveryOrder><packages><package><logisticsName>JY</logisticsName><expressCode" +
                ">JY0005330465</expressCode><packageCode></packageCode><length>0.0</length><width>0.0</width><height>0.0</height><" +
                "weight>0.0</weight><volume>0.0</volume><packageMaterialList/><items><item><itemCode>416</itemCode><itemId>1700040" +
                "948</itemId><quantity>10</quantity></item></items></package></packages><orderLines><orderLine><outBizCode>U310001" +
                "49443111</outBizCode><orderLineNo>0</orderLineNo><itemCode>416</itemCode><itemId>1700040948</itemId><itemName></i" +
                "temName><inventoryType>ZP</inventoryType><actualQty>10</actualQty><batchCode></batchCode><productDate></productDa" +
                "te><expireDate></expireDate><produceCode></produceCode><batchs/></orderLine></orderLines></request>";
        XStream xStream = JiuUtils.createXstream();
        xStream.alias("request", ConfirmOrderBean.class);
        xStream.alias("deliveryOrder", DeliveryOrderBean.class);
        xStream.alias("orderLine", OrderLineItemBean.class);
        xStream.alias("package", PackageBean.class);
        xStream.alias("item", ItemBean.class);
        xStream.alias("batch", BatchBean.class);
        xStream.processAnnotations(ConfirmOrderBean.class);
        ConfirmOrderBean confirmOrderBean = (ConfirmOrderBean) xStream.fromXML(body);
        System.out.println(confirmOrderBean.toString());
    }


    public static String monthFirst(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return format.format(c.getTime());
    }

    public static String monthLast(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        c.add(Calendar.MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH, 0);
        return format.format(c.getTime());
    }

    private static void parse() {
        String xml =
                "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                        "<request>\n" +
                        "    <deliveryOrder>\n" +
                        "        <deliveryOrderCode>CK2017120503</deliveryOrderCode>\n" +
                        "        <deliveryOrderId>U310001473319</deliveryOrderId>\n" +
                        "        <warehouseCode>B01</warehouseCode>\n" +
                        "        <orderType>JYCK</orderType>\n" +
                        "        <status>DELIVERED</status>\n" +
                        "        <outBizCode>U310001473319</outBizCode>\n" +
                        "        <confirmType>0</confirmType>\n" +
                        "        <orderConfirmTime>2018-01-31 11:42:23</orderConfirmTime>\n" +
                        "        <operatorCode></operatorCode>\n" +
                        "        <operatorName></operatorName>\n" +
                        "        <operateTime>2018-01-31 11:42:23</operateTime>\n" +
                        "    </deliveryOrder>\n" +
                        "    <packages>\n" +
                        "        <package>\n" +
                        "            <logisticsCode>JIUYE</logisticsCode>\n" +
                        "            <logisticsName>JY</logisticsName>\n" +
                        "            <expressCode>JY0005330347</expressCode>\n" +
                        "            <packageCode></packageCode>\n" +
                        "            <length></length>\n" +
                        "            <width></width>\n" +
                        "            <height></height>\n" +
                        "            <theoreticalWeight></theoreticalWeight>\n" +
                        "            <weight></weight>\n" +
                        "            <volume></volume>\n" +
                        "            <invoiceNo></invoiceNo>\n" +
                        "            <packageMaterialList/>\n" +
                        "            <items>\n" +
                        "                <item>\n" +
                        "                    <itemCode>416</itemCode>\n" +
                        "                    <itemId>1700040948</itemId>\n" +
                        "                    <quantity>2</quantity>\n" +
                        "                </item>\n" +
                        "            </items>\n" +
                        "        </package>\n" +
                        "    </packages>\n" +
                        "    <orderLines>\n" +
                        "        <orderLine>\n" +
                        "            <orderLineNo>1</orderLineNo>\n" +
                        "            <orderSourceCode></orderSourceCode>\n" +
                        "            <subSourceCode></subSourceCode>\n" +
                        "            <ownerCode>jhyd</ownerCode>\n" +
                        "            <itemCode>416</itemCode>\n" +
                        "            <itemId>1700040948</itemId>\n" +
                        "            <inventoryType>ZP</inventoryType>\n" +
                        "            <itemName></itemName>\n" +
                        "            <extCode></extCode>\n" +
                        "            <planQty>2</planQty>\n" +
                        "            <actualQty>2</actualQty>\n" +
                        "            <batchCode></batchCode>\n" +
                        "            <productDate></productDate>\n" +
                        "            <expireDate></expireDate>\n" +
                        "            <produceCode></produceCode>\n" +
                        "            <batchs/>\n" +
                        "            <qrCode></qrCode>\n" +
                        "        </orderLine>\n" +
                        "    </orderLines>\n" +
                        "</request>";

        XStream xStream = JiuUtils.createXstream();
        xStream.alias("request", ConfirmOrderBean.class);
        xStream.alias("deliveryOrder", DeliveryOrderBean.class);
        xStream.alias("orderLine", OrderLineItemBean.class);
        xStream.alias("package", PackageBean.class);
        xStream.alias("item", ItemBean.class);
        xStream.alias("batch", BatchBean.class);
        xStream.processAnnotations(ConfirmOrderBean.class);
        ConfirmOrderBean confirmOrderBean = (ConfirmOrderBean) xStream.fromXML(xml);
        System.out.println(confirmOrderBean.toString());
    }

    private static void teste() {
        Map<String, String> map = new HashMap<>();
        map.put("method", "deliveryorder.create");
        map.put("timestamp", DateUtils.getNowTime());
        map.put("format", "format");
        map.put("app_key", "e966a9f1-d110-49f8-8a0a-7f6e184d7326");
        map.put("v", "2.0");
        map.put("sign_method", "md5");
        map.put("customerId", "test");

        StringBuilder body = new StringBuilder();
        Set<String> keySet = map.keySet();
        Iterator<String> iterable = keySet.iterator();
        List<String> keyList = new ArrayList<>();
        while (iterable.hasNext()) {
            String key = iterable.next();
            body.append(key);
            body.append("=");
            body.append(map.get(key));
            body.append("&");
            keyList.add(key);
        }
        System.out.println(body.toString());
        keyList.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        String bodyStr = body.toString().substring(0, body.length() - 1);
        StringBuilder secretStr = new StringBuilder();
        secretStr.append("test");
        for (int i = 0; i < keyList.size(); i++) {
            String key = keyList.get(i);
            secretStr.append(key);
            secretStr.append(map.get(key));
        }
        secretStr.append(bodyStr);
        secretStr.append("test");
        System.out.println(secretStr);
        try {
            String secret = DigestUtils.encodeMD5Hex(secretStr.toString().getBytes("utf-8"));
            System.out.println(secret);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    private static void testd() {
        Date dateS = new Date();
        String date = new SimpleDateFormat("yyyyMMdd").format(dateS);
        String time = new SimpleDateFormat("HHmmss").format(dateS);
        int max = 100;
        int min = 10;
        Random random = new Random();
        int second = random.nextInt(max) % (max - min + 1) + min;

        String order = date + second + time + getEight();
        System.out.println(order);
    }

    private static int getEight() {
        int max = 900000000;
        int min = 100000000;
        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
    }


    private static void testc() {
        try {
            SchedulerManager manager = SchedulerManager.getInstance();
            manager.start();
            Scheduler scheduler = manager.getScheduler();

            //定义一个JobDetail
            JobDetail job = JobBuilder.newJob(HelloQuartz.class) //定义Job类为HelloQuartz类，这是真正的执行逻辑所在
                    .withIdentity("job1", "group1") //定义name/group
                    .build();


            Date start = DateBuilder.nextGivenSecondDate(null, 4);

            //定义一个Trigger  触发器
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1") //定义name/group
//                    .startNow()//一旦加入scheduler，立即生效
                    .startAt(start)
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule() //使用SimpleTrigger
                    )
                    .build();
//            Thread.sleep(4000);
            //加入这个调度
            scheduler.scheduleJob(job, trigger);
            Thread.sleep(6000);
            manager.shutDown();

        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testb() {
        try {
            //创建scheduler
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            //定义一个Trigger
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1") //定义name/group
                    .startNow()//一旦加入scheduler，立即生效
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule() //使用SimpleTrigger
                    )
                    .build();

            //定义一个JobDetail
            JobDetail job = JobBuilder.newJob(HelloQuartz.class) //定义Job类为HelloQuartz类，这是真正的执行逻辑所在
                    .withIdentity("job1", "group1") //定义name/group
                    .usingJobData("name", "quartz") //定义属性
                    .build();

            //加入这个调度
            scheduler.scheduleJob(job, trigger);

            //启动之
            scheduler.startDelayed(4);

            //运行一段时间后关闭
            Thread.sleep(6000);
            scheduler.shutdown(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double convert(String numStr) {
        double num = Double.parseDouble(numStr);
        int d = (int) (num / 100);
        double s = (num - d * 100) / 60;
        return d + s;
    }

    public static void test4() {
        String str = "D5:B7:1F:C1:F9:E2:42:68:36:83:F1:41:C9:8F:AF:59";
        String[] arr = str.split(":");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i].toLowerCase());
        }

        System.out.println(sb);
    }


    public static boolean isMobileNO(String mobiles) {

        Pattern p = Pattern.compile("^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");

        Matcher m = p.matcher(mobiles);

        System.out.println(m.matches() + "---");

        return m.matches();

    }

    public static String bytes2Hex(byte[] src) {
        if (src == null || src.length <= 0) {
            return null;
        }

        char[] res = new char[src.length * 2]; // 每个byte对应两个字符
        final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        for (int i = 0, j = 0; i < src.length; i++) {
            res[j++] = hexDigits[src[i] >> 4 & 0x0f]; // 先存byte的高4位
            res[j++] = hexDigits[src[i] & 0x0f]; // 再存byte的低4位
        }

        return new String(res);
    }

    public static byte[] hexToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }

        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] bytes = new byte[length];
        String hexDigits = "0123456789abcdef";
        for (int i = 0; i < length; i++) {
            int pos = i * 2; // 两个字符对应一个byte
            int h = hexDigits.indexOf(hexChars[pos]) << 4; // 注1
            int l = hexDigits.indexOf(hexChars[pos + 1]); // 注2
            if (h == -1 || l == -1) { // 非16进制字符
                return null;
            }
            bytes[i] = (byte) (h | l);
        }
        return bytes;
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    private static void testB() {
        byte[] by = "x22450.00".getBytes();
        System.out.println(by.length);
        System.out.println('0' + Integer.toHexString(by[0] & 0XFF));
        byte[] by2 = new byte[1];
        by2[0] = by[0];
        System.out.println(String.valueOf(by[0]));
        try {
            System.out.println(new String(by2, "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testa() {
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " × " + i + " = " + (i * j));
                System.out.print("\t");
            }
            System.out.println("");
        }
    }

}

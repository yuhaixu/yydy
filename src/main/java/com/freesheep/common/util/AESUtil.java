package com.freesheep.common.util;

import com.freesheep.web.util.Constant;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class AESUtil {

    public static final String KEY_ALGORITHM = "AES";
    public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * 加密
     *
     * @param content  需要加密的内容
     * @param password 加密密码
     * @return
     */
    public static byte[] encrypt(String content, String password) {
        try {
            SecretKey secretKey = new SecretKeySpec(password.getBytes("utf-8"), KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);// 创建密码器
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);// 初始化
            return cipher.doFinal(content.getBytes("utf-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] cookEncrypt(String content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes());
            kgen.init(128, random);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            return cipher.doFinal(byteContent);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  AES加密 携带base64
     * @param content
     * @param password
     * @return 加密后的数据
     * @throws Exception
     */
    public static String encryptForBase64(String content, String password){
        if(StringUtils.isEmpty(content) || StringUtils.isEmpty(password)) return null;
        byte[] arr = encrypt(content, password);
        if(arr == null || arr.length == 0) return null;
        try {
            return Base64Utils.encode(arr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 解密
     *
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     */
    public static byte[] decrypt(byte[] content, String password) {
        try {
            SecretKey secretKey = new SecretKeySpec(password.getBytes(), KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, secretKey);// 初始化
            return cipher.doFinal(content);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     * @param content
     * @param password
     * @return 解密后的数据
     */
    public static String decryptForBase64(byte[] content, String password){
        if(content == null || StringUtils.isEmpty(password)) return null;
        try {
            byte[] arr = Base64.decodeBase64(content);
            byte[] deArr = decrypt(arr, password);
            return new String(deArr, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String Md5(String mail,long dspId) throws NoSuchAlgorithmException {
        String contentHead = mail.substring(0, mail.indexOf("@")) + Long.toString(dspId);
        String contentTail = mail.substring(mail.indexOf("@"),mail.length());
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(contentHead.getBytes());
        byte [] b = md.digest();
        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }

        return  buf.toString().substring(8, 24)  + contentTail;
    }


    public static void main(String[] args) throws Exception {

        String str = "测试数据";

        String sc = encryptForBase64(str, Constant.APP_REQUEST);
        System.out.println("加密后的数据 ： " + sc);
        String de = decryptForBase64(sc.getBytes("utf-8"), Constant.APP_REQUEST);
        System.out.println("解密后的数据 ： " + de);


    }
}

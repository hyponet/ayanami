package xyz.acmer.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import xyz.acmer.entity.user.User;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密相关工具集
 * Created by hypo on 16-2-17.
 */
public class EncryptHelper {

    public static String md5(String srt){

        if(srt == null || srt.length() == 0){
            return "";
        }

        MessageDigest md5= null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //加密后的字符串
        String rnt = "";
        BASE64Encoder base64en = new BASE64Encoder();
        try {
            rnt = base64en.encode(md5.digest(srt.getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return rnt;
    }

    public static String getPassword(String str){

        // 加盐
        String str1 = md5(str + "unnecessary");
        String password = md5(str1 + str);

        return password;
    }

    /**
     * Base64加密
     * @param str
     * @return
     */
    public static String getBase64(String str) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }

        return s;
    }

    /**
     * Base64解密
     * @param s
     * @return
     */
    public static String getFromBase64(String s) {

        byte[] b;
        String result = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();

                return null;
            }
        }

        return result;
    }
}

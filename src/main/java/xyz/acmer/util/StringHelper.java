package xyz.acmer.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 字符串相关工具集
 * Created by hypo on 16-2-15.
 */
public class StringHelper {

    public static String getCurrentDateTime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(date);
    }

    public static String getSafeString(String str, Integer maxLength){

        if (str == null){
            return "";
        }
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");

        if (str.length() > maxLength){
            str = str.substring(0, maxLength);
        }

        return str;
    }

    public static Date stringToDate(String str){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return date;
    }
}

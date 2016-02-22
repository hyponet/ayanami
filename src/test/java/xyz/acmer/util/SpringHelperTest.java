package xyz.acmer.util;

import org.junit.Test;

import java.util.Date;

/**
 * Created by hypo on 16-2-22.
 */
public class SpringHelperTest {

    @Test
    public void getDateByString(){
        String string = "2016-02-45";

        Date date = StringHelper.stringToDate(string);

        System.out.println(StringHelper.getCurrentDateTime(date));
    }
}

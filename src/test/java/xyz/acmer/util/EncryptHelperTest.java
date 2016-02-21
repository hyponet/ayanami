package xyz.acmer.util;

import org.junit.Test;

/**
 * Created by hypo on 16-2-21.
 */
public class EncryptHelperTest {

    @Test
    public void getPassword(){

        String password = "This is a Password";
        for(int i = 0;i < 10;i++){
            password = EncryptHelper.getPassword(password);
            System.out.println(i + " ### " + password);
        }

        System.out.println();

        password = "This is a Password";
        for(int i = 0;i < 10;i++){
            password = EncryptHelper.getPassword(password);
            System.out.println(i + " ### " + password);
        }
    }

    @Test
    public void testMD5(){

        String string = "This is a String";
        StringBuffer stringBuffer = new StringBuffer();

        for(int i = 0;i < 50;i++){
            stringBuffer.append(string + (i % 10 == 0 ? "\n" : " ")) ;
        }

        System.out.println(stringBuffer);
        System.out.println(EncryptHelper.md5(string));
    }
}

package xyz.acmer.util;

import org.json.JSONObject;
import org.junit.Test;

/**
 * Created by hypo on 16-2-21.
 */
public class JsonSenderTest {

    @Test
    public void senderGET(){
        JSONObject jsonObject = JsonSender.get("http://127.0.0.1:5000/poj/problem/1000");

        System.out.println(jsonObject.get("problem_id") + "\n" + jsonObject);
    }

    @Test
    public void senderPOST(){
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("password", "mxh19940822");

        JSONObject userInfo = JsonSender.post("http://127.0.0.1:5000/poj/user/13110572081", jsonObject);

        System.out.println(userInfo);
    }
}

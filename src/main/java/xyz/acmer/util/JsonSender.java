package xyz.acmer.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;

/**
 * 负责发送和接受json的工具类
 * Created by hypo on 16-2-21.
 */
public class JsonSender {

    /**
     * post方法发送json，并返回json
     * @param url
     * @param json
     * @return
     */
    public static JSONObject post(String url,JSONObject json){
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        JSONObject response = null;
        try {
            StringEntity s = new StringEntity(json.toString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");
            post.setEntity(s);

            HttpResponse res = httpClient.execute(post);
            if(res.getStatusLine().getStatusCode() == HttpStatus.OK.value()){
                HttpEntity entity = res.getEntity();

                if (entity != null) {
                    String retSrc = EntityUtils.toString(entity);
                    // 格式化为JSON
                    response = new JSONObject(retSrc);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    /**
     * get方法获得json
     * @param url
     * @return
     */
    public static JSONObject get(String url){
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);
        JSONObject response = null;
        try {
            get.addHeader("accept", "application/json");

            HttpResponse res = httpClient.execute(get);
            if(res.getStatusLine().getStatusCode() == HttpStatus.OK.value()){

                HttpEntity entity = res.getEntity();

                if (entity != null) {
                    String retSrc = EntityUtils.toString(entity);
                    // 格式化为JSON
                    response = new JSONObject(retSrc);
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}

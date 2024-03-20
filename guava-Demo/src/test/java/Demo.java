import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author: 11653
 * @createTime: 2024/02/29 9:11
 * @package: PACKAGE_NAME
 * @description:
 */
public class Demo {

    private static final String KEYWORD_URL = "http://10.203.7.251:19002/CKE_out_results";
    public static void main(String[] args) throws IOException {

        long currentTimestamp = System.currentTimeMillis();

        //doPostJson(currentTimestamp, "贵港市工信局");

        String op = "1222222222222222333345";

        String substring = op.substring(0, op.length() - 1);



        //封装数据
        /**"level-p1":"项目名称",
         "level-p2":"招投标标题",
         "level-p3":"业绩合同名称",
         "level-p4":"经营范围" ,  */
        Map<String,Object> map =new HashMap<>();
        map.put("id",9999);
        map.put("level-p1","南方电网百色供电局");
        map.put("level-p2","");
        map.put("level-p3","");
        map.put("level-p4", "");
        map.put("level-p5","");
        JSONArray arrs = new JSONArray();
        arrs.add(map);

        //提取关键词
        String[] keyword = null;
        String result = doPost(KEYWORD_URL, arrs.toJSONString());
        JSONObject resultObj = JSONObject.parseObject(result);
        String code = (String) resultObj.get("code");
        JSONArray lists = resultObj.getJSONArray("data");
        JSONObject data = new JSONObject();
        if ("200".equals(code) && lists.size() > 0) {
            data = (JSONObject) lists.get(0);
        }
        if (data.getJSONArray("level-p1").size() > 0) {
            keyword = JsonToArray(data.getJSONArray("level-p1"));
        }
        int i = 0;
    }


    public static void httpoy(long op) throws IOException{
        // 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 目标URL
        String url = "https://capi.tianyancha.com/cloud-tempest/search/suggest/v3?_=" + op;

        // 创建HttpPost对象
        HttpPost httpPost = new HttpPost(url);

        // 设置请求头
        httpPost.addHeader("X-Auth-Token", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxODc3NzIzMjMyNCIsImlhdCI6MTcwOTE3MjA2MSwiZXhwIjoxNzExNzY0MDYxfQ.wjz8lsGMJYF_CcJpEBkpvz8vGgl0UPePx4mOZ6BwJ2WN4cmsjexjjZ073ig-mTg_zZJEi5cwHLBCMxjWAVgfmw");
        httpPost.addHeader("X-Tycid", "12d2ed60aba911ee9f17b153529da940");
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36");

        // 设置请求体
        String jsonBody = "{\"keyword\" : \"南宁\"}";
        StringEntity entity = new StringEntity(jsonBody);
        httpPost.setEntity(entity);

        // 执行请求并获取响应
        HttpResponse response = httpClient.execute(httpPost);

        // 从响应中提取内容
        HttpEntity responseEntity = response.getEntity();
        String responseBody = EntityUtils.toString(responseEntity);

        // 输出响应内容
        System.out.println(responseBody);

        int op1 = 1;

        // 关闭HttpClient
        httpClient.close();
    }

    public static  void doPostJson(long op,String name) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";

        String jsonBody = "{\"keyword\" : \""+ name +"\"}";

        String url = "https://capi.tianyancha.com/cloud-tempest/search/suggest/v3?_=" + op;
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);

            httpPost.addHeader("X-Auth-Token",
                    "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxODc3NzIzMjMyNCIsImlhdCI6MTcwOTE3MjA2MSwiZXhwIjoxNzExNzY0MDYxfQ.wjz8lsGMJYF_CcJpEBkpvz8vGgl0UPePx4mOZ6BwJ2WN4cmsjexjjZ073ig-mTg_zZJEi5cwHLBCMxjWAVgfmw");
            httpPost.addHeader("X-Tycid", "12d2ed60aba911ee9f17b153529da940");
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("Version", "TYC-Web");
            httpPost.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36");

//            // 创建请求内容
            StringEntity entity = new StringEntity(jsonBody, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        JSONArray data = JSON.parseObject(resultString).getJSONArray("data");

        ArrayList<Map<String, String>> objects = new ArrayList<>();
        for (Object obj : data) {
            Map<String, String> resultMap = new HashMap<>();
            JSONObject rt = (JSONObject) obj;
            for (Map.Entry<String, Object> entry : rt.entrySet()) {
                if (ObjectUtils.isEmpty(entry.getValue())){
                    continue;
                }
                resultMap.put(entry.getKey(), entry.getValue().toString());
            }
            objects.add(resultMap);
        }
        int io1 = 1;
        //return dataMap;
    }

    public static String doPost(String url ,String json) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String res = "";

        try {
            HttpPost post = new HttpPost(url);
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            post.setEntity(entity);
            post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0"); // 设置请求头消息User-Agent
            response = httpClient.execute(post);

            res = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }


    public static String[] JsonToArray(JSONArray array){
        TreeSet<String> set = new TreeSet<String>();
        for(int i=0; i<array.size(); i++) {
            Object object = array.get(i);
            if(object instanceof JSONArray){//子元素为数组
                JSONArray objects = array.getJSONArray(i);
                //创建一个与JSONArray 长度相同的String数组
                String[] arr = new String[objects.size()];
                //使用JSONArray 中的toArray进行转换
                String[] strings = objects.toArray(arr);
                for (String s : strings) {
                    set.add(s);
                }
            }else{//子元素为String
                set.add(object.toString());
            }
        }
        String[] result =set.toArray(new String[0]);
        return result;
    }

}

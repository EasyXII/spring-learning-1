package com.soft1851.spring.web.Spider;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.soft1851.spring.web.entity.Topic;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/3/27 14:06
 * @Version 1.0
 **/
public class JueJinSpider {
    private static final Integer SUCCESS = 200;

    public static List<Topic> getTopics() {
        List<Topic> topics = new ArrayList<>();
        //目标地址
        String url = "https://short-msg-ms.juejin.im/v1/topicList?uid=&device_id=&token=&src=web&sortType=hot&page=0&pageSize=100";
        //创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建get对象
        HttpGet get = new HttpGet(url);
        //创建context对象
        HttpClientContext context = HttpClientContext.create();
        //创建response对象
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(get, context);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //输出状态行  403 访问被拒绝  404找不到  405方法不对应，比如get,post
//        System.out.println(response.getStatusLine());
        //如果请求成功，则获取网页源码
        if (response.getStatusLine().getStatusCode() == SUCCESS) {
            //获取响应对象实体，并转成UTF-8字符串
            HttpEntity entity = response.getEntity();
            String res = null;
            try {
                res = EntityUtils.toString(entity, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
            //json数据
//            System.out.println("res是整个大的json对象");
//            System.out.println(res);
            //字符串转成json数据
            JSONObject jsonObject = JSONObject.parseObject(res);
            //get到键是d的值      获取d的json对象             再获取list集合
            JSONArray list = jsonObject.getJSONObject("d").getJSONArray("list");
            //遍历这个json数组
            list.forEach(o -> {
                //json数组中的对象不是JSONObject,只是简单的字符串
                //再把字符串转成JSONObject对象
                JSONObject json = JSONObject.parseObject(o.toString());
                Topic topic = Topic.builder()
                        .Id(json.getString("objectId"))
                        .topicName(json.getString("title"))
                        .topicIcon(json.getString("icon"))
                        .description(json.getString("description"))
                        .msgCount(json.getInteger("msgsCount"))
                        .followsCount(json.getInteger("followsCount"))
                        .followed(json.getBoolean("followed"))
                        .build();
                topics.add(topic);
            });
        } else {
            System.out.println("请求失败");
        }
        try {
            response.close();
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return topics;
    }

    public static void main(String[] args) {
        System.out.println(getTopics());
    }
}

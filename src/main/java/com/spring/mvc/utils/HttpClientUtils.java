package com.spring.mvc.utils;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import javax.ws.rs.core.Response;
import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liluoqi on 15/4/25.
 */
public class HttpClientUtils {
    private static Logger logger = Logger.getLogger(HttpClientUtils.class);
    private static RequestConfig config = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(15000).build();
    private static CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();

    /**
     * @param url  访问的URL
     * @param data 发送的数据，这个接口最终会以json的方式post过去
     * @return 返回json格式，仅仅满足于返回的数据格式是json的，其他的类型的没有封装
     */
    public static <T> T postJson(String url, Object data, Class<T> tClass) {
        try {
            logger.info(String.format("发送到中外运的业务数据是:%s", new Gson().toJson(data)));
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(new Gson().toJson(data)));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = StringUtils.emptyString();
            if (entity != null) {
                result = IOUtils.toString(entity.getContent(), "UTF-8");
            }
            httpPost.abort();
            logger.info(String.format("发送中外运订单返回结果是:%s", result));
            return new Gson().fromJson(result, tClass);
        } catch (UnsupportedEncodingException e) {
            logger.error("不支持的编码格式", e);
        } catch (ClientProtocolException e) {
            logger.error("客户端协议异常", e);
        } catch (IOException e) {
            logger.error("IO异常", e);
        } catch (Exception e) {
            logger.error("post提交时异常", e);
        }
        return null;
    }

    /**
     * @param url 请求地址url
     * @param map 表单数据，是一个map形式
     * @return 仅仅满足于返回的数据格式是json的，其他的类型的没有封装
     */
    public static <T> T postForm(String url, Map<String, String> map, Class<T> tClass) {
        try {
            List<NameValuePair> formParams = new ArrayList<NameValuePair>();
            for (String key : map.keySet()) {
                formParams.add(new BasicNameValuePair(key, map.get(key)));
            }
            HttpPost httpPost = new HttpPost(url);
            HttpEntity httpEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
            httpPost.setEntity(httpEntity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = StringUtils.emptyString();
            if (entity != null) {
                result = IOUtils.toString(entity.getContent(), "UTF-8");
            }
            httpPost.abort();
            return new Gson().fromJson(result, tClass);
        } catch (UnsupportedEncodingException e) {
            logger.error("不支持的字符编码", e);
        } catch (IOException e) {
            logger.error("IO异常", e);
        } catch (Exception e) {
            logger.error("post提交时异常", e);
        }
        return null;
    }

    /**
     * @param url 请求地址url
     * @param map 表单数据，是一个map形式
     * @return 仅仅满足于返回的数据格式是json的，其他的类型的没有封装
     */
    public static <T> T postForm(String url, Map<String, String> map, TypeToken<T> typeToken) {
        try {
            List<NameValuePair> formParams = new ArrayList<NameValuePair>();
            for (String key : map.keySet()) {
                formParams.add(new BasicNameValuePair(key, map.get(key)));
            }
            HttpPost httpPost = new HttpPost(url);
            HttpEntity httpEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
            httpPost.setEntity(httpEntity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = StringUtils.emptyString();
            if (entity != null) {
                result = IOUtils.toString(entity.getContent(), "UTF-8");
            }
            httpPost.abort();
            return new Gson().fromJson(result, typeToken.getType());
        } catch (UnsupportedEncodingException e) {
            logger.error("不支持的字符编码", e);
        } catch (IOException e) {
            logger.error("IO异常", e);
        } catch (Exception e) {
            logger.error("post提交时异常", e);
        }
        return null;
    }


    /**
     * @param url 请求地址url
     * @param map 表单数据，是一个map形式
     * @return 返回response里面的字符串
     */
    public static String postForm(String url, Map<String, String> map) {
        try {
            List<NameValuePair> formParams = new ArrayList<NameValuePair>();
            for (String key : map.keySet()) {
                formParams.add(new BasicNameValuePair(key, map.get(key)));
            }
            HttpPost httpPost = new HttpPost(url);
            HttpEntity httpEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
            httpPost.setEntity(httpEntity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = StringUtils.emptyString();
            if (entity != null) {
                result = IOUtils.toString(entity.getContent(), "UTF-8");
            }
            httpPost.abort();
            return result;
        } catch (UnsupportedEncodingException e) {
            logger.error("不支持的字符编码", e);
        } catch (IOException e) {
            logger.error("IO异常", e);
        } catch (Exception e) {
            logger.error("获取中外运token出现异常", e);
        }
        return null;
    }


    /**
     * @param url 请求地址url
     * @param map 表单数据，是一个map形式
     * @return 返回 response
     */
    public static CloseableHttpResponse post(String url, Map<String, String> map) {
        try {
            List<NameValuePair> formParams = new ArrayList<NameValuePair>();
            for (String key : map.keySet()) {
                formParams.add(new BasicNameValuePair(key, map.get(key)));
            }
            HttpPost httpPost = new HttpPost(url);
            HttpEntity httpEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
            httpPost.setEntity(httpEntity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            httpPost.abort();
            return response;
        } catch (UnsupportedEncodingException e) {
            logger.error("不支持的字符编码", e);
        } catch (IOException e) {
            logger.error("IO异常", e);
        } catch (Exception e) {
            logger.error("获取中外运token出现异常", e);
        }
        return null;
    }

    public static InputStream postJsonData(String url, String jsonString, boolean setFormHeader) {
        try {
            // 创建连接
            URL requestUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) requestUrl.openConnection();

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            if (setFormHeader) {
                conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            }
            conn.connect();
            // json参数
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(jsonString);
            out.flush();
            out.close();

            InputStream inputStream = conn.getInputStream();
            // 关闭连接
            conn.disconnect();
            return inputStream;
        } catch (Exception e) {
            logger.error("IO异常", e);
            return null;
        }
    }
}
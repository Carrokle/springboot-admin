package com.lb.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Map;

import javax.net.ssl.SSLContext;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author liub
 * @since on 2018/7/25
 */
public final class HttpUtil {

    private static  Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    private static final MediaType CONTENT_TYPE_FORM = MediaType
            .parse("application/x-www-form-urlencoded;charset=UTF-8");
    private static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36";

    private HttpUtil() {
    }

    public static final String get(String url) {
        String result = "";
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        try {
            client.executeMethod(method);
            result = method.getResponseBodyAsString();
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            method.releaseConnection();
        }
        return result;
    }

    public static final String post(String url, ArrayList<NameValuePair> list) {
        String result = "";
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(url);
        try {
            NameValuePair[] params = new NameValuePair[list.size()];
            for (int i = 0; i < list.size(); i++) {
                params[i] = list.get(i);
            }
            method.addParameters(params);
            client.executeMethod(method);
            result = method.getResponseBodyAsString();
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            method.releaseConnection();
        }
        return result;
    }

    /**
     * 使用okhttp进行请求
     * @param url
     * @param params
     * @return
     */
    public static String post(String url, String params) {
        RequestBody body = RequestBody.create(CONTENT_TYPE_FORM, params);
        Request request = new Request.Builder().url(url).post(body).build();
        return exec(request);
    }

    /**
     * 使用okhttp上传文件例子
     * @param url
     * @param fileKey
     * @param fileName
     * @param file
     * @param params
     * @return
     */
    public static String post(String url, String fileKey, String fileName, File file, Map<String,String> params){
        MediaType fileMediaType = MediaType.parse("multipart/form-data");
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file",fileName,RequestBody.create(fileMediaType,file))
                .addFormDataPart("key","value")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        return exec(request);

    }

    private static String exec(Request request) {
        try {
            okhttp3.Response response = new OkHttpClient().newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new RuntimeException("Unexpected code " + response);
            }
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String postSSL(String url, String data, String certPath, String certPass) {
        try {
            KeyStore clientStore = KeyStore.getInstance("PKCS12");
            // 读取本机存放的PKCS12证书文件
            FileInputStream instream = new FileInputStream(certPath);
            try {
                // 指定PKCS12的密码(商户ID)
                clientStore.load(instream, certPass.toCharArray());
            } finally {
                instream.close();
            }
            SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(clientStore, certPass.toCharArray()).build();
            // 指定TLS版本
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null,
                SSLConnectionSocketFactory.getDefaultHostnameVerifier());
            // 设置httpclient的SSLSocketFactory
            CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            try {
                HttpPost httpost = new HttpPost(url); // 设置响应头信息
                httpost.addHeader("Connection", "keep-alive");
                httpost.addHeader("Accept", "*/*");
                httpost.addHeader("Content-Type", CONTENT_TYPE_FORM.toString());
                httpost.addHeader("X-Requested-With", "XMLHttpRequest");
                httpost.addHeader("Cache-Control", "max-age=0");
                httpost.addHeader("User-Agent", DEFAULT_USER_AGENT);
                httpost.setEntity(new StringEntity(data, "UTF-8"));
                CloseableHttpResponse response = httpclient.execute(httpost);
                try {
                    HttpEntity entity = response.getEntity();
                    String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
                    EntityUtils.consume(entity);
                    return jsonStr;
                } finally {
                    response.close();
                }
            } finally {
                httpclient.close();
            }
        } catch (Exception e) {
            logger.error("", e);
            throw new RuntimeException(e);
        }
    }
}

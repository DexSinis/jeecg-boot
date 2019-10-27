package org.jeecg.util.http;

import org.jeecg.util.http.HttpResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * 工具类 - httpclient请求
 */
public class HttpClientUtils {

    private static final String CHARSET_UTF8 = "UTF-8";

    /**
     * 服务处理响应超时时间
     */
    private final static int SOCKET_TIMEOUT = 5000;

    /**
     * 建立连接超时时间
     */
    private final static int CONNECT_TIMEOUT = 5000;

    public static HttpResponse doGet(String uri) throws IOException {
        return doGet(uri, SOCKET_TIMEOUT, CONNECT_TIMEOUT);
    }

    public static HttpResponse doGet(String uri, int socketTimeout, int connectTimeout, Header... headers) throws IOException {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        CloseableHttpResponse closeableHttpResponse = null;
        try {
            HttpGet httpGet = new HttpGet(uri);
            RequestConfig config = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
            httpGet.setConfig(config);
            httpGet.setHeaders(headers);

            long startTime = System.currentTimeMillis();
            closeableHttpResponse = closeableHttpClient.execute(httpGet);

            String stringResult = null;
            HttpEntity entity = closeableHttpResponse.getEntity();
            if (entity != null) {
                stringResult = EntityUtils.toString(entity, CHARSET_UTF8);
            }

            int costsMilliseconds = (int) (System.currentTimeMillis() - startTime);
            int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
            return new HttpResponse(statusCode, costsMilliseconds, stringResult);
        } finally {
            org.apache.http.client.utils.HttpClientUtils.closeQuietly(closeableHttpClient);
            org.apache.http.client.utils.HttpClientUtils.closeQuietly(closeableHttpResponse);
        }
    }

    public static HttpResponse doPostByJson(String uri, String body) throws IOException {
        return doPostByJson(uri, body, SOCKET_TIMEOUT, CONNECT_TIMEOUT);
    }

    public static HttpResponse doPostByJson(String uri, String body, int socketTimeout, int connectTimeout) throws IOException {
        Header contentTypeHeader = new BasicHeader(HTTP.CONTENT_TYPE, "application/json");
        return doPostByBody(uri, body, socketTimeout, connectTimeout, contentTypeHeader);
    }

    public static HttpResponse doPostByXml(String uri, String body) throws IOException {
        return doPostByXml(uri, body, SOCKET_TIMEOUT, CONNECT_TIMEOUT);
    }

    public static HttpResponse doPostByXml(String uri, String body, int socketTimeout, int connectTimeout) throws IOException {
        Header contentTypeHeader = new BasicHeader(HTTP.CONTENT_TYPE, "application/xml");
        return doPostByBody(uri, body, socketTimeout, connectTimeout, contentTypeHeader);
    }

    public static HttpResponse doPostByBody(String uri, String body, int socketTimeout, int connectTimeout, Header... headers) throws IOException {
        HttpPost httpPost = new HttpPost(uri);
        RequestConfig config = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
        httpPost.setConfig(config);
        httpPost.setHeaders(headers);

        if (StringUtils.isNotBlank(body)) {
            StringEntity entity = new StringEntity(body, CHARSET_UTF8);
            httpPost.setEntity(entity);
        }

        return doPost(httpPost);
    }

    private static HttpResponse doPost(HttpPost httpPost) throws IOException {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        CloseableHttpResponse closeableHttpResponse = null;

        try {
            long startTime = System.currentTimeMillis();
            closeableHttpResponse = closeableHttpClient.execute(httpPost);
            int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
            if (HttpStatus.SC_MOVED_PERMANENTLY == statusCode || HttpStatus.SC_MOVED_TEMPORARILY == statusCode || HttpStatus.SC_SEE_OTHER == statusCode) {
                HttpGet httpGet = new HttpGet(closeableHttpResponse.getLastHeader("location").getValue());
                closeableHttpResponse = closeableHttpClient.execute(httpGet);
                statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
            }

            String stringResult = null;
            HttpEntity entity = closeableHttpResponse.getEntity();
            if (entity != null) {
                stringResult = EntityUtils.toString(entity, CHARSET_UTF8);
            }

            int costsMilliseconds = (int) (System.currentTimeMillis() - startTime);
            return new HttpResponse(statusCode, costsMilliseconds, stringResult);
        } finally {
            org.apache.http.client.utils.HttpClientUtils.closeQuietly(closeableHttpClient);
            org.apache.http.client.utils.HttpClientUtils.closeQuietly(closeableHttpResponse);
        }
    }

    /**
     * post请求，参数在uri里，比如form提交
     */
    public static HttpResponse doPostByParams(String uri, Map<String, String> params) throws IOException {
        return doPostByParams(uri, params, SOCKET_TIMEOUT, CONNECT_TIMEOUT);
    }

    /**
     * post请求，参数在uri里，比如form提交
     */
    public static HttpResponse doPostByParams(String uri, Map<String, String> params, int socketTimeout, int connectTimeout, Header... headers) throws IOException {
        HttpPost httpPost = new HttpPost(uri);
        RequestConfig config = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
        httpPost.setConfig(config);
        httpPost.setHeaders(headers);

        if (params != null && !params.isEmpty()) {
            List<NameValuePair> nameValuePairs = new ArrayList<>(params.size());
//            params.forEach((name, value) -> nameValuePairs.add(new BasicNameValuePair(name, value)));
            params.forEach(new BiConsumer<String, String>() {
                @Override
                public void accept(String name, String value) {
                    nameValuePairs.add(new BasicNameValuePair(name, value));
                }
            });
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, CHARSET_UTF8));
        }

        return doPost(httpPost);
    }


    public static CloseableHttpClient getHttpsClient() throws Exception {
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
                null, (chain, authType) -> {
                    return true; //信任所有
                }).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    }

}

package xyz.ddlnt.commonutil.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * @author ZZULI_SE 210910
 * @data 2025/7/16 22:27
 */
public class HttpUtils {


    public static CloseableHttpClient createInsecureClient() {
        try {
            // 创建信任所有证书的 TrustManager
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                    }
            };

            // 初始化 SSLContext
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            return HttpClients.custom()
                    .setSSLSocketFactory(new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.getDefaultHostnameVerifier()))
                    .build();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException("Failed to create insecure HttpClient", e);
        }
    }

    public static String postJsonStr(String url, Map<String, String> headers, String jsonStr) {
        // 创建HttpClient实例
//        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
        try (CloseableHttpClient httpClient = createInsecureClient()) {
            // 创建POST请求
            HttpPost httpPost = new HttpPost(url);

            // 设置请求头
            if (headers != null && !headers.isEmpty()) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }
            // 设置请求体（JSON格式）
            StringEntity entity = new StringEntity(jsonStr, StandardCharsets.UTF_8);
            httpPost.setEntity(entity);

            // 执行请求并获取响应
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                // 解析响应实体
                HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {
                    return EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
                }
            }
        } catch (Exception e) {
            // 处理异常（实际应用中建议更详细的异常处理）
            e.printStackTrace();
        }
        return null;
    }

    public static String httpGet(String url, Map<String, String> headers, Map<String, String> params) {
        // 创建HttpClient实例
//        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
        try (CloseableHttpClient httpClient = createInsecureClient()) {
            // 构建带参数的URI
            URIBuilder uriBuilder = new URIBuilder(url);
            if (params != null && !params.isEmpty()) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    uriBuilder.addParameter(entry.getKey(), entry.getValue());
                }
            }
            URI uri = uriBuilder.build();

            // 创建GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 设置请求头
            if (headers != null && !headers.isEmpty()) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpGet.setHeader(entry.getKey(), entry.getValue());
                }
            }

            // 执行请求并获取响应
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                // 解析响应实体
                HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {
                    return EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
                }
            }
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
        return null;
    }
}

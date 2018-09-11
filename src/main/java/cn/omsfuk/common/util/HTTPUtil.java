package cn.omsfuk.common.util;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * by omsfuk
 * ---- 创建于 9/11/18 8:58 AM
 */
public class HTTPUtil {

    public static final String DEFAULT_CHARSET = "UTF-8";

    /**
     *
     * @param url
     * @param params 参数键值对
     * @return 如果结果为200，正常返回响应体。否则返回null
     */
    public static String get(String url, Map<String, String> params) {
        String finalUrl = buildUrl(url, params);
        try {
            URL urlObject = new URL(finalUrl);
            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder ans = new StringBuilder();
                String tmp = null;
                while ((tmp = reader.readLine()) != null) {
                    ans.append(tmp);
                }
                return ans.toString();
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String buildUrl(String baseUrl, Map<String, String> params) {
        if (params == null || params.isEmpty()) return baseUrl;
        List<String> list = params.entrySet().stream()
                .map(entry -> encode(entry.getKey()) + "=" + encode(entry.getValue()))
                .collect(Collectors.toList());
        return StringUtils.join(list, "&");
    }

    private static String encode(String originStr) {
        try {
            return URLEncoder.encode(originStr, DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}

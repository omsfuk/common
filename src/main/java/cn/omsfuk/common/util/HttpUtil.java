package cn.omsfuk.common.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * by omsfuk
 * ---- 创建于 9/11/18 8:58 AM
 */
public class HttpUtil {

    public static final String DEFAULT_CHARSET = "UTF-8";

    /**
     *
     * @param url
     * @param params 参数键值对
     * @return 如果结果为200，正常返回响应体。否则返回null
     */
    public static String get(String url, Map<String, String> params) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getHttpInputStream(url, params))));
        StringBuilder ans = new StringBuilder();
        String tmp = null;
        try {
            while ((tmp = reader.readLine()) != null) {
                ans.append(tmp);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ans.toString();
    }

    public static int download(String url, String savePath) {
        BufferedInputStream inputStream = new BufferedInputStream(getHttpInputStream(url, null));
        try {
            FileUtils.forceMkdirParent(new File(savePath));
            BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(new File(savePath)));
            byte[] buffer = new byte[1024];
            int totalSize = 0, l;
            while ((l = inputStream.read(buffer, 0, 1024)) != -1) {
                output.write(buffer, 0, l);
                totalSize += l;
            }
            return totalSize;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String buildUrl(String baseUrl, Map<String, String> params) {
        if (params == null || params.isEmpty()) return baseUrl;
        List<String> list = params.entrySet().stream()
                .map(entry -> encode(entry.getKey()) + "=" + encode(entry.getValue()))
                .collect(Collectors.toList());
        return StringUtils.join(list, "&");
    }

    public static String encode(String originStr) {
        try {
            return URLEncoder.encode(originStr, DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private static InputStream getHttpInputStream(String url, Map<String, String> params) {
        String finalUrl = buildUrl(url, params);
        try {
            URL urlObject = new URL(finalUrl);
            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return connection.getInputStream();
            }
            throw new HttpFailureException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

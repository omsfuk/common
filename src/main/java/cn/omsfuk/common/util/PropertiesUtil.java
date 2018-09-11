package cn.omsfuk.common.util;

import java.io.*;

/**
 * by omsfuk
 * ---- 创建于 9/11/18 5:41 PM
 */
public abstract class PropertiesUtil {

    public static Properties parse(File propertyFile) {
        try {
            return parse(new FileInputStream(propertyFile));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Properties parse(InputStream inputStream) {
        Properties properties = new Properties();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String tmp;
            while ((tmp = reader.readLine()) != null) {
                int equalPos = tmp.indexOf('=');
                properties.addProperty(tmp.substring(0, equalPos), tmp.substring(equalPos + 1, tmp.length()));
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }


}

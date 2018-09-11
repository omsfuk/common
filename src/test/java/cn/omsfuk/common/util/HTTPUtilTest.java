package cn.omsfuk.common.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * by omsfuk
 * ---- 创建于 9/11/18 9:54 AM
 */
public class HTTPUtilTest {

    @Test
    public void get() {
        String ans = HTTPUtil.get("https://www.baidu.com", null);
        Assert.assertNotNull(ans);
    }
}

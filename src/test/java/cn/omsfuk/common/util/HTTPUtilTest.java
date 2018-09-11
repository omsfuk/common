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
        String ans = HttpUtil.get("https://www.baidu.com", null);
        Assert.assertNotNull(ans);
        Assert.assertTrue(ans.contains("baidu"));
    }

    @Test
    public void download() {
        int totalSize = HttpUtil.download("https://minecraft.curseforge.com/projects/mob-grinding-utils/files/2602562/download", "/Users/omsfuk/chisel.jar");
        Assert.assertTrue(totalSize > 0);

    }

}

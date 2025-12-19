package com.tool.util;


import org.junit.Test;

import java.util.Date;

public class PhotoUtilsTest {

    /**
     * 测试getPhotoDate方法
     */
    @Test
    public void testGetPhotoDate() {
        String path = "H:\\图片\\手机\\127APPLE\\IMG_7880.JPG";
        System.out.println(PhotoUtils.getPhotoDate(path));
    }
}
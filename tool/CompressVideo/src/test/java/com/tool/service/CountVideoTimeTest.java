package com.tool.service;

import org.junit.jupiter.api.Test;

class CountVideoTimeTest {

    @Test
    void countVideoTime() {
        CountVideoTime countVideoTime = new CountVideoTime();
        countVideoTime.countVideoTime("E:\\学习\\09. 闪聚支付(A)\\day3视频\\视频");
    }
}
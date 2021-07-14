package com.pfxiong.demo.guava;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * @author: xiongpengfei
 * @datetime: 2021/6/29 20:15
 * @description:
 */
public class GuavaBloomFilter {


    @Test
    public void testBloomFilter() throws IOException {
        BloomFilter<Integer> filter = BloomFilter.create(
                Funnels.integerFunnel(),
                1000,
                0.001);
        ByteArrayOutputStream byteOs = new ByteArrayOutputStream();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            filter.put(random.nextInt(500000));
        }
        filter.writeTo(byteOs);
        FileUtils.writeByteArrayToFile(new File("D:\\bloomfilter\\bloom"), byteOs.toByteArray());

//        FileUtils.writeStringToFile(new File("D:\\bloomfilter\\json"), JSON.toJSONString(set), "utf-8");

    }
}
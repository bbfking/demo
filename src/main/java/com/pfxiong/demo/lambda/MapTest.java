package com.pfxiong.demo.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: xiongpengfei
 * @datetime: 2020/11/23 16:27
 * @description:
 */
public class MapTest {

    @Test
    public void testMap() throws InterruptedException {
        List<String> list = Arrays.asList("1", "2", "3", "4");
        list.stream().map(s -> {
            System.out.println(Thread.currentThread().getName());
            new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
            return s + "s";
        }).collect(Collectors.toList());
        Thread.sleep(1000);
    }
}
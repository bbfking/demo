package com.pfxiong.demo.nanoid;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import org.junit.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author: xiongpengfei
 * @datetime: 2020/12/15 10:29
 * @description: https://github.com/aventrix/jnanoid
 */
public class NanoidTest {

    @Test
    public void customIdTest() {
        // Use a faster, but non-secure, random generator

        int size = 8;
        int error = 0;
        for (int j = 0; j < 100; j++) {
            Set<String> idSet = new HashSet<>();
            for (int i = 0; i < 1000000; i++) {
                Random random = new Random();
                String id = NanoIdUtils.randomNanoId(random, "123456789abcdefghijklmnopqrstuvwxyz".toCharArray(), size); // "babbcaabcb"
                idSet.add(id);
            }
            System.out.println(idSet.size());
            if (idSet.size() < 1000000) {
                System.out.println(++error);
            }
        }


    }
}
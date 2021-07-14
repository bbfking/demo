package com.pfxiong.demo.serialization;


import org.xerial.snappy.Snappy;

import java.io.IOException;

/**
 * @author: xiongpengfei
 * @datetime: 2021/3/1 20:00
 * @description: Snappy压缩工作类
 */
public class SnappyUtil {

    /**
     * 使用snappy进行数据压缩
     *
     * @param data
     * @return
     */
    public static byte[] compress(String data) {
        if (data == null) {
            return null;
        }
        byte[] compressed = null;
        try {
            compressed = Snappy.compress(data.getBytes("UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return compressed;
    }
    /**
     * 使用snappy进行数据压缩
     *
     * @param data
     * @return
     */
    public static byte[] compress(byte[] data) {
        if (data == null) {
            return null;
        }
        byte[] compressed = null;
        try {
            compressed = Snappy.compress(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return compressed;
    }

    /**
     * 使用snappy进行数据解压缩
     *
     * @param compressed
     * @return
     */
    public static String uncompress(byte[] compressed) {
        if (compressed == null) {
            return null;
        }
        String uncompress = null;
        try {
            uncompress = new String(Snappy.uncompress(compressed), "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return uncompress;
    }
}
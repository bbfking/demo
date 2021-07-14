package com.pfxiong.demo.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author: xiongpengfei
 * @datetime: 2021/7/13 18:18
 * @description:
 */
public class GZipUtil {
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    /**
     * 压缩
     *
     * @param s 需要压缩的字符串
     * @return 压缩后的二进制数据
     */
    public static byte[] zip(String s) {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try (GZIPOutputStream gout = new GZIPOutputStream(bout)) {
            gout.write(s.getBytes(DEFAULT_CHARSET));
            gout.close();
            return bout.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 压缩
     *
     * @param data 需要压缩的数据
     * @return 压缩后的二进制数据
     */
    public static byte[] zip(byte[] data) {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try (GZIPOutputStream gout = new GZIPOutputStream(bout)) {
            gout.write(data);
            gout.close();
            return bout.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解压缩
     *
     * @param b 压缩后的二进制数据
     * @return 原始字符串
     */
    public static String unzip(byte[] b) {
        ByteArrayInputStream bin = new ByteArrayInputStream(b);
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try (GZIPInputStream gin = new GZIPInputStream(bin)) {
            byte[] buf = new byte[256];
            int n;
            while ((n = gin.read(buf)) >= 0) {
                bout.write(buf, 0, n);
            }
            return new String(bout.toByteArray(), DEFAULT_CHARSET);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
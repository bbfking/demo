package com.pfxiong.demo.bitmap;

import com.alibaba.fastjson.JSON;
import com.pfxiong.demo.serialization.GZipUtil;
import com.pfxiong.demo.serialization.SnappyUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author: xiongpengfei
 * @datetime: 2021/7/13 17:05
 * @description:
 */
public class Bitmap {

    private byte[] bitmap;

    private int length;

    private final static int DEFAULT_LENGTH = 8;

    public boolean get(int number) {
        //获取位置
        int site = number >>> 3;//等价于 site=number/8
        //获取该字节
        byte temp = bitmap[site];

        //获取该字节的第几个
        int i = number & 7;//等价于 i=number%8
        byte comp = 1;

        //将这个字节数右移(7-i)位（也就是把要查找的位移动到最右侧），然后与 0000 0001相与
        if (((temp >>> (7 - i)) & 1) == 0) {
            return false;
        }
        return true;
    }

    private void set(int number, boolean bool) {
        //获取位置
        int site = number >>> 3;//等价于 site=number/8
        //获取该字节
        byte temp = bitmap[site];

        //获取该字节的第几个
        int i = number & 7;//等价于 i=number%8
        //将0000 0001 左移(7-i)
        byte comp = (byte) (1 << (7 - i));

        if (bool) {//设置为1
            bitmap[site] = (byte) (comp | temp);//取或(0.. 1 0..)
        } else {//设置为0
            comp = (byte) ~comp;//取反
            bitmap[site] = (byte) (comp & temp);//相与(1.. 0 1..)
        }
    }

    public void add(int index) {
        if (index >= length) {
            grow(index);
        }
        set(index, true);
    }

    public void delete(int index) {
        set(index, false);
    }

    public Bitmap(int length) {
        grow(length);
    }

    private void grow(int minLength) {
        if (minLength < length) {
            return;
        }
        int size = (minLength >>> 3) + 1;
        int newSize = size + (size >> 1);
        length = newSize << 3;
        if (bitmap == null) {
            bitmap =  new byte[newSize];
        } else {
            bitmap = Arrays.copyOf(bitmap, newSize);
        }
    }

    public Bitmap() {
        this.length = DEFAULT_LENGTH;
        bitmap = new byte[DEFAULT_LENGTH >>> 3];
    }

    public int getLength() {
        return length;
    }


    public static void main(String[] args) throws IOException {
        List<Bitmap> bitmaps = new ArrayList<>();

        Bitmap bitmap = new Bitmap();
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            int rand = random.nextInt(600000);
            bitmap.add(rand);
            set.add(rand);
        }
        String json = JSON.toJSONString(set);
        FileUtils.writeStringToFile(new File("D:\\bloomfilter\\string"), json, "utf-8");

        FileUtils.writeByteArrayToFile(new File("D:\\bloomfilter\\stringSnappy"), SnappyUtil.compress(json));
        FileUtils.writeByteArrayToFile(new File("D:\\bloomfilter\\stringGzip"), GZipUtil.zip(json));


        FileUtils.writeByteArrayToFile(new File("D:\\bloomfilter\\bitmap"), bitmap.bitmap);
        FileUtils.writeByteArrayToFile(new File("D:\\bloomfilter\\compressbitmap"), SnappyUtil.compress(bitmap.bitmap));
        FileUtils.writeByteArrayToFile(new File("D:\\bloomfilter\\compressGzipbitmap"), GZipUtil.zip(bitmap.bitmap));
    }
}
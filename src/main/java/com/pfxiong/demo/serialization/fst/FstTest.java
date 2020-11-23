package com.pfxiong.demo.serialization.fst;

import org.junit.Test;
import org.nustaq.serialization.FSTConfiguration;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * @author: xiongpengfei
 * @datetime: 2020/11/18 16:58
 * @description:
 */
public class FstTest {

    @Test
    public void output() throws Exception {
        SomeClass object = new SomeClass();
//        object.value = FileUtils.readFileToString(new File("D:\\20112.txt"), "utf-8");
        object.value = "hello java";
        long start = System.currentTimeMillis();
        FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
        for (int i = 0; i < 10000; i++) {
            OutputStream stream = new FileOutputStream("D:\\testfile\\fstFile.bin" + i);
            stream.write(conf.asByteArray(object));
            stream.close();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }

    static public class SomeClass implements Serializable {
        String value;
        int a = 1;
        double b = 5.0D;
    }
}
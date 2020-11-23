package com.pfxiong.demo.serialization.hessian2;

import com.alibaba.com.caucho.hessian.io.Hessian2Output;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.Serializable;

/**
 * @author: xiongpengfei
 * @datetime: 2020/11/18 16:26
 * @description:
 */
public class Hessian2Test {

    @Test
    public void output() throws Exception {
        SomeClass object = new SomeClass();
//        object.value = FileUtils.readFileToString(new File("D:\\20112.txt"), "utf-8");
        object.value = "hello java";
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            Hessian2Output ho = new Hessian2Output(new FileOutputStream("D:\\testfile\\hessian2file.bin" + i));
            ho.writeObject(object);
            ho.close();
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
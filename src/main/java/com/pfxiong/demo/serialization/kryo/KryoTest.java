package com.pfxiong.demo.serialization.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author: xiongpengfei
 * @datetime: 2020/11/18 16:00
 * @description:
 */
public class KryoTest {

    @Test
    public void output () throws Exception {
        Kryo kryo = new Kryo();
        kryo.register(SomeClass.class);
        kryo.register(String.class);
        SomeClass object = new SomeClass();
//        object.value = FileUtils.readFileToString(new File("D:\\20112.txt"), "utf-8");
        object.value = "hello java";
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            Output output = new Output(new FileOutputStream("D:\\testfile\\file.bin" + i));
            kryo.writeObject(output, object);
            output.close();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private void input() throws Exception {
        Kryo kryo = new Kryo();
        kryo.register(SomeClass.class);
        kryo.register(String.class);
        Input input = new Input(new FileInputStream("file.bin"));
        SomeClass object2 = kryo.readObject(input, SomeClass.class);
        input.close();
    }
    static public class SomeClass {
        String value;
        int a = 1;
        double b = 5.0D;
    }
}
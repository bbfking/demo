package com.pfxiong.demo.serialization.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.Test;

/**
 * @author: xiongpengfei
 * @datetime: 2020/11/23 11:21 下午
 * @description:
 */
public class ProtobufTest {

    /**
     *
     * protoc -I=src/main/proto --java_out=src/main/java person.proto 生成protobuf的类文件
     * -I表示源文件目录
     * --java_out表示生成的java文件目录   最后为需要转换的proto文件
     *
     * @throws InvalidProtocolBufferException
     */
    @Test
    public void personTest() throws InvalidProtocolBufferException {
        PersonModel.Person.Builder builder = PersonModel.Person.newBuilder();
        builder.setId(1);
//        builder.setName("pfxiong");
        builder.setEmail("bbfking@gmail.com");

        PersonModel.Person person = builder.build();
        System.out.println("before:" + person);

        System.out.println("===Person Byte:");
        for (byte b : person.toByteArray()) {
            System.out.print(b);
        }
        System.out.println("================");

        byte[] byteArray = person.toByteArray();
        PersonModel.Person p2 = PersonModel.Person.parseFrom(byteArray);
        System.out.println("after id:" + p2.getId());
        System.out.println("after name:" + p2.getName());
        System.out.println("after email:" + p2.getEmail());
    }

}
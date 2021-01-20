package com.pfxiong.demo.serialization.json;

import com.alibaba.fastjson.JSONWriter;
import com.pfxiong.demo.serialization.GameVersion;
import com.pfxiong.demo.serialization.Scene;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: pfXiong
 * @datetime: 2021/1/20 16:20
 * @description:
 */
public class FastjsonTest {

    @Test
    public void testStreamApi() throws IOException, InterruptedException {
        GameVersion gameVersion = new GameVersion();
        gameVersion.setId(1);
        List<Scene> scenes = new ArrayList<>();
        List<String> macros = new ArrayList<>();
//        for (int i = 0; i < 3800000; i++) {
//            Scene scene = new Scene();
//            scene.setId(i);
//            scene.setCreateTime(new Timestamp(System.currentTimeMillis()));
//            scene.setCommand(i + "testsettttt");
//            scenes.add(scene);
////            macros.add(i + "macro");
//        }
        Thread.sleep(20000);
//        System.out.println(JSON.toJSONString(scenes).length()/(1024*1024));
//        StringBuffer sb = new StringBuffer();
//        scenes.forEach(s ->{
//            sb.append(s.getCommand());
//        });
//        StringWriter stringWriter = new StringWriter();
        FileWriter fileWriter = new FileWriter(new File("D://a.txt"));
        JSONWriter writer = new JSONWriter(fileWriter);
        writer.startArray();
        for (int i = 0; i < 10; i++) {
            Scene scene = new Scene();
            scene.setId(i);
            scene.setCreateTime(new Timestamp(System.currentTimeMillis()));
            scene.setCommand(i + "testsettttt");
            writer.writeValue(scene);
        }
        writer.endArray();
        writer.close();
//        System.out.println(stringWriter.toString().length()/(1024*1024));
//        stringWriter.close();
        Thread.sleep(600000);

    }

}
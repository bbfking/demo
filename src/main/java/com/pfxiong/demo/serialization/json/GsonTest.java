package com.pfxiong.demo.serialization.json;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
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
 * @datetime: 2021/1/20 22:26
 * @description:
 */
public class GsonTest {
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
//            macros.add(i + "macro");
//        }
//        Thread.sleep(10000);
//        StringWriter stringWriter = new StringWriter();
        FileWriter fileWriter = new FileWriter(new File("D://c.txt"));
        Gson gson = new Gson();
        try(JsonWriter writer = new JsonWriter(fileWriter)){
            writer.beginArray();
            for (int i = 0; i < 10; i++) {
                Scene scene = new Scene();
                scene.setId(i);
                scene.setCreateTime(new Timestamp(System.currentTimeMillis()));
                scene.setCommand(i + "testsettttt");
                writer.value(gson.toJson(scene));
            }
            writer.endArray();
        }
//        System.out.println(stringWriter.toString().length()/(1024*1024));
        Thread.sleep(600000);

    }
}
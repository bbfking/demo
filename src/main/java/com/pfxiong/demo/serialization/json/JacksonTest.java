package com.pfxiong.demo.serialization.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.pfxiong.demo.serialization.GameVersion;
import com.pfxiong.demo.serialization.Scene;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: pfXiong
 * @datetime: 2021/1/20 17:15
 * @description:
 */
public class JacksonTest {
    @Test
    public void testStreamApi() throws IOException, InterruptedException {
        GameVersion gameVersion = new GameVersion();
        gameVersion.setId(1);
        List<Scene> scenes = new ArrayList<>();
//        List<String> macros = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Scene scene = new Scene();
            scene.setId(i);
            scene.setCreateTime(new Timestamp(System.currentTimeMillis()));
            scene.setCommand(i + "testsettttt");
            scenes.add(scene);
        }
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String rs = jsonMapper.writeValueAsString(scenes);
        ObjectWriter writer = jsonMapper.writer();
        FileWriter fileWriter = new FileWriter(new File("D://b.txt"));
        StringWriter stringWriter = new StringWriter();
        try (SequenceWriter sequenceWriter = writer.writeValues(stringWriter)) {
            sequenceWriter.init(true);
//            sequenceWriter.
            for (int i = 0; i < 10; i++) {
                Scene scene = new Scene();
                scene.setId(i);
                scene.setCommand(i + "testsettttt");
                sequenceWriter.write(scene);
            }

        } catch (Exception e) {

        }
//        System.out.println(stringWriter.toString().length()/(1024*1024));



        Thread.sleep(600000);

    }
}
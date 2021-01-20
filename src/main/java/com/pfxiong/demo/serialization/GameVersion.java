package com.pfxiong.demo.serialization;

/**
 * @author: pfXiong
 * @datetime: 2021/1/20 16:22
 * @description:
 */
public class GameVersion {
    private Integer id;

    private String sceneJson;

    private String macroJson;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSceneJson() {
        return sceneJson;
    }

    public void setSceneJson(String sceneJson) {
        this.sceneJson = sceneJson;
    }

    public String getMacroJson() {
        return macroJson;
    }

    public void setMacroJson(String macroJson) {
        this.macroJson = macroJson;
    }
}
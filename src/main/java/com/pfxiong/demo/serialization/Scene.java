package com.pfxiong.demo.serialization;

import java.sql.Timestamp;

/**
 * @author: pfXiong
 * @datetime: 2021/1/20 16:21
 * @description:
 */
public class Scene {
    private Integer id;

    private String command;

    private Timestamp createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
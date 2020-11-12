package com.shop.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ChenTengfei
 * @date 2019/8/14 18:58
 **/
public class LoggingInfoBO implements Serializable {

    private static final long serialVersionUID = 4726136735529378592L;

    /**
     * 日志属性
     */
    private String type;

    /**
     * 日志内容
     */
    private String text;
    /**
     * 日志时间
     */
    private Date time;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "LoggingInfoBO{" +
                "type='" + type + '\'' +
                ", text='" + text + '\'' +
                ", time=" + time +
                '}';
    }
}
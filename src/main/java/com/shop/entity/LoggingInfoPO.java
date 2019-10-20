package com.shop.entity;

import java.io.Serializable;

/**
 * @author ChenTengfei
 * @date 2019/8/14 19:26
 **/
public class LoggingInfoPO implements Serializable {
    private static final long serialVersionUID = -9055740983864987899L;
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
    private String time;

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "LoggingInfoPO{" +
                "type='" + type + '\'' +
                ", text='" + text + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
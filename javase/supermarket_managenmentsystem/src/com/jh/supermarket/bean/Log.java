package com.jh.supermarket.bean;

import com.jh.supermarket.util.IdUtil;

import java.io.Serializable;
import java.util.Date;

public class Log implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private String description;
    private Date date;
    private boolean success;
    private int logType;

    public Log() {
        this.date = new Date();
        this.id = IdUtil.getLogId();
    }

    public int getLogType() {
        return logType;
    }

    public void setLogType(int logType) {
        this.logType = logType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

package com.stickerwall.entity;

import java.sql.Timestamp;
import java.util.Date;

public class Replies extends IdEntity {
    private Long stickerId;    //主题ID
    private Long userId;     //回帖人ID
    private String repliesContent;   //回帖内容
    private Timestamp repliesTime = new Timestamp(new Date().getTime());//回复时间
    private int state;   //使用状态

    public Long getStickerId() {
        return stickerId;
    }

    public void setStickerId(Long stickerId) {
        this.stickerId = stickerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRepliesContent() {
        return repliesContent;
    }

    public void setRepliesContent(String repliesContent) {
        this.repliesContent = repliesContent;
    }

    public Timestamp getRepliesTime() {
        return repliesTime;
    }

    public void setRepliesTime(Timestamp repliesTime) {
        this.repliesTime = repliesTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Replies{" +
                "stickerId=" + stickerId +
                ", userId=" + userId +
                ", repliesContent='" + repliesContent + '\'' +
                ", repliesTime=" + repliesTime +
                ", state=" + state +
                ", id=" + id +
                '}';
    }
}
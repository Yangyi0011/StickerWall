package com.stickerwall.entity;

import java.sql.Timestamp;
import java.util.Date;

public class Sticker extends IdEntity {
    private long userId;     //发帖人ID
    private String stickerTitle; //标题
    private String stickerContent;  //发帖内容
    private Timestamp releaseTime = new Timestamp(new Date().getTime());//发帖时间
    private int clickCount;  //点击数
    private int praiseCount; //点赞数
    private int replyCount;  //回复数
    private Timestamp lastReplyTime;//最后回复时间
    private int state;      //使用状态

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getStickerTitle() {
        return stickerTitle;
    }

    public void setStickerTitle(String stickerTitle) {
        this.stickerTitle = stickerTitle;
    }

    public String getStickerContent() {
        return stickerContent;
    }

    public void setStickerContent(String stickerContent) {
        this.stickerContent = stickerContent;
    }

    public Timestamp getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Timestamp releaseTime) {
        this.releaseTime = releaseTime;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    public int getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(int praiseCount) {
        this.praiseCount = praiseCount;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public Timestamp getLastReplyTime() {
        return lastReplyTime;
    }

    public void setLastReplyTime(Timestamp lastReplyTime) {
        this.lastReplyTime = lastReplyTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Sticker{" +
                "userId=" + userId +
                ", stickerTitle='" + stickerTitle + '\'' +
                ", stickerContent='" + stickerContent + '\'' +
                ", releaseTime=" + releaseTime +
                ", clickCount=" + clickCount +
                ", praiseCount=" + praiseCount +
                ", replyCount=" + replyCount +
                ", lastReplyTime=" + lastReplyTime +
                ", state=" + state +
                ", id=" + id +
                '}';
    }
}
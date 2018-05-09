package com.stickerwall.entity;

//点赞实体，对应数据库点赞表
public class Praise extends IdEntity {
    private Long stickerId;
    private String praiseUserIds;

    public Long getStickerId() {
        return stickerId;
    }

    public void setStickerId(Long stickerId) {
        this.stickerId = stickerId;
    }

    public String getPraiseUserIds() {
        return praiseUserIds;
    }

    public void setPraiseUserIds(String praiseUserIds) {
        this.praiseUserIds = praiseUserIds;
    }

    @Override
    public String toString() {
        return "Praise{" +
                "stickerId=" + stickerId +
                ", praiseUserIds='" + praiseUserIds + '\'' +
                ", id=" + id +
                '}';
    }
}
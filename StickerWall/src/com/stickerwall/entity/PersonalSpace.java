package com.stickerwall.entity;

public class PersonalSpace extends IdEntity {
    private String spaceName;   //空间名
    private Long userID;      //所属用户
    private int visitCount;     //空间访问量
    private int state;          //使用状态

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "PersonalSpace{" +
                "spaceName='" + spaceName + '\'' +
                ", userID=" + userID +
                ", visitCount=" + visitCount +
                ", state=" + state +
                ", id=" + id +
                '}';
    }
}
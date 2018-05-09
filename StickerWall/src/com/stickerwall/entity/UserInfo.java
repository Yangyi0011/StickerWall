package com.stickerwall.entity;

import java.sql.Timestamp;
import java.util.Date;

public class UserInfo extends IdEntity {
    private String userName;    //账号
    private String nickName;     //昵称
    private Long userId;    //对应用户的ID
    private String headPortrait;      //头像
    private String sex;      //性别
    //    对于时间类型需要作出相应转换，否则插入数据库时会出错
    private java.util.Date birthday;    //生日
    private String address;      //地址
    private int EXP;     //经验
    private int grade;       //等级
    private String motto;        //个性签名
    private Timestamp lastLoginTime;//最后登录时间
    private Timestamp registrationTime;//注册时间
    private int state;      //使用状态

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getEXP() {
        return EXP;
    }

    public void setEXP(int EXP) {
        this.EXP = EXP;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Timestamp getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Timestamp registrationTime) {
        this.registrationTime = registrationTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", userId=" + userId +
                ", headPortrait='" + headPortrait + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", EXP=" + EXP +
                ", grade=" + grade +
                ", motto='" + motto + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", registrationTime=" + registrationTime +
                ", state=" + state +
                ", id=" + id +
                '}';
    }
}
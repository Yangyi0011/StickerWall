package com.stickerwall.service;

import com.stickerwall.dao.impl.UserInfoDaoImpl;
import com.stickerwall.entity.User;
import com.stickerwall.entity.UserInfo;
import com.stickerwall.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserInfoService {
    private UserInfoDaoImpl userInfoDao = new UserInfoDaoImpl();
    private UserService userService = new UserService();

    /**
     * 获取数据记录条数总数
     * @return
     */
    public int getAllRowTotal(){
        Connection conn = null;
        ResultSet res = null;
        int rowTotal = 0;

        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);

            res = userInfoDao.getAllRowTotal(conn);
            if(res.next()){
                rowTotal = res.getInt(1);
            }

            conn.commit();

            return rowTotal;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rowTotal;
    }

    /**
     * 通过UserId获取UserInfo
     * @param userId
     * @return
     */
    public UserInfo getUserInfoByUserId(Long userId){
        Connection conn = null;
        UserInfo userInfo = new UserInfo();

        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);

            userInfo = userInfoDao.getUserInfoByUserId(conn, userId);
            conn.commit();

            return userInfo;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userInfo;
    }

    /**
     * 通过UserName获取UserInfo
     * @param userName
     * @return
     * @throws SQLException
     */
    public UserInfo getUserInfoByUserName(String userName){

        //通过userName去获取userId,再通过userId去获取UserInfo
        User user = userService.getUserByName(userName);
        UserInfo userInfo = getUserInfoByUserId(user.getId());
        return userInfo;
    }

    /**
     * 分页获取用户信息
     * @param startIndex：从第几条开始获取
     * @param pageSize：获取多少条，即每页显示多少条
     * @return
     */
    public List<UserInfo> getUserInfoWithPage( String searchContent, String order, int startIndex, int pageSize){
        Connection conn = null;
        ResultSet res = null;

        List<UserInfo> userInfoList = new ArrayList<>();

        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);

            res = userInfoDao.getUserInfoWithPage(conn,searchContent,order,startIndex, pageSize);
            while (res.next()){
                UserInfo userInfo = new UserInfo();

                userInfo.setUserName(res.getString("userName"));
                userInfo.setNickName(res.getString("nickName"));    //昵称
                userInfo.setUserId(res.getLong("userId"));  //对应的用户ID
                userInfo.setHeadPortrait(res.getString("headPortrait"));   //头像
                userInfo.setSex(res.getString("sex"));  //性别

                if (res.getDate("birthday") != null){
                    userInfo.setBirthday(new java.util.Date(res.getDate("birthday").getTime()));  //生日
                }
                userInfo.setAddress(res.getString("address"));  //地址
                userInfo.setEXP(res.getInt("EXP")); //经验
                userInfo.setGrade(res.getInt("grade")); //等级
                userInfo.setMotto(res.getString("motto"));  //个性签名
                userInfo.setLastLoginTime(res.getTimestamp("lastLoginTime")); //最后登录时间
                userInfo.setRegistrationTime(res.getTimestamp("registrationTime")); //注册时间
                userInfo.setState(res.getInt("state")); //使用状态
                userInfo.setId(res.getLong("id"));  //id

                userInfoList.add(userInfo);
            }

            conn.commit();

            return userInfoList;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userInfoList;
    }

    public void updateUserInfo( Long userId, UserInfo userInfo){
        Connection conn = null;

        try {
            conn = ConnectionFactory.getInstance().makeConnection();

            conn.setAutoCommit(false);
            userInfoDao.updateUserInfo(conn, userId,userInfo);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteUserInfo( User user){
        Connection conn = null;

        try {
            conn = ConnectionFactory.getInstance().makeConnection();

            conn.setAutoCommit(false);
            userInfoDao.deleteUserInfo(conn, user);

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
} 
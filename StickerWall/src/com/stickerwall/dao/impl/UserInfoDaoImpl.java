package com.stickerwall.dao.impl;

import com.stickerwall.dao.UserInfoDao;
import com.stickerwall.entity.User;
import com.stickerwall.entity.UserInfo;
import com.stickerwall.util.DateAndTime;

import java.sql.*;

public class UserInfoDaoImpl implements UserInfoDao {

    /**
     * 获取所有记录条数
     * @param conn
     * @return
     * @throws SQLException
     */
    public ResultSet getAllRowTotal(Connection conn) throws SQLException {
        String sql = " Select Count(*) From userinfo ;";
        PreparedStatement ps = conn.prepareStatement(sql);

        return ps.executeQuery();
    }

    /**
     *分页获取用户信息
     * @param connection
     * @param startIndex:从第几条开始获取用户信息
     * @param pageSize: 需要获取多少条数据
     * @return
     * @throws SQLException
     */
    public ResultSet getUserInfoWithPage(Connection connection, String searchContent, String order,
                                         int startIndex, int pageSize) throws SQLException {
        String sql = " Select * From userinfo ";

        if (searchContent != null && !searchContent.equals("")){
            sql += " Where userName Like '%"+searchContent+"%' Or nickName Like '%"+searchContent+"%'  Or sex Like '%"+searchContent+"%' Or state Like '%"+searchContent+"%'";
        }

        if(order != null && !order.equals("")){
            sql += " Order By " +order+ " DESC limit ?, ?";
        }else {
            sql += " Order By grade DESC , EXP DESC limit ?, ? ";
        }
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, startIndex);
        ps.setInt(2, pageSize);

        return ps.executeQuery();
    }

    /**
     * 添加用户信息
     * @param conn
     * @param user
     * @throws SQLException
     */
    @Override
    public void addUserInfo(Connection conn, User user) throws SQLException {
        String sql = " Insert Into userinfo ( userName,nickName, userId, lastLoginTime, registrationTime) Values (?,?,?,?,?);";

        PreparedStatement ps = conn.prepareCall(sql);
        ps.setString(1, user.getName());
        ps.setString(2, user.getName());  //默认用户昵称为用户的账号
        ps.setLong(3, user.getId());     //添加对应用户id
        ps.setTimestamp(4, DateAndTime.getDateTimeForSql());    //添加最后登录时间
        ps.setTimestamp(5, DateAndTime.getDateTimeForSql());    //添加注册时间

//        其他用户信息均为默认
        ps.execute();
    }

    /**
     * 更新用户信息
     * @param conn
     * @param userId
     * @param userInfo
     * @throws SQLException
     */
    @Override
    public void updateUserInfo(Connection conn, Long userId, UserInfo userInfo) throws SQLException {
        String sql = " Update userinfo SET nickName = ?, headPortrait = ?,sex = ?, birthday = ?, address = ?, EXP = ?," +
                " grade =?, motto = ?, lastLoginTime = ?, state = ? Where userId = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, userInfo.getNickName());
        ps.setString(2, userInfo.getHeadPortrait());
        ps.setString(3, userInfo.getSex());
        if(userInfo.getBirthday() != null && !(userInfo.getBirthday().toString()).equals("")){
            ps.setDate(4, DateAndTime.strToDate(userInfo.getBirthday().toString()));
        }else {
            ps.setDate(4, null);
        }
        ps.setString(5, userInfo.getAddress());
        ps.setInt(6, userInfo.getEXP());
        ps.setInt(7, userInfo.getGrade());
        ps.setString(8, userInfo.getMotto());
        ps.setTimestamp(9, userInfo.getLastLoginTime());
        ps.setInt(10, userInfo.getState());
        ps.setLong(11, userId);

        ps.execute();
    }

    /**
     * 通过用户ID来删除用户的信息
     * @param conn
     * @param user
     * @throws SQLException
     */
    @Override
    public void deleteUserInfo(Connection conn, User user) throws SQLException {
        String sql = " Delete From userinfo Where userId = ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1,user.getId());

        ps.execute();
    }

    /**
     * 通过用户ID来获取用户信息
     * @param conn
     * @param userId
     * @return
     * @throws SQLException
     */
    public UserInfo getUserInfoByUserId(Connection conn, Long userId) throws SQLException {
        String sql = " Select * From userinfo Where userId =? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, userId);
        UserInfo userInfo = new UserInfo();
        ResultSet res = ps.executeQuery();
        //        提取用户信息
        while (res.next()){
            userInfo.setUserName(res.getString("userName"));
            userInfo.setNickName(res.getString("nickName"));    //昵称
            userInfo.setUserId(res.getLong("userId"));  //对应的用户ID
            userInfo.setHeadPortrait(res.getString("headPortrait"));   //头像
            userInfo.setSex(res.getString("sex"));  //性别
            userInfo.setBirthday(res.getDate("birthday"));  //生日
            userInfo.setAddress(res.getString("address"));  //地址
            userInfo.setEXP(res.getInt("EXP")); //经验
            userInfo.setGrade(res.getInt("grade")); //等级
            userInfo.setMotto(res.getString("motto"));  //个性签名
            userInfo.setLastLoginTime(res.getTimestamp("lastLoginTime")); //最后登录时间
            userInfo.setRegistrationTime(res.getTimestamp("registrationTime")); //注册时间
            userInfo.setState(res.getInt("state")); //使用状态
            userInfo.setId(res.getLong("id"));  //id
        }

        return userInfo;
    }
}
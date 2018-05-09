package com.stickerwall.dao;

import com.stickerwall.entity.User;
import com.stickerwall.entity.UserInfo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserInfoDao {
    public ResultSet getAllRowTotal(Connection conn) throws SQLException;
    public ResultSet getUserInfoWithPage(Connection connection, String searchContent, String order, int startIndex, int pageSize) throws SQLException;
    public void addUserInfo(Connection conn, User user) throws SQLException;
    public void updateUserInfo(Connection conn, Long userId, UserInfo userInfo) throws  SQLException;
    public void deleteUserInfo(Connection conn, User user) throws  SQLException;
    public UserInfo getUserInfoByUserId(Connection conn, Long userId) throws SQLException;
}
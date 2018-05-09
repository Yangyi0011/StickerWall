package com.stickerwall.dao;

import com.stickerwall.entity.PersonalSpace;
import com.stickerwall.entity.Replies;
import com.stickerwall.entity.Sticker;
import com.stickerwall.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//UserDao接口
public interface UserDao {
    public void save(Connection conn, User user) throws SQLException;
    public ResultSet get(Connection conn, User user) throws SQLException;
    public ResultSet getAllUser(Connection conn) throws SQLException;
    public ResultSet checkUserByName(Connection conn, String userName) throws SQLException;
    public void update(Connection conn, Long id, User user) throws  SQLException;
    public void delete(Connection conn, User user) throws  SQLException;
    public User getUserByName(Connection conn, String userName) throws SQLException;
    public User getUserById(Connection conn, Long id) throws SQLException;
    public User getUserByReplies(Connection conn, Replies replies) throws SQLException;
    public User getUserBySticker(Connection conn, Sticker sticker) throws SQLException;
}
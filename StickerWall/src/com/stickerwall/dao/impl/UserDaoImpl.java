package com.stickerwall.dao.impl;

import com.stickerwall.dao.UserDao;
import com.stickerwall.entity.PersonalSpace;
import com.stickerwall.entity.Replies;
import com.stickerwall.entity.Sticker;
import com.stickerwall.entity.User;
import com.stickerwall.service.PersonalSpaceService;
import com.stickerwall.service.UserInfoService;
import com.stickerwall.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    public void save(Connection conn, User user) throws SQLException {
        UserInfoDaoImpl userInfoDao = new UserInfoDaoImpl();
        PersonalSpaceDaoImpl spaceDao = new PersonalSpaceDaoImpl();
        conn.setAutoCommit(false);

//                1.添加用户账号和密码
//                2.通过刚刚添加的用户账号去获取用户
//                3.添加用户一些默认信息
//                4.添加用户空间
//            用户在注册的时候只有用户名和密码，是没有userId的
//            但以下操作需要userId才能完成，所以必须先找出userId
        add(conn, user);

        User thisUser = getUserByName(conn, user.getName());

        userInfoDao.addUserInfo(conn, thisUser);

        spaceDao.add(conn, thisUser);

        conn.commit();
    }

    /**
     * 添加用户的账号和密码
     * 必须与添加用户信息的方法一起使用
     *
     * @param conn
     * @param user
     * @throws SQLException
     */
    private void add(Connection conn, User user) throws SQLException {
        String insertSql = "Insert Into user(name, password) VALUES (?,?);";
        PreparedStatement ps = conn.prepareCall(insertSql);

//        此处需注意：索引是从1开始的
        ps.setString(1, user.getName());
        ps.setString(2, user.getPassword());

//        保存到数据库
        ps.execute();
    }

    /**
     * 根据用户指定的ID更新用户
     *
     * @param conn
     * @param id
     * @param user
     * @throws SQLException
     */
    @Override
    public void update(Connection conn, Long id, User user) throws SQLException {
        String updateSql = "Update user Set name = ?, password = ? where id = ?;";
        PreparedStatement ps = conn.prepareStatement(updateSql);
        ps.setString(1, user.getName());
        ps.setString(2, user.getPassword());
        ps.setLong(3, id);

        ps.execute();
    }

    /**
     * 通过ID删除用户及其所有信息
     *
     * @param conn
     * @param user
     * @throws SQLException
     */
    @Override
    public void delete(Connection conn, User user) {
        try {
//            关闭事务自动提交
            conn.setAutoCommit(false);
            // 1.通过用户的账号去获取其对应的用户信息
            // 2.删除用户的信息
            // 3.删除用户空间
            // 4.删除用户账号和密码

            UserInfoDaoImpl userInfoDao = new UserInfoDaoImpl();
//            删除用户信息
            userInfoDao.deleteUserInfo(conn, user);

            PersonalSpaceDaoImpl spaceDao = new PersonalSpaceDaoImpl();
            PersonalSpace space = spaceDao.getSpaceByUserId(conn, user.getId());
//            删除用户空间
            spaceDao.delete(conn, space);

//            删除用户账号密码
            deleteUser(conn, user);

            conn.commit();      //提交事务

        } catch (SQLException e) {
            e.printStackTrace();
            try {
//                出现异常时进行回滚
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 只删除用户账号和密码。
     * 注：用户信息、用户空间没有删除时，不能执行此方法！
     *
     * @param conn
     * @param user
     * @throws SQLException
     */
    private void deleteUser(Connection conn, User user) throws SQLException {
        String deleteSql = "Delete From user Where id = ?;";
        PreparedStatement ps = conn.prepareStatement(deleteSql);
        ps.setLong(1, user.getId());
        ps.execute();
    }


    /**
     * 通过用户账号来获取用户
     *
     * @param conn
     * @param userName
     * @return
     * @throws SQLException
     */
    public User getUserByName(Connection conn, String userName) throws SQLException {
        String sql = "Select * From user Where name = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, userName);

        User user = new User();
        ResultSet res = ps.executeQuery();
        while (res.next()) {
            user.setId(res.getLong("id"));
            user.setName(res.getString("name"));
            user.setPassword(res.getString("password"));
        }
        return user;
    }

    /**
     * 通过用户ID来获取用户
     *
     * @param conn
     * @param id
     * @return
     * @throws SQLException
     */
    public User getUserById(Connection conn, Long id) throws SQLException {
        String sql = "Select * From user Where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);

        User user = new User();
        ResultSet res = ps.executeQuery();
        while (res.next()) {
            user.setId(res.getLong("id"));
            user.setName(res.getString("name"));
            user.setPassword(res.getString("password"));
        }
        return user;
    }

    /**
     * 获取用户信息
     * 返回ResultSet类型
     *
     * @param conn
     * @param user
     * @return
     * @throws SQLException
     */
    @Override
    public ResultSet get(Connection conn, User user) throws SQLException {
        String sql = "Select * From user Where name = ? And password = ? ;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, user.getName());
        ps.setString(2, user.getPassword());

        return ps.executeQuery();
    }

    /**
     * 获取所有用户
     * @param conn
     * @return
     */
    public ResultSet getAllUser(Connection conn) throws SQLException {
        String sql = " Select * from user";
        PreparedStatement ps = conn.prepareStatement(sql);

        return ps.executeQuery();
    }

    /**
     * 通过用户名验证用户是否存在
     * 返回ResultSet类型
     *
     * @param conn
     * @param userName
     * @return
     * @throws SQLException
     */
    public ResultSet checkUserByName(Connection conn, String userName) throws SQLException {
        String sql = "Select * From user Where name = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, userName);

        return ps.executeQuery();
    }

    /**
     * 通过回复的帖子获取回复人
     *
     * @param conn
     * @param replies
     * @return
     * @throws SQLException
     */
    public User getUserByReplies(Connection conn, Replies replies) throws SQLException {
        String sql = " Select userId From replies Where id = ? ; ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, replies.getId());

        ResultSet res = ps.executeQuery();

        Long userId = null;
        if (res.next()) {
            userId = res.getLong("userId");
        }

        UserDaoImpl userDao = new UserDaoImpl();

//        通过userId获取user
        return userDao.getUserById(conn, userId);
    }

    /**
     * 通过发布的帖子获取发布人
     *
     * @param conn
     * @param sticker
     * @return
     * @throws SQLException
     */
    public User getUserBySticker(Connection conn, Sticker sticker) throws SQLException {
        String sql = " Select userId From sticker Where id = ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, sticker.getId());

        ResultSet res = ps.executeQuery();

        Long userId = null;
        if (res.next()) {
            userId = res.getLong("userId");
        }
        UserDaoImpl userDao = new UserDaoImpl();

//        通过userId获取user
        return userDao.getUserById(conn, userId);
    }

}
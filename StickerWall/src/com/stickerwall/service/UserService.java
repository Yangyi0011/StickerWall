package com.stickerwall.service;

import com.stickerwall.dao.impl.*;
import com.stickerwall.entity.PersonalSpace;
import com.stickerwall.entity.Replies;
import com.stickerwall.entity.Sticker;
import com.stickerwall.entity.User;
import com.stickerwall.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private UserDaoImpl userDao = new UserDaoImpl();
    StickerService stickerService = new StickerService();
    RepliesService repliesService = new RepliesService();

    //获取所有用户
    public List<User> getAllUser(){
        Connection conn = null;
        ResultSet res = null;
        List<User> userList = new ArrayList<>();

        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);

            res = userDao.getAllUser(conn);
            while (res.next()){
                User user = new User();

                user.setId(res.getLong("id"));
                user.setName(res.getString("name"));
                user.setPassword(res.getString("password"));

                userList.add(user);
            }
            conn.commit();

            return userList;
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
        return userList;
    }

    /**
     * 通过用户名获取用户
     * @param userName
     * @return
     */
    public User getUserByName(String userName) {
        Connection conn = null;
        User user = null;
        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);
            user = userDao.getUserByName(conn, userName);
            conn.commit();

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return user;
    }

    /**
     * 通过用户ID获取用户
     * @param id
     * @return
     */
    public User getUserById(Long id) {
        Connection conn = null;
        User user = new User();

        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);
            user = userDao.getUserById(conn, id);
            conn.commit();

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
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

        return user;
    }

    /**
     * 通过主贴获取用户
     * @param sticker
     * @return
     */
    public User getUserBySticker(Sticker sticker) {
        Connection conn = null;
        User user = new User();

        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);
            user = userDao.getUserBySticker(conn, sticker);
            conn.commit();

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
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
        return user;
    }

    /**
     * 通过回帖获取用户
     * @param replies
     * @return
     */
    public User getUserByReplies(Replies replies) {
        Connection conn = null;
        User user = new User();

        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);
            user = userDao.getUserByReplies(conn,replies);
            conn.commit();

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
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
        return user;
    }

    /**
     * 通过空间获取用户
     * @param space
     * @return
     */
    public User getUserByPersonalSpace(PersonalSpace space){
        Connection conn = null;
        User user = new User();

        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);
            user = userDao.getUserById(conn,space.getUserID());

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
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
        return user;
    }
    /**
     * 保存新用户
     * @param user
     * @throws SQLException
     */
    public void save(User user) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionFactory.getInstance().makeConnection();
//                关闭事物的自动提交
            conn.setAutoCommit(false);
            userDao.save(conn, user);
            conn.commit();
        } catch (SQLException e) {

            e.printStackTrace();
//            有异常就回滚
            conn.rollback();
        }
    }
}
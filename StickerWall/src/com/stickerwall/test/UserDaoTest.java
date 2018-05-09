package com.stickerwall.test;

import com.stickerwall.dao.UserDao;
import com.stickerwall.dao.impl.RepliesDaoImpl;
import com.stickerwall.dao.impl.StickerDaoImpl;
import com.stickerwall.dao.impl.UserDaoImpl;
import com.stickerwall.dao.impl.UserInfoDaoImpl;
import com.stickerwall.entity.Replies;
import com.stickerwall.entity.Sticker;
import com.stickerwall.entity.User;
import com.stickerwall.service.*;
import com.stickerwall.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoTest {
    private static UserService userService = new UserService();
    private static PersonalSpaceService spaceService = new PersonalSpaceService();
    private static StickerService stickerService = new StickerService();
    private static RepliesService repliesService = new RepliesService();

    public static void main(String[] args) {
        //            通过连接工厂获取一个连接实例
        Connection conn = ConnectionFactory.getInstance().makeConnection();

//        saveTest(conn);
//        updateTest(conn);
//        deleteTest(conn);
//        getUserByNameTset();
        getUserByIdTest();
//        try {
//            System.out.println(selectTest(conn));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        getUserByStickerTest(conn);

//        getAllSticker(conn);
//        getAllRepliesTest(conn);
//        getAllStickerIdsTest(conn);
//        getAllRepliesIdsTest(conn);
    }


    //    测试添加
    public static void saveTest(Connection conn) {
        try {
//            关闭事物的自动提交
            conn.setAutoCommit(false);

            User Tom = new User();
            Tom.setName("Tom");
            Tom.setPassword("root");
            userService.save(Tom);
//            提交事物
            conn.commit();

        } catch (SQLException e) {
//            如果有异常则进行回滚
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    //  测试更新
    public static void updateTest(Connection conn) {
        try {
//            关闭事物的自动提交
            conn.setAutoCommit(false);

            UserDao userDao = new UserDaoImpl();
            User som = userService.getUserByName("yangyi");
            som.setName("yangyi");
            som.setPassword("rootroot");
            userDao.update(conn, som.getId(), som);

//            提交事物
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
//            如果有异常则进行回滚
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    //    测试删除
    public static void deleteTest(Connection conn) {
        try {
//            关闭事物的自动提交
            conn.setAutoCommit(false);

            UserDaoImpl userDao = new UserDaoImpl();
            User Tom = userService.getUserByName("Som");
            userDao.delete(conn, Tom);

//            提交事物
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
//            如果有异常则进行回滚
            try {
                conn.rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
    }

    //    测试查找
    public static boolean selectTest(Connection conn) throws SQLException {

        conn.setAutoCommit(false);
        String Tom = "Tom";
        UserDaoImpl userDao = new UserDaoImpl();
        ResultSet res = userDao.checkUserByName(conn, Tom);
        if (res.next()) {
            return true;
        } else {
            return false;
        }
    }

    public static void getUserByStickerTest() {
        Sticker sticker = null;
        User user = null;

        sticker = stickerService.getStickerByStickerId(Long.valueOf(4));
        System.out.println(sticker.toString());

        user = userService.getUserBySticker(sticker);
        System.out.println(user.toString());
    }

    public static void getUserByRepliesTest(Connection conn) {

        RepliesDaoImpl repliesDao = new RepliesDaoImpl();
        Replies replies = null;
        User user = null;

        try {
            replies = repliesDao.getRepliesByRepliesId(conn, Long.valueOf(1));
            user = userService.getUserByReplies(replies);

            System.out.println(replies.toString());
            System.out.println(user.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getUserByNameTset(){
        UserService userService = new UserService();
        User user = userService.getUserByName("yangyi");
        System.out.println(user.toString());
    }
    public static void getUserByIdTest(){
        UserService userService = new UserService();
        User user = userService.getUserById(Long.valueOf(40));
        System.out.println(user.toString());
    }
}
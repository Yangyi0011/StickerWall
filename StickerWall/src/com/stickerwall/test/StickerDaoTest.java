package com.stickerwall.test;

import com.stickerwall.dao.impl.StickerDaoImpl;
import com.stickerwall.dao.impl.UserDaoImpl;
import com.stickerwall.entity.Sticker;
import com.stickerwall.entity.User;
import com.stickerwall.service.PersonalSpaceService;
import com.stickerwall.service.RepliesService;
import com.stickerwall.service.StickerService;
import com.stickerwall.service.UserService;
import com.stickerwall.util.ConnectionFactory;
import com.stickerwall.util.DateAndTime;

import java.sql.Connection;
import java.sql.SQLException;

public class StickerDaoTest {
    private static UserService userService = new UserService();
    private static PersonalSpaceService spaceService = new PersonalSpaceService();
    private static StickerService stickerService = new StickerService();
    private static RepliesService repliesService = new RepliesService();

    public static void main(String[] args) {
        Connection conn = ConnectionFactory.getInstance().makeConnection();

//        addTest(conn);
//        updateTest(conn);
        deleteTest(conn);
//        getStickerIdsTest(conn);
//        getStickerbyStickerIdTser();
    }

    public static void addTest(Connection conn) {
        User user = null;
        user = userService.getUserByName("yangyi");

        Sticker sticker = new Sticker();
        sticker.setStickerTitle("哈哈哈哈啊");
        sticker.setStickerContent("若不是离愁，又怎会晕上眉头？！");
        sticker.setReleaseTime(DateAndTime.getDateTimeForSql());
        sticker.setLastReplyTime(DateAndTime.getDateTimeForSql());

        StickerDaoImpl stickerDao = new StickerDaoImpl();
        try {
            stickerDao.add(conn, user, sticker);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateTest(Connection conn) {

        StickerDaoImpl stickerDao = new StickerDaoImpl();
        Sticker sticker = null;
        sticker = stickerService.getStickerByStickerId(Long.valueOf(8));

        sticker.setClickCount(666);
        sticker.setPraiseCount(99);
        sticker.setLastReplyTime(DateAndTime.getDateTimeForSql());

        try {
            stickerDao.update(conn, sticker.getId(), sticker);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteTest(Connection conn) {
        StickerDaoImpl stickerDao = new StickerDaoImpl();

        try {
            Sticker sticker = stickerService.getStickerByStickerId(Long.valueOf(21));
            stickerDao.delete(conn, sticker);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getStickerbyStickerIdTser() {

        Sticker sticker = stickerService.getStickerByStickerId(Long.valueOf(8));

        System.out.println(sticker.toString());
    }
} 
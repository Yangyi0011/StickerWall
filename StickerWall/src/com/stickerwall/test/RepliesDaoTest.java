package com.stickerwall.test;

import com.stickerwall.dao.impl.RepliesDaoImpl;
import com.stickerwall.dao.impl.StickerDaoImpl;
import com.stickerwall.dao.impl.UserDaoImpl;
import com.stickerwall.entity.Replies;
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

public class RepliesDaoTest {
    private static UserService userService = new UserService();
    private static PersonalSpaceService spaceService = new PersonalSpaceService();
    private static StickerService stickerService = new StickerService();
    private static RepliesService repliesService = new RepliesService();

    public static void main(String[] args) {
        Connection connection = ConnectionFactory.getInstance().makeConnection();

        addTest(connection);
//        deleteTest(connection);
//        getRepliesIdsByStickerTest(connection);
    }

    public static void addTest(Connection conn) {

        UserDaoImpl userDao = new UserDaoImpl();
        StickerDaoImpl stickerDao = new StickerDaoImpl();
        RepliesDaoImpl repliesDao = new RepliesDaoImpl();

        try {
            User user = userService.getUserByName("yangyi");
            Sticker sticker = stickerService.getStickerByStickerId(Long.valueOf(8));
            Replies replies = new Replies();
            replies.setRepliesContent("当程序员太苦逼了！");
            replies.setRepliesTime(DateAndTime.getDateTimeForSql());

            repliesDao.add(conn, user, sticker, replies);

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void deleteTest(Connection conn) {
        RepliesDaoImpl repliesDao = new RepliesDaoImpl();

        try {
            Replies replies = repliesDao.getRepliesByRepliesId(conn, Long.valueOf(13));
            repliesDao.delete(conn, replies);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
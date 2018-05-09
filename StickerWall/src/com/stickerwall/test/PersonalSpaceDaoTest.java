package com.stickerwall.test;

import com.stickerwall.dao.impl.PersonalSpaceDaoImpl;
import com.stickerwall.dao.impl.UserDaoImpl;
import com.stickerwall.entity.PersonalSpace;
import com.stickerwall.entity.User;
import com.stickerwall.service.PersonalSpaceService;
import com.stickerwall.service.RepliesService;
import com.stickerwall.service.StickerService;
import com.stickerwall.service.UserService;
import com.stickerwall.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class PersonalSpaceDaoTest {
    private static UserService userService = new UserService();
    private static PersonalSpaceService spaceService = new PersonalSpaceService();
    private static StickerService stickerService = new StickerService();
    private static RepliesService repliesService = new RepliesService();

    public static void main(String[] args) {
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        updateTest(conn);
//        getSpaceByUserIdTest(conn);
//        getUserByPersonalSpaceTest(conn);
    }

    public static void updateTest(Connection conn) {
        PersonalSpaceDaoImpl spaceDao = new PersonalSpaceDaoImpl();
        try {
            conn.setAutoCommit(false);

            User user = userService.getUserByName("yangyi");
            PersonalSpace space = spaceService.getSpaceByUserId(user.getId());

            space.setSpaceName("程序员");
            spaceDao.update(conn, user, space);

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void getSpaceByUserIdTest(Connection conn) {

        User user = userService.getUserByName("yangyi");
        PersonalSpace space = spaceService.getSpaceByUserId(user.getId());
        System.out.println(space.toString());

    }

    public static void getUserByPersonalSpaceTest(Connection conn) {
        PersonalSpaceDaoImpl spaceDao = new PersonalSpaceDaoImpl();
        PersonalSpace space = spaceService.getSpaceBySpaceId(Long.valueOf(2));
        User user = userService.getUserByPersonalSpace(space);

        System.out.println(user.toString());
    }
} 
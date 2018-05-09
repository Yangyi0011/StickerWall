package com.stickerwall.test;

import com.stickerwall.dao.impl.UserDaoImpl;
import com.stickerwall.dao.impl.UserInfoDaoImpl;
import com.stickerwall.entity.User;
import com.stickerwall.entity.UserInfo;
import com.stickerwall.service.*;
import com.stickerwall.util.ConnectionFactory;
import com.stickerwall.util.DateAndTime;

import java.sql.Connection;
import java.sql.SQLException;

public class UserInfoDaoTest {
    private static UserService userService = new UserService();
    private static UserInfoService userInfoService = new UserInfoService();

    public static void main(String[] args) {
        Connection conn = ConnectionFactory.getInstance().makeConnection();
//        updateUserInfoTest(conn);
//        getUserInfoByUserName(conn);
        getAllRowTotalTest();
    }

    public static void updateUserInfoTest(Connection conn) {

        UserDaoImpl userDao = new UserDaoImpl();
        UserInfoDaoImpl userInfoDao = new UserInfoDaoImpl();

        try {
//            获取用户
            User user = userService.getUserByName("yangyi");
//            获取用户信息
            UserInfo userInfo = userInfoService.getUserInfoByUserId(user.getId());
//            修改用户信息
            userInfo.setNickName("兮乄");
            userInfo.setBirthday(DateAndTime.strToDate("1996-03-13"));
            userInfo.setAddress("贵州省贵阳市花溪区贵州大学");
            userInfo.setMotto("若不是离愁，又怎会晕上眉头？");
            userInfo.setLastLoginTime(DateAndTime.getDateTimeForSql());

            userInfoDao.updateUserInfo(conn, user.getId(), userInfo);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getUserInfoByUserName(Connection conn) {

        UserInfo userInfo = userInfoService.getUserInfoByUserName("yangyi");
        System.out.println(userInfo.toString());
    }

    public static void getAllRowTotalTest() {

        int rowTotal = userInfoService.getAllRowTotal();
        System.out.println("===============" + rowTotal + "========================");

    }
} 
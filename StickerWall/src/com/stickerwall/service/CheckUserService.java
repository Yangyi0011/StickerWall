package com.stickerwall.service;

import com.stickerwall.dao.impl.UserDaoImpl;
import com.stickerwall.entity.User;
import com.stickerwall.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckUserService {
    private UserDaoImpl userDao = new UserDaoImpl();

    /**
     * 验证用户账号密码是否正确
     * @param user
     * @return
     */
    public boolean check(User user){
        Connection conn = null;

        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);

            ResultSet res = userDao.get(conn, user);

            while (res.next()){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();

            try{
                conn.rollback();
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }finally {
            try {
                conn.close();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 验证用户名是否可用
     * @param userName
     * @return
     */
    public boolean CheckUserName(String userName){

        Connection conn = null;
        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            ResultSet res = userDao.checkUserByName(conn, userName);

            while (res.next()){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            try {
                conn.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }
} 
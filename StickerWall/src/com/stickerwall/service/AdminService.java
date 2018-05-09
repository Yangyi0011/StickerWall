package com.stickerwall.service;

import com.stickerwall.dao.impl.AdminDaoImpl;
import com.stickerwall.entity.Admin;
import com.stickerwall.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminService {
    AdminDaoImpl adminDao = new AdminDaoImpl();

    public boolean checkAdminName(String adminName){
        Connection conn = null;

        try {
            conn = ConnectionFactory.getInstance().makeConnection();

            ResultSet res = adminDao.checkAdminName(conn,adminName);

            if (res.next()){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean checkAdmin(Admin admin){
        Connection conn = null;
        ResultSet res = null;

        try {
            conn = ConnectionFactory.getInstance().makeConnection();

            res = adminDao.get(conn,admin);

            if(res.next()){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public int getRowTotal(){
        Connection conn = null;
        int rowTotal = 0;
        ResultSet res = null;

        try {
            conn = ConnectionFactory.getInstance().makeConnection();

            res = adminDao.getRowTotal(conn);

            if(res.next()){
                rowTotal = res.getInt(1);
            }

            return rowTotal;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rowTotal;
    }

    public void save(Admin admin){
        Connection conn = null;

        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);

            adminDao.save(conn,admin);

            conn.commit();
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
    }

    public void update(Admin admin){
        Connection conn = null;

        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);

            adminDao.update(conn,admin);

            conn.commit();
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
    }

    public void delete(Admin admin){
        Connection conn = null;

        try {
            conn = ConnectionFactory.getInstance().makeConnection();

            conn.setAutoCommit(false);
            adminDao.delete(conn,admin);
            conn.commit();
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
    }

    public List<Admin> getAllWithPage(int startIndex, int pageSize){
        Connection conn = null;
        ResultSet res = null;
        List<Admin> adminList = new ArrayList<>();

        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);

            res = adminDao.getAllWithPage(conn,startIndex,pageSize);

            while (res.next()){
                Admin admin = new Admin();

                admin.setId(res.getLong("id"));
                admin.setName(res.getString("name"));
                admin.setPassword(res.getString("password"));
                admin.setNickname(res.getString("nickname"));
                admin.setType(res.getInt("type"));
                admin.setEmail(res.getString("email"));
                admin.setJoinTime(res.getTimestamp("joinTime"));
                admin.setLastLoginTime(res.getTimestamp("lastLoginTime"));

                adminList.add(admin);
            }
            conn.commit();

            return adminList;
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

        return adminList;
    }

    public Admin getAdminById(Long adminId){
        Connection conn = null;
        Admin admin = new Admin();
        ResultSet res = null;

        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);

            res = adminDao.getAdminById(conn,adminId);
            while (res.next()){
                admin.setId(res.getLong("id"));
                admin.setName(res.getString("name"));
                admin.setPassword(res.getString("password"));
                admin.setNickname(res.getString("nickname"));
                admin.setType(res.getInt("type"));
                admin.setEmail(res.getString("email"));
                admin.setJoinTime(res.getTimestamp("joinTime"));
                admin.setLastLoginTime(res.getTimestamp("lastLoginTime"));
            }
            conn.commit();

            return admin;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admin;
    }

    public Admin getAdminByName(String adminName){
        Connection conn = null;
        Admin admin = new Admin();
        ResultSet res = null;

        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);

            res = adminDao.getAdminByName(conn,adminName);
            while (res.next()){
                admin.setId(res.getLong("id"));
                admin.setName(res.getString("name"));
                admin.setPassword(res.getString("password"));
                admin.setNickname(res.getString("nickname"));
                admin.setType(res.getInt("type"));
                admin.setEmail(res.getString("email"));
                admin.setJoinTime(res.getTimestamp("joinTime"));
                admin.setLastLoginTime(res.getTimestamp("lastLoginTime"));
            }
            conn.commit();

            return admin;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admin;
    }
}
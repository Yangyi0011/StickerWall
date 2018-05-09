package com.stickerwall.test;

import com.stickerwall.dao.impl.PraiseDaoImpl;
import com.stickerwall.entity.Praise;
import com.stickerwall.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class PraiseDaoTest {

    public static void main(String[] args) {
        addTest();
    }
    public static void addTest(){
        PraiseDaoImpl praiseDao = new PraiseDaoImpl();
        Connection conn = null;
        Praise praise = new Praise();
        praise.setStickerId(Long.valueOf(8));
        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);
            praiseDao.add(conn, praise);
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
} 
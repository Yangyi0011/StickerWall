package com.stickerwall.test;

import com.stickerwall.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactoryTest {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory cf = ConnectionFactory.getInstance();

        Connection conn = cf.makeConnection();

        System.out.println(conn.getAutoCommit());
    }
} 
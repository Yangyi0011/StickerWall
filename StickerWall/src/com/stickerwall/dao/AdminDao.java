package com.stickerwall.dao;

import com.stickerwall.entity.Admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface AdminDao {
    public ResultSet checkAdminName(Connection conn, String adminName) throws SQLException;
    public ResultSet getRowTotal(Connection conn) throws SQLException;
    public ResultSet get(Connection conn, Admin admin) throws SQLException;
    public void save(Connection conn, Admin admin) throws SQLException;
    public void update(Connection conn, Admin admin) throws SQLException;
    public void delete(Connection conn, Admin admin) throws SQLException;
    public ResultSet getAllWithPage(Connection conn, int startIndex, int pageSize) throws SQLException;
    public ResultSet getAdminById(Connection conn, Long adminId) throws SQLException;
    public ResultSet getAdminByName(Connection conn, String adminName) throws SQLException;
} 
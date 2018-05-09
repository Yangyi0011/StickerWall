package com.stickerwall.dao.impl;

import com.stickerwall.dao.AdminDao;
import com.stickerwall.entity.Admin;
import com.stickerwall.util.DateAndTime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDaoImpl implements AdminDao {

    public ResultSet checkAdminName(Connection conn, String adminName) throws SQLException {
        String sql = " Select * From admin Where name = ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, adminName);

        return ps.executeQuery();
    }

    @Override
    public ResultSet getRowTotal(Connection conn) throws SQLException {
        String sql = " Select Count(*) From admin ";
        PreparedStatement ps = conn.prepareStatement(sql);

        return ps.executeQuery();
    }

    @Override
    public ResultSet get(Connection conn, Admin admin) throws SQLException {
        String sql = " Select * From admin Where name = ? And password = ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, admin.getName());
        ps.setString(2, admin.getPassword());

        return ps.executeQuery();
    }

    @Override
    public void save(Connection conn, Admin admin) throws SQLException {
        String sql = " Insert admin ( name, password, nickname, type, email, joinTime, lastLoginTime) Values (?,?,?,?,?,?,?) ";
        PreparedStatement ps = conn.prepareCall(sql);
        ps.setString(1, admin.getName());
        ps.setString(2, admin.getPassword());
        ps.setString(3, admin.getNickname());
        ps.setInt(4, admin.getType());
        ps.setString(5, admin.getEmail());
        ps.setTimestamp(6, DateAndTime.getDateTimeForSql());
        ps.setTimestamp(7, DateAndTime.getDateTimeForSql());

        ps.execute();
    }

    @Override
    public void update(Connection conn, Admin admin) throws SQLException {
        String sql = " Update admin set password = ?, nickname = ?, email = ?, lastLoginTime = ? Where id = ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, admin.getPassword());
        ps.setString(2, admin.getNickname());
        ps.setString(3, admin.getEmail());
        ps.setTimestamp(4, admin.getLastLoginTime());
        ps.setLong(5, admin.getId());

        ps.execute();
    }

    public void delete(Connection conn, Admin admin) throws SQLException {
        String sql = " Delete From admin Where id = ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, admin.getId());

        ps.execute();
    }

    @Override
    public ResultSet getAllWithPage(Connection conn, int startIndex, int pageSize) throws SQLException {
        String sql = " Select * From admin Where type = 0 Order By joinTime Desc limit ?,? ";
        PreparedStatement ps = conn.prepareCall(sql);
        ps.setInt(1, startIndex);
        ps.setInt(2, pageSize);

        return ps.executeQuery();
    }

    @Override
    public ResultSet getAdminById(Connection conn, Long adminId) throws SQLException {
        String sql = " Select * From admin Where id = ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, adminId);

        return ps.executeQuery();
    }

    @Override
    public ResultSet getAdminByName(Connection conn, String adminName) throws SQLException {
        String sql = " Select * From admin Where name = ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, adminName);

        return ps.executeQuery();
    }
}
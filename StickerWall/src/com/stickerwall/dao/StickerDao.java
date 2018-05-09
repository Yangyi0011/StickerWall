package com.stickerwall.dao;

import com.stickerwall.entity.Replies;
import com.stickerwall.entity.Sticker;
import com.stickerwall.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface StickerDao {
    public ResultSet getRowTotal(Connection conn, String searchContent) throws SQLException;
    public ResultSet getRowTotalForAdmin(Connection conn, String searchContent) throws SQLException;
    public ResultSet getRowTotalByUser(Connection conn, User user, String searchContent) throws SQLException;

    public ResultSet getStickersWithPage(Connection conn, String searchContent, String order, int startIndex, int pageSize) throws SQLException;
    public ResultSet getStickersWithPageByUser(Connection conn, User user, String searchContent, String order, int startIndex, int pageSize) throws SQLException;

    public ResultSet getStickersWithPageForAdmin(Connection conn, String searchContent, String order, int startIndex, int pageSize) throws SQLException;
    public void add(Connection conn, User user, Sticker sticker) throws SQLException;
    public void update(Connection conn, Long stickerId, Sticker sticker) throws SQLException;
    public void delete(Connection conn, Sticker sticker) throws SQLException;
    public Sticker getStickerByStickerId(Connection conn, Long stickerId) throws SQLException;
    public Sticker getStickerByReplies(Connection conn, Replies replies) throws SQLException;
}
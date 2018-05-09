package com.stickerwall.dao;

import com.stickerwall.entity.Replies;
import com.stickerwall.entity.Sticker;
import com.stickerwall.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface RepliesDao {

    public ResultSet getRowTotalBySticker(Connection conn, Sticker sticker) throws SQLException;
    public ResultSet getRowTotalByUser(Connection conn, User user) throws SQLException;
    public ResultSet getRepliesWithPageBySticker(Connection conn, Sticker sticker, int startIndex, int pageSize) throws SQLException;
    public ResultSet getRepliesWithPageByUser(Connection conn, User user, int startIndex, int pageSize) throws SQLException;

    public void add(Connection conn, User user, Sticker sticker, Replies replies) throws SQLException;
    public void update(Connection conn, Long repliesId, Replies replies) throws SQLException;
    public void delete(Connection conn, Replies replies) throws SQLException;
    public void deleteAllRepliesBySticker(Connection conn, Sticker sticker) throws SQLException;
}
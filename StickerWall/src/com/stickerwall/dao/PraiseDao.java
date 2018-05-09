package com.stickerwall.dao;

import com.stickerwall.entity.Praise;
import com.stickerwall.entity.Sticker;

import java.sql.Connection;
import java.sql.SQLException;

public interface PraiseDao {
    public void add(Connection conn, Praise praise) throws SQLException;
    public void update(Connection conn, Praise praise) throws SQLException;
    public void delete(Connection conn, Praise praise) throws SQLException;
    public void deletePraiseBySticker(Connection conn, Sticker sticker) throws SQLException;
    public Praise getPraiseById(Connection conn, Long id) throws SQLException;
    public Praise getPraiseByStickerId(Connection conn, Long stickerId) throws SQLException;
    public void deleteUserIdFromPraiseUserIds(Connection conn, Praise praise, Long userId) throws SQLException;
}
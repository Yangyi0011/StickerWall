package com.stickerwall.dao;

import com.stickerwall.entity.PersonalSpace;
import com.stickerwall.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface PersonalSpaceDao {

    public void add(Connection conn, User user) throws SQLException;
    public void update(Connection conn, User user, PersonalSpace space) throws SQLException;
    public void delete(Connection conn, PersonalSpace space) throws SQLException;
    public PersonalSpace getSpaceBySpaceId(Connection conn, Long spaceId) throws SQLException;
    public PersonalSpace getSpaceByUserId(Connection conn, Long userId) throws SQLException;
} 
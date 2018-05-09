package com.stickerwall.dao.impl;

import com.stickerwall.dao.PersonalSpaceDao;
import com.stickerwall.entity.PersonalSpace;
import com.stickerwall.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonalSpaceDaoImpl implements PersonalSpaceDao{
    /**
     * 开通用户空间
     * @param conn
     * @param user
     * @throws SQLException
     */
    @Override
    public void add(Connection conn, User user) throws SQLException {
        String sql = " Insert into personalspace (spaceName, userId) Values (?, ?)";
        PreparedStatement ps = conn.prepareCall(sql);
        ps.setString(1, user.getName() + "的空间");
        ps.setLong(2, user.getId());
        ps.execute();
    }

    /**
     * 更新用户空间
     * @param conn
     * @param user
     * @param space
     * @throws SQLException
     */
    @Override
    public void update(Connection conn, User user, PersonalSpace space) throws SQLException {
        String sql = " Update personalspace Set spaceName = ?, visitCount = ?, state = ? Where userId = ?; ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, space.getSpaceName());  //空间名
        ps.setInt(2, space.getVisitCount());    //空间访问数量
        ps.setInt(3, space.getState());     //空间使用状态
        ps.setLong(4, user.getId());

        ps.execute();
    }

    /**
     * 删除用户空间
     * @param conn
     * @param space
     * @throws SQLException
     */
    @Override
    public void delete(Connection conn, PersonalSpace space) throws SQLException {
        String sql = " Delete From personalspace Where id = ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, space.getId());

        ps.execute();
    }

    /**
     * 通过空间ID获取空间
     * @param conn
     * @param spaceId
     * @return
     * @throws SQLException
     */
    public PersonalSpace getSpaceBySpaceId ( Connection conn, Long spaceId) throws SQLException {
        String sql = " Select * From personalspace Where id = ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, spaceId);

        PersonalSpace space = new PersonalSpace();
        ResultSet res =  ps.executeQuery();

        while (res.next()){
            space.setId(res.getLong("id"));
            space.setSpaceName(res.getString("spaceName"));
            space.setUserID(res.getLong("userId"));
            space.setVisitCount(res.getInt("visitCount"));
            space.setState(res.getInt("state"));
        }
        return space;
    }

    /**
     * 通过用户ID获取用户空间
     * @param conn
     * @param userId
     * @return
     * @throws SQLException
     */
    public PersonalSpace getSpaceByUserId( Connection conn, Long userId) throws SQLException {
        String sql = " Select * From personalspace Where userId = ? ; ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, userId);

        PersonalSpace space = new PersonalSpace();
        ResultSet res =  ps.executeQuery();

        while (res.next()){
            space.setId(res.getLong("id"));
            space.setSpaceName(res.getString("spaceName"));
            space.setUserID(res.getLong("userId"));
            space.setVisitCount(res.getInt("visitCount"));
            space.setState(res.getInt("state"));
        }
        return space;
    }
}
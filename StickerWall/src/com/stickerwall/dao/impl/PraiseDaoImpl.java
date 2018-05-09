package com.stickerwall.dao.impl;

import com.stickerwall.dao.PraiseDao;
import com.stickerwall.entity.Praise;
import com.stickerwall.entity.Sticker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PraiseDaoImpl implements PraiseDao {
    /**
     * 添加点赞表
     * @param conn
     * @param praise
     * @throws SQLException
     */
    @Override
    public void add(Connection conn, Praise praise) throws SQLException {
        boolean isExist = checkPraise(conn,praise);
        if(!isExist){
            String sql = " Insert Into praise ( stickerId, praiseUserIds) Values (?, ?);";
            PreparedStatement ps = conn.prepareCall(sql);

            ps.setLong(1, praise.getStickerId());
            ps.setString(2, praise.getPraiseUserIds());
            ps.execute();
        }
    }

    /**
     * 验证点赞表是否已经存在
     * @param conn
     * @param praise
     * @return
     * @throws SQLException
     */
    private boolean checkPraise(Connection conn, Praise praise) throws SQLException {
        String sql = " Select * From praise Where stickerId = ? ;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, praise.getStickerId());

        ResultSet res = ps.executeQuery();
        while (res.next()){
            return true;
        }
        return false;
    }
    /**
     * 更新点赞表
     * @param conn
     * @param praise
     * @throws SQLException
     */
    @Override
    public void update(Connection conn, Praise praise) throws SQLException {
        String sql = " Update praise Set praiseUserIds = ? Where stickerId = ? ;";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, praise.getPraiseUserIds());
        ps.setLong(2, praise.getStickerId());

        ps.execute();
    }

    /**
     *删除点赞表
     * @param conn
     * @param praise
     * @throws SQLException
     */
    @Override
    public void delete(Connection conn, Praise praise) throws SQLException {
        String sql = " Delete From praise Where id = ? ;";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setLong(1, praise.getId());
        ps.execute();
    }

    /**
     * 通过ID获取点赞表
     * @param conn
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public Praise getPraiseById(Connection conn, Long id) throws SQLException {
        String sql = " Select * From praise Where id = ? ;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);

        ResultSet res = ps.executeQuery();
        Praise praise = new Praise();
        while (res.next()){
            praise.setId(res.getLong("id"));
            praise.setStickerId(res.getLong("stickerId"));
            praise.setPraiseUserIds(res.getString("praiseUserIds"));
        }

        return praise;
    }

    /**
     * 通过主贴获取点赞表
     * @param conn
     * @param stickerId
     * @return
     * @throws SQLException
     */
    @Override
    public Praise getPraiseByStickerId(Connection conn, Long stickerId) throws SQLException {
        String sql = " Select * From praise Where stickerId = ? ;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, stickerId);

        ResultSet res = ps.executeQuery();
        Praise praise = new Praise();
        while (res.next()){
            praise.setId(res.getLong("id"));
            praise.setStickerId(res.getLong("stickerId"));
            praise.setPraiseUserIds(res.getString("praiseUserIds"));
        }

        return praise;
    }

    /**
     * 删除主贴时删除其对应的点赞记录
     * @param conn
     * @param sticker
     * @throws SQLException
     */
    @Override
    public void deletePraiseBySticker(Connection conn, Sticker sticker) throws SQLException {
        String sql = " Delete From praise Where stickerId = ? ;";
        PreparedStatement ps =  conn.prepareStatement(sql);
        ps.setLong(1, sticker.getId());

        ps.execute();
    }

    /**
     * 用户取消点赞时，删除其点赞信息
     * @param conn
     * @param praise
     * @param userId
     * @return
     * @throws SQLException
     */
    @Override
    public void deleteUserIdFromPraiseUserIds(Connection conn, Praise praise, Long userId) throws SQLException {
        String userIds = praise.getPraiseUserIds();

        String [] idsArray = null;
        String newIds = "";   //接收新的IDS

        if ( !userIds.equals(null) && !userIds.equals("")){

            if(userIds.length() > 1){
                idsArray = userIds.split(",");
                for (String id : idsArray){
                    if( !id.equals(userId.toString()) && !id.equals(null) && !id.equals("") ){
                        if(newIds == null || newIds == ""){
                            newIds += id;
                        }else {
                            newIds += ",";
                            newIds += id;
                        }
                    }
                }
            }else {
                if(!userIds.equals(userId.toString())){
                    newIds += userIds;
                }
            }
        }
        praise.setPraiseUserIds(newIds);
        update(conn, praise);      //更新数据
    }
}
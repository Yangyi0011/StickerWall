package com.stickerwall.dao.impl;

import com.stickerwall.dao.StickerDao;
import com.stickerwall.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StickerDaoImpl implements StickerDao {

    /**
     * 获取所有贴纸总数
     * 用于首页显示
     * @param conn
     * @return
     * @throws SQLException
     */
    public ResultSet getRowTotal(Connection conn, String searchContent) throws SQLException {
        String sql = " Select Count(*) From sticker Where state = 1 ";

        if( searchContent != null && !searchContent.equals("")){
            sql += " And ( stickerTitle Like '%"+searchContent+"%' Or stickerContent Like '%"+searchContent+"%' ) ";
        }
        PreparedStatement ps = conn.prepareStatement(sql);

        return ps.executeQuery();
    }
    /**
     * 获取所有贴纸总数
     * 用于管理
     * @param conn
     * @return
     * @throws SQLException
     */
    public ResultSet getRowTotalForAdmin(Connection conn, String searchContent) throws SQLException {
        String sql = " Select Count(*) From sticker Where 1 = 1 ";

        if( searchContent != null && !searchContent.equals("")){
            sql += " And ( stickerTitle Like '%"+searchContent+"%' Or stickerContent Like '%"+searchContent+"%' ) ";
        }
        PreparedStatement ps = conn.prepareStatement(sql);

        return ps.executeQuery();
    }

    /**
     * 获取一个用户的发帖总数
     * 用于显示
     * @param conn
     * @param user
     * @return
     * @throws SQLException
     */
    public ResultSet getRowTotalByUser(Connection conn, User user, String searchContent) throws SQLException {
        String sql = " Select Count(*) From sticker Where state = 1 And userId = ? ";

        if( searchContent != null && !searchContent.equals("")){
            sql += " And ( stickerTitle Like '%"+searchContent+"%' Or stickerContent Like '%"+searchContent+"%' ) ";
        }
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, user.getId());

        return ps.executeQuery();
    }

    /**
     * 分页获取所有帖子数据
     * 用于显示
     * @param conn
     * @param searchContent: 查找内容
     * @param startIndex：从第几条开始获取
     * @param pageSize：需要获取多少条，即每页显示多少条数据
     * @return
     * @throws SQLException
     */
    public ResultSet getStickersWithPage(Connection conn, String searchContent, String order, int startIndex, int pageSize) throws SQLException {
        String sql = " Select * From sticker Where state = 1  ";

        if (searchContent != null && !searchContent.equals("")){
            sql += " And ( stickerTitle Like '%"+searchContent+"%' Or stickerContent Like '%"+searchContent+"%' ) ";
        }
        if(order != null && !order.equals("")){
            sql +=" ORDER BY "+order+" desc limit ?, ? ";
        }else {
            sql +=" ORDER BY releaseTime desc,lastReplyTime desc,replyCount desc,praiseCount desc,replyCount desc limit ?, ? ";
        }
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, startIndex);
        ps.setInt(2, pageSize);

        return ps.executeQuery();
    }

    /**
     * 分页获取所有帖子数据
     * 用于管理
     * @param conn
     * @param startIndex：从第几条开始获取
     * @param pageSize：需要获取多少条，即每页显示多少条数据
     * @return
     * @throws SQLException
     */
    public ResultSet getStickersWithPageForAdmin(Connection conn, String searchContent, String order, int startIndex, int pageSize) throws SQLException {
        String sql = " Select * From sticker Where 1 = 1 ";

        if (searchContent != null && !searchContent.equals("")){
            sql += " And stickerTitle Like '%"+searchContent+"%' Or stickerContent Like '%"+searchContent+"%' ";
        }

        if(order != null && !order.equals("")){
            sql +=" ORDER BY "+order+" desc limit ?, ? ";
        }else {
            sql +=" ORDER BY releaseTime desc,lastReplyTime desc,replyCount desc,praiseCount desc,replyCount desc limit ?, ? ";
        }

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, startIndex);
        ps.setInt(2, pageSize);

        return ps.executeQuery();
    }

    /**
     * 分页获取一个用户的所有发帖数据
     * 用于显示
     * @param conn
     * @param user
     * @param startIndex
     * @param pageSize
     * @return
     * @throws SQLException
     */
    public ResultSet getStickersWithPageByUser(Connection conn, User user, String searchContent, String order, int startIndex, int pageSize) throws SQLException {
        String sql = " Select * From sticker Where state = 1 And userId = ?  ";

        if (searchContent != null && !searchContent.equals("")){
            sql += " And stickerTitle Like '%"+searchContent+"%' Or stickerContent Like '%"+searchContent+"%' ";
        }

        if(order != null && !order.equals("")){
            sql +=" ORDER BY "+order+" limit ?, ? ";
        }else {
            sql +=" ORDER BY releaseTime desc,lastReplyTime,replyCount,praiseCount,replyCount desc limit ?, ? ";
        }
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, user.getId());
        ps.setInt(2, startIndex);
        ps.setInt(3, pageSize);

        return ps.executeQuery();
    }

    /**
     * 添加发帖
     * @param conn
     * @param user
     * @param sticker
     * @throws SQLException
     */
    @Override
    public void add(Connection conn, User user, Sticker sticker) throws SQLException {
        String sql = " Insert Into sticker (userID, stickerTitle, stickerContent, releaseTime, lastReplyTime ) " +
                " Values (?, ?, ?, ?, ?) ";
        PreparedStatement ps = conn.prepareCall(sql);
        ps.setLong(1, user.getId());
        ps.setString(2, sticker.getStickerTitle());
        ps.setString(3, sticker.getStickerContent());
        ps.setTimestamp(4, sticker.getReleaseTime());
        ps.setTimestamp(5, sticker.getLastReplyTime());
        ps.execute();
    }

    /**
     * 更新帖子信息
     *
     * @param conn
     * @param stickerId
     * @param sticker
     * @throws SQLException
     */
    @Override
    public void update(Connection conn, Long stickerId, Sticker sticker) throws SQLException {
        String sql = " Update sticker Set clickCount = ?, praiseCount = ?, replyCount = ?, lastReplyTime = ?, " +
                " state = ? Where id = ?  ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, sticker.getClickCount());  //点击数
        ps.setInt(2, sticker.getPraiseCount()); //点赞数
        ps.setInt(3, sticker.getReplyCount());  //回复数
        ps.setTimestamp(4, sticker.getLastReplyTime()); //最后回复时间
        ps.setInt(5, sticker.getState());   //使用状态
        ps.setLong(6, stickerId);

        ps.execute();
    }

    /**
     * 删除贴纸
     * @param conn
     * @param sticker
     * @throws SQLException
     */
    @Override
    public void delete(Connection conn, Sticker sticker) throws SQLException {

//        每次删贴前，先删除其跟帖信息，再删除此贴
//        1.删除主贴前，先删除其全部跟帖
        RepliesDaoImpl repliesDao = new RepliesDaoImpl();
        repliesDao.deleteAllRepliesBySticker(conn, sticker);

//        2.删除主贴之前，先删除其点赞表
        PraiseDaoImpl praiseDao = new PraiseDaoImpl();
        praiseDao.deletePraiseBySticker(conn, sticker);

//        3.删除这个主贴
        String sql = " Delete From sticker Where id = ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, sticker.getId());
        ps.execute();

    }

    /**
     * 通过ID来获取帖子
     *
     * @param conn
     * @param stickerId
     * @return
     * @throws SQLException
     */
    public Sticker getStickerByStickerId(Connection conn, Long stickerId) throws SQLException {
        String sql = " Select * From sticker Where id = ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, stickerId);

        Sticker sticker = new Sticker();
        ResultSet res = ps.executeQuery();

        while (res.next()) {
            sticker.setId(res.getLong("id"));
            sticker.setUserId(res.getLong("userID"));
            sticker.setStickerTitle(res.getString("stickerTitle"));
            sticker.setStickerContent(res.getString("stickerContent"));
            sticker.setReleaseTime(res.getTimestamp("releaseTime"));
            sticker.setClickCount(res.getInt("clickCount"));
            sticker.setPraiseCount(res.getInt("praiseCount"));
            sticker.setReplyCount(res.getInt("replyCount"));
            sticker.setLastReplyTime(res.getTimestamp("lastReplyTime"));
            sticker.setState(res.getInt("state"));
        }
        return sticker;
    }

    /**
     * 通过回复获取主贴
     *
     * @param conn
     * @param replies
     * @return
     * @throws SQLException
     */
    public Sticker getStickerByReplies(Connection conn, Replies replies) throws SQLException {
        String sql = " Select stickerId From replies Where id = ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, replies.getId());

        ResultSet res = ps.executeQuery();
        Long stickerId = null;
        while (res.next()) {
            stickerId = res.getLong("stickerId");
        }
        return getStickerByStickerId(conn, stickerId);
    }
}
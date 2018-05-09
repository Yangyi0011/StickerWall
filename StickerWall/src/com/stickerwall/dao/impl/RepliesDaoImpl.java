package com.stickerwall.dao.impl;

import com.stickerwall.dao.RepliesDao;
import com.stickerwall.dao.UserDao;
import com.stickerwall.entity.PersonalSpace;
import com.stickerwall.entity.Replies;
import com.stickerwall.entity.Sticker;
import com.stickerwall.entity.User;
import com.stickerwall.service.StickerService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepliesDaoImpl implements RepliesDao {
    /**
     * 获取一个主贴的回帖总数
     * @param conn
     * @return
     * @throws SQLException
     */
    public ResultSet getRowTotalBySticker(Connection conn, Sticker sticker) throws SQLException {
        String sql = " Select Count(*) From replies Where stickerId = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, sticker.getId());

        return ps.executeQuery();
    }

    /**
     * 获取一个用户的回帖总数
     * @param conn
     * @param user
     * @return
     * @throws SQLException
     */
    public ResultSet getRowTotalByUser(Connection conn, User user) throws SQLException {
        String sql = " Select Count(*) From replies Where userId = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, user.getId());

        return ps.executeQuery();
    }

    /**
     * 分页获取一个主贴的所有回帖
     * @param conn
     * @param sticker
     * @param startIndex
     * @param pageSize
     * @return
     * @throws SQLException
     */
    public ResultSet getRepliesWithPageBySticker(Connection conn, Sticker sticker,
                                                 int startIndex, int pageSize) throws SQLException {
        String sql = " Select * From replies Where stickerId = ? limit ?, ? ;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, sticker.getId());
        ps.setInt(2, startIndex);
        ps.setInt(3, pageSize);

        return ps.executeQuery();
    }

    /**
     * 分页获取一个用户的所有回帖
     * @param conn
     * @param user
     * @param startIndex
     * @param pageSize
     * @return
     * @throws SQLException
     */
    public ResultSet getRepliesWithPageByUser(Connection conn, User user,
                                                 int startIndex, int pageSize) throws SQLException {
        String sql = " Select * From replies Where userId = ? limit ?, ? ;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, user.getId());
        ps.setInt(2, startIndex);
        ps.setInt(3, pageSize);

        return ps.executeQuery();
    }
    /**
     * 添加回帖
     * @param conn
     * @param user
     * @param sticker
     * @param replies
     * @throws SQLException
     */
    @Override
    public void add(Connection conn, User user, Sticker sticker, Replies replies) throws SQLException {
        String sql = " Insert Into replies (stickerID, userID, repliesContent, repliesTime ) " +
                " Values (?, ?, ?, ? ) ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, sticker.getId());
        ps.setLong(2, user.getId());
        ps.setString(3, replies.getRepliesContent());
        ps.setTimestamp(4, replies.getRepliesTime());

        ps.execute();

//        每一次回复后都会更新主贴的回复数和最后回复时间
        StickerDaoImpl stickerDao = new StickerDaoImpl();
        sticker.setReplyCount(sticker.getReplyCount() + 1);
        sticker.setLastReplyTime(replies.getRepliesTime());
        stickerDao.update(conn, sticker.getId(), sticker);
    }

    /**
     * 编辑回帖内容？
     * @param conn
     * @param repliesId
     * @param replies
     * @throws SQLException
     */
    @Override
    public void update(Connection conn, Long repliesId, Replies replies) throws SQLException {

    }

    /**
     * 删除回帖
     *
     * @param conn
     * @param replies
     * @throws SQLException
     */
    @Override
    public void delete(Connection conn, Replies replies) throws SQLException {

//        每次删除回复前，先通过此回复找到主贴，让主贴的回复数-1，再删除这个回帖
        StickerDaoImpl stickerDao = new StickerDaoImpl();
        Sticker sticker = stickerDao.getStickerByReplies(conn, replies);
        sticker.setReplyCount(sticker.getReplyCount() - 1);
        stickerDao.update(conn, sticker.getId(), sticker);

//       删除这个回贴
        String sql = " Delete From replies Where id = ? ; ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, replies.getId());
        ps.execute();
    }

    /**
     * 通过回帖ID来获取回帖
     *
     * @param conn
     * @param repliesId
     * @return
     * @throws SQLException
     */
    public Replies getRepliesByRepliesId(Connection conn, Long repliesId) throws SQLException {
        String sql = " Select * From replies Where id = ? ; ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, repliesId);

        ResultSet res = ps.executeQuery();
        Replies replies = new Replies();
        while (res.next()) {
            replies.setId(res.getLong("id"));
            replies.setStickerId(res.getLong("stickerId"));
            replies.setUserId(res.getLong("userId"));
            replies.setRepliesContent(res.getString("repliesContent"));
            replies.setRepliesTime(res.getTimestamp("repliesTime"));
            replies.setState(res.getInt("state"));
        }
        return replies;
    }

//    /**
//     * 从回帖ID集中删除指定的ID
//     * @param conn
//     * @param deletRepliesId
//     * @return
//     * @throws SQLException
//     */
//    @Override
//    public String deleteRepliesIdFromIDS(Connection conn, Long deletRepliesId) throws SQLException {
//
////        1.通过id获取被删贴纸
//        Replies replies = getRepliesByRepliesId(conn, deletRepliesId);
//
////        2.通过被删贴纸获取贴纸主人
//        UserDaoImpl userDao = new UserDaoImpl();
//        User user = userDao.getUserById(conn, replies.getUserId());
//
////        3.通过贴纸主人获取其所有回帖ID集
//        String idsStr = getRepliesIdsByUser(conn, user);
//
////        4.创建一个容器，储存新的IDS
//        String ids = "";
//
////        5.从IDS中去除被删ID
//        if (idsStr.length() > 1) {
//            String[] idsArray = idsStr.split(",");
//            for (String id : idsArray) {
//                if (!id.equals(deletRepliesId) && !id.equals(null) && !id.equals("")) {
//                    if (ids.equals("") || ids.equals(null)) {
//                        ids += id;
//                    } else {
//                        ids = ",";
//                        ids += id;
//                    }
//                }
//            }
//        } else {
//            if (!idsStr.equals(deletRepliesId)) {
//                ids = idsStr;
//            }
//        }
//
////        6.返回更新的IDS
//        return ids;
//    }

    /**
     * 删除一个主贴的所有回帖信息
     * @param conn
     * @param sticker
     * @throws SQLException
     */
    public void deleteAllRepliesBySticker(Connection conn, Sticker sticker) throws SQLException {
        String sql = " Delete From replies Where stickerId = ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, sticker.getId());
        ps.execute();
    }
}
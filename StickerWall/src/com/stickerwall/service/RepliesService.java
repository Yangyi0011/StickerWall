package com.stickerwall.service;

import com.stickerwall.dao.impl.RepliesDaoImpl;
import com.stickerwall.entity.Replies;
import com.stickerwall.entity.Sticker;
import com.stickerwall.entity.User;
import com.stickerwall.util.ConnectionFactory;
import com.stickerwall.util.DateAndTime;
import net.sf.json.JSONArray;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepliesService {
    RepliesDaoImpl repliesDao = new RepliesDaoImpl();

    /**
     * 获取一个主贴的回帖总数
     * @param sticker
     * @return
     */
    public int getRowTotalBySticker(Sticker sticker){
        Connection conn = null;
        ResultSet res = null;
        int rowTotal = 0;

        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);

            res = repliesDao.getRowTotalBySticker(conn, sticker);
            if(res.next()){
                rowTotal = res.getInt(1);
            }

            conn.commit();

            return rowTotal;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rowTotal;
    }
    /**
     * 获取一个用户的回帖总数
     * @param user
     * @return
     */
    public int getRowTotalByUser(User user){
        Connection conn = null;
        ResultSet res = null;
        int rowTotal = 0;

        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);

            res = repliesDao.getRowTotalByUser(conn,user);
            if(res.next()){
                rowTotal = res.getInt(1);
            }

            conn.commit();

            return rowTotal;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rowTotal;
    }

    /**
     * 分页获取一个帖子的所有回帖
     * @param sticker
     * @param startIndex
     * @param pageSize
     * @return
     */
    public List<Replies> getRepliesWithPageBySticker(Sticker sticker, int startIndex, int pageSize){
        Connection conn = null;
        ResultSet res = null;
        List<Replies> repliesList = new ArrayList<>();

        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);

            res = repliesDao.getRepliesWithPageBySticker(conn, sticker, startIndex, pageSize);
            while (res.next()){
                Replies replies = new Replies();

                replies.setId(res.getLong("id"));
                replies.setStickerId(res.getLong("stickerId"));
                replies.setUserId(res.getLong("userId"));
                replies.setRepliesContent(res.getString("repliesContent"));
                replies.setRepliesTime(res.getTimestamp("repliesTime"));
                replies.setState(res.getInt("state"));

                repliesList.add(replies);
            }

            conn.commit();

            return repliesList;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return repliesList;
    }

    /**
     * 分页获取一个用户的所有回帖
     * @param user
     * @param startIndex
     * @param pageSize
     * @return
     */
    public List<Replies> getRepliesWithPageByUser(User user, int startIndex, int pageSize){
        Connection conn = null;
        ResultSet res = null;
        List<Replies> repliesList = new ArrayList<>();

        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);

            res = repliesDao.getRepliesWithPageByUser(conn,user,startIndex,pageSize);
            while (res.next()){
                Replies replies = new Replies();

                replies.setId(res.getLong("id"));
                replies.setStickerId(res.getLong("stickerId"));
                replies.setUserId(res.getLong("userId"));
                replies.setRepliesContent(res.getString("repliesContent"));
                replies.setRepliesTime(res.getTimestamp("repliesTime"));
                replies.setState(res.getInt("state"));

                repliesList.add(replies);
            }

            conn.commit();

            return repliesList;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return repliesList;
    }

    public void save(User user, Sticker sticker, String repliesContent){
        Connection conn = null;

        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);

            Replies replies = new Replies();
            replies.setRepliesContent(repliesContent);
            replies.setRepliesTime(DateAndTime.getDateTimeForSql());

            repliesDao.add(conn,user,sticker,replies);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
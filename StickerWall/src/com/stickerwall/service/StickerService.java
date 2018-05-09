package com.stickerwall.service;

import com.stickerwall.dao.impl.StickerDaoImpl;
import com.stickerwall.entity.Replies;
import com.stickerwall.entity.Sticker;
import com.stickerwall.entity.User;
import com.stickerwall.util.ConnectionFactory;
import com.stickerwall.util.DateAndTime;
import net.sf.json.JSONArray;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class StickerService {
    StickerDaoImpl stickerDao = new StickerDaoImpl();

    /**
     * 获取所有贴纸总数
     * 用于显示
     * @return
     */
    public int getRowTotal(String searchContent){
        Connection conn = null;
        ResultSet res = null;
        int rowTotal = 0;

        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);

            res = stickerDao.getRowTotal(conn, searchContent);
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
     * 获取所有贴纸总数
     * 用于管理
     * @return
     */
    public int getRowTotalForAdmin(String searchContent){
        Connection conn = null;
        ResultSet res = null;
        int rowTotal = 0;

        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);

            res = stickerDao.getRowTotalForAdmin(conn, searchContent);
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
     * 获取一个用户的发帖总数
     * @param user
     * @return
     */
    public int getRowTotalByUser(User user, String searchContent){
        Connection conn = null;
        ResultSet res = null;
        int rowTotal = 0;

        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);

            res = stickerDao.getRowTotalByUser(conn, user,searchContent);
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
     * 分页获取所有贴纸
     * 用于显示
     * @param startIndex：从第几条开始获取
     * @param pageSize：共获取多少条，即每页显示多少条
     * @return
     */
    public List<Sticker> getStickersWithPage(String searchContent, String order, int startIndex, int pageSize){
        Connection conn = null;
        ResultSet res = null;
        List<Sticker> stickerList = new ArrayList<>();

        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);

            res = stickerDao.getStickersWithPage(conn,searchContent, order, startIndex,pageSize);
            while (res.next()){
                Sticker sticker = new Sticker();

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

                stickerList.add(sticker);
            }

            conn.commit();

            return stickerList;
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

        return stickerList;
    }

    /**
     * 分页获取所有贴纸
     * 用于管理
     * @param startIndex：从第几条开始获取
     * @param pageSize：共获取多少条，即每页显示多少条
     * @return
     */
    public List<Sticker> getStickersWithPageForAdmin(String searchContent, String order, int startIndex, int pageSize){
        Connection conn = null;
        ResultSet res = null;
        List<Sticker> stickerList = new ArrayList<>();

        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);

            res = stickerDao.getStickersWithPageForAdmin(conn,searchContent, order, startIndex,pageSize);
            while (res.next()){
                Sticker sticker = new Sticker();

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

                stickerList.add(sticker);
            }

            conn.commit();

            return stickerList;
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

        return stickerList;
    }
    /**
     * 分页获取一个用户的所有发帖
     * @param startIndex：从第几条开始获取
     * @param pageSize：共获取多少条，即每页显示多少条
     * @return
     */
    public List<Sticker> getStickersWithPageByUser(User user, String searchContent, String order, int startIndex, int pageSize){
        Connection conn = null;
        ResultSet res = null;
        List<Sticker> stickerList = new ArrayList<>();

        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);

            res = stickerDao.getStickersWithPageByUser(conn, user, searchContent, order,startIndex, pageSize);
            while (res.next()){
                Sticker sticker = new Sticker();

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

                stickerList.add(sticker);
            }

            conn.commit();

            return stickerList;
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

        return stickerList;
    }

    /**
     * 更新信息
     * @param sticker
     */
    public void update(Sticker sticker){
        Connection conn = null;

        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);

            stickerDao.update(conn,sticker.getId(),sticker);
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

    /**
     * 保存一个新的发帖
     * @param user
     * @param stickerTitle
     * @param stickerContent
     */
    public void save(User user, String stickerTitle, String stickerContent){
        Connection conn = null;
        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);

            Sticker sticker = new Sticker();
            sticker.setStickerTitle(stickerTitle);
            sticker.setStickerContent(stickerContent);
            sticker.setReleaseTime(DateAndTime.getDateTimeForSql());
            sticker.setLastReplyTime(DateAndTime.getDateTimeForSql());

            stickerDao.add(conn,user,sticker);
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

    /**
     * 删除一个贴纸
     * @param sticker
     */
    public void delete(Sticker sticker){
        Connection conn = null;
        try {
            conn = ConnectionFactory.getInstance().makeConnection();

            conn.setAutoCommit(false);
            stickerDao.delete(conn,sticker);
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

    /**
     * 通过贴纸Id获取贴纸信息
     * @param stickerId
     * @return
     */
    public Sticker getStickerByStickerId( Long stickerId){
        Connection conn = null;
        Sticker sticker = new Sticker();

        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);
            sticker = stickerDao.getStickerByStickerId(conn, stickerId);
            conn.commit();

            return sticker;
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
        return sticker;
    }

    /**
     * 通过回帖获取主贴信息
     * @param replies
     * @return
     */
    public Sticker getStickerByReplies(Replies replies){
        Connection conn = null;
        Sticker sticker = new Sticker();

        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);

            sticker = stickerDao.getStickerByReplies(conn, replies);
            conn.commit();

            return sticker;
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
        return sticker;
    }
}
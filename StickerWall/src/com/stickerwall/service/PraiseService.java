package com.stickerwall.service;

import com.stickerwall.dao.impl.PraiseDaoImpl;
import com.stickerwall.dao.impl.StickerDaoImpl;
import com.stickerwall.entity.Praise;
import com.stickerwall.entity.Sticker;
import com.stickerwall.entity.User;
import com.stickerwall.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class PraiseService {
    PraiseDaoImpl praiseDao = new PraiseDaoImpl();
    StickerDaoImpl stickerDao = new StickerDaoImpl();

    /**
     * 通过点赞表ID获取点赞信息
     * @param id
     * @return
     */
    public Praise getPraiseById(Long id){
        Connection conn = null;
        Praise praise = null;
        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);

            praise = praiseDao.getPraiseById(conn,id);
            conn.commit();

            return praise;
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
        return praise;
    }

    /**
     * 通过主贴ID获取其对应点赞信息
     * @param stickerId
     * @return
     */
    public Praise getPraiseByStickerId(Long stickerId){
        Connection conn = null;
        Praise praise = null;
        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);

            praise = praiseDao.getPraiseByStickerId(conn,stickerId);
            conn.commit();

            return praise;
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
        return praise;
    }

    /**
     * 验证用户对指定贴纸是否已点赞过
     * @param sticker
     * @param userId
     * @return
     */
    public boolean checkPraiseUser(Sticker sticker, String userId){
        Connection conn = null;
        String ids = null;

        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);

            Praise praise = praiseDao.getPraiseByStickerId(conn, sticker.getId());
            ids = praise.getPraiseUserIds();
            conn.commit();

            if( ids == null || ids.equals("")){
                return false;

            }else {
                if(ids.length() > 1){
                    String [] idsArray = ids.split(",");

                    for (String id : idsArray){
                        if ( id.equals(userId)){
                            return true;
                        }
                    }
                }else if(ids.equals(userId)){
                    return true;
                }else {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 点赞
     * @param sticker
     * @param userId
     */
    public void doPraise(Sticker sticker, Long userId){
        Connection conn = null;
        Praise praise = new Praise();

        conn = ConnectionFactory.getInstance().makeConnection();

        if(sticker.getPraiseCount() > 0){
            //已经有点赞表了就执行后面的，否则先创建点赞表
        }else {
            praise.setStickerId(sticker.getId());
            praise.setPraiseUserIds("");    //避免第一个为null
            try {
                praiseDao.add(conn,praise);
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }

        try {
            conn.setAutoCommit(false);

//            1.让被赞贴纸的点赞数+1
            int praiseCount = sticker.getPraiseCount() + 1;
            sticker.setPraiseCount(praiseCount);
            stickerDao.update(conn,sticker.getId(),sticker);

//            2.在点赞表中添加点赞人的ID
            praise = praiseDao.getPraiseByStickerId(conn, sticker.getId());
            String ids = praise.getPraiseUserIds();
            if(ids == null || ids.equals("")){
                ids += userId;

            }else {
                ids += ",";
                ids += userId;
            }

            praise.setPraiseUserIds(ids);
            praiseDao.update(conn,praise);

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
     * 取消点赞
     * @param sticker
     * @param userId
     */
    public void cancelPraise(Sticker sticker, Long userId){
        Connection conn = null;
        Praise praise = null;

        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);

//            1.让主贴的点赞量-1
            int praiseCount = sticker.getPraiseCount() - 1;
            sticker.setPraiseCount(praiseCount);
            stickerDao.update(conn,sticker.getId(),sticker);

//            2.从点赞表中提出取消点赞的用户ID
            praise = praiseDao.getPraiseByStickerId(conn, sticker.getId());
            praiseDao.deleteUserIdFromPraiseUserIds(conn,praise,userId);

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
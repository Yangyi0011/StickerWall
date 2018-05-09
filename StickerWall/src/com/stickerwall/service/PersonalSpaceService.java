package com.stickerwall.service;

import com.stickerwall.dao.impl.PersonalSpaceDaoImpl;
import com.stickerwall.entity.PersonalSpace;
import com.stickerwall.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonalSpaceService {
    private PersonalSpaceDaoImpl spaceDao = new PersonalSpaceDaoImpl();

    /**
     *通过空间ID获取空间信息
      * @param spaceId
     * @return
     */
    public PersonalSpace getSpaceBySpaceId(Long spaceId){
        Connection conn = null;
        PersonalSpace space = new PersonalSpace();

        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);

            space = spaceDao.getSpaceBySpaceId(conn, spaceId);

            conn.commit();
            return space;
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
        return space;
    }

    /**
     * 通过用户ID获取空间信息
     * @param userId
     * @return
     */
   public PersonalSpace getSpaceByUserId(Long userId){
       Connection conn = null;
       ResultSet res = null;
       PersonalSpace space = new PersonalSpace();

       try {
           conn = ConnectionFactory.getInstance().makeConnection();
           conn.setAutoCommit(false);

           space = spaceDao.getSpaceByUserId(conn, userId);
           conn.commit();

           return space;
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
       return space;
   }
}
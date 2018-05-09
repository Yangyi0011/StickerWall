package com.stickerwall.service;

import com.stickerwall.entity.Admin;
import com.stickerwall.entity.Sticker;
import com.stickerwall.entity.User;
import com.stickerwall.entity.UserInfo;
import com.stickerwall.util.PageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;

public class PageBeanService {
    private UserInfoService userInfoService = new UserInfoService();
    private StickerService stickerService = new StickerService();
    private RepliesService repliesService = new RepliesService();
    private AdminService adminService = new AdminService();
    /**
     * 分页获取所有用户信息
     * @param thisPage
     * @param pageSize
     * @return
     */
    public JSONObject getUserInfoWithPage( String searchContent, String order, int thisPage, int pageSize){

//        在这里需要将PageBean中的数据创建好，然后将该对象传回去
//        先要从数据库中获取所有用户的数据总数，从而获取dataTotal
        int dataTotal = userInfoService.getAllRowTotal();

//        有了三个初始数据便可创建pageBean对象了
        PageBean pb = new PageBean(thisPage,pageSize,dataTotal);

//        获取PageBean对象中的startIndex.
        int startIndex = pb.getStartIndex();

//        有startIndex和PageSize便可以拿到每一页的数据了
        pb.setList(userInfoService.getUserInfoWithPage(searchContent, order,startIndex,pageSize));

        JSONObject jsonObject = JSONObject.fromObject(pb);

        return jsonObject;
    }

    /**
     * 分页获取所有贴纸信息，用于显示
     * 返回Json
     * @param thisPage
     * @param pageSize
     * @return
     */
    public JSONObject getAllStickersWithPage( String searchContent, String order, int thisPage, int pageSize){

        int dataTotal = stickerService.getRowTotal(searchContent);
//        有了三个初始数据便可创建pageBean对象了
        PageBean pb = new PageBean(thisPage,pageSize,dataTotal);

        int startIndex = pb.getStartIndex();
        List<Sticker> stickerList = stickerService.getStickersWithPage(searchContent, order, startIndex,pageSize);
        pb.setList(stickerList);

        JSONObject jsonObject = JSONObject.fromObject(pb);

        return jsonObject;
    }

    /**
     * 分页获取所有贴纸，用于管理
     * @param searchContent
     * @param order
     * @param thisPage
     * @param pageSize
     * @return
     */
    public JSONObject getStickersWithPageForAdmin( String searchContent, String order, int thisPage, int pageSize){

        int dataTotal = stickerService.getRowTotalForAdmin(searchContent);
//        有了三个初始数据便可创建pageBean对象了
        PageBean pb = new PageBean(thisPage,pageSize,dataTotal);

        int startIndex = pb.getStartIndex();
        List<Sticker> stickerList = stickerService.getStickersWithPageForAdmin(searchContent, order, startIndex,pageSize);
        pb.setList(stickerList);

        JSONObject jsonObject = JSONObject.fromObject(pb);

        return jsonObject;
    }

    /**
     * 分页获取管理员
     * @param thisPage
     * @param pageSize
     * @return
     */
    public JSONObject getAdminWithPage(int thisPage, int pageSize){

        int dataTotal = adminService.getRowTotal();
//        有了三个初始数据便可创建pageBean对象了
        PageBean pb = new PageBean(thisPage,pageSize,dataTotal);

        int startIndex = pb.getStartIndex();
        List<Admin> adminList = adminService.getAllWithPage(startIndex,pageSize);

        pb.setList(adminList);

        JSONObject jsonObject = JSONObject.fromObject(pb);

        return jsonObject;
    }

    /**
     * 分页获取一个用户的所有发帖信息
     * 返回Json
     * @param user
     * @param thisPage
     * @param pageSize
     * @return
     */
    public JSONObject getStickersWithPageByUser(User user, String searchContent, String order, int thisPage, int pageSize){

        int dataTotal = stickerService.getRowTotalByUser(user,searchContent);
//        有了三个初始数据便可创建pageBean对象了
        PageBean pb = new PageBean(thisPage,pageSize,dataTotal);

        int startIndex = pb.getStartIndex();
        pb.setList(stickerService.getStickersWithPageByUser(user, searchContent, order, startIndex,pageSize));

        JSONObject jsonObject = JSONObject.fromObject(pb);

        return jsonObject;
    }

    /**
     * 分页获取一个主贴的所有回帖信息
     * 返回Json
     * @param thisPage
     * @param pageSize
     * @return
     */
    public JSONObject getRepliesWithPageBySticker(Sticker sticker, int thisPage, int pageSize){

        int dataTotal = repliesService.getRowTotalBySticker(sticker);
        PageBean pb = new PageBean(thisPage,pageSize,dataTotal);

        int startIndex = pb.getStartIndex();
        pb.setList(repliesService.getRepliesWithPageBySticker(sticker,startIndex,pageSize));

        JSONObject jsonObject = JSONObject.fromObject(pb);

        return jsonObject;
    }
    /**
     * 分页获取一个用户的所有回帖信息
     * 返回Json
     * @param thisPage
     * @param pageSize
     * @return
     */
    public JSONObject getRepliesWithPageByUser(User user, int thisPage, int pageSize){

        int dataTotal = repliesService.getRowTotalByUser(user);
        PageBean pb = new PageBean(thisPage,pageSize,dataTotal);

        int startIndex = pb.getStartIndex();
        pb.setList(repliesService.getRepliesWithPageByUser(user,startIndex,pageSize));

        JSONObject jsonObject = JSONObject.fromObject(pb);

         return jsonObject;
    }
}
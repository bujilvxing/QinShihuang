package com.bjlx.QinShihuang.controller;

import com.bjlx.QinShihuang.core.ColumnAPI;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 专栏相关
 * Created by gaomin on 2016/11/12
 */
@Controller
public class ColumnController {

    /**
     * 取得专栏1015
     * @return 专栏列表
     */
    @RequestMapping(value = "/app/misc/columns",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public @ResponseBody String getColumns(){
        try {
            return ColumnAPI.getColumns();
        }catch (Exception e){
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取得首页1016
     * @return banner列表
     */
    @RequestMapping(value = "/app/misc/banners",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public @ResponseBody String getBanners(){
        try{
            return ColumnAPI.getBanners();
        }catch (Exception e){
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取得商品列表(特产等)1017
     * @return 取得商品列表(特产等)
     */
    @RequestMapping(value = "/app/marketplace/commodities",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getColumnCommodities(){
        try {
            return ColumnAPI.getColumnCommoditys();
        } catch(Exception e){
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取得首页攻略列表1019
     * @return 取得首页攻略列表
     */
    @RequestMapping(value = "/app/columnguides",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public @ResponseBody String getColumnGuides(){
        try{
            return ColumnAPI.getColumnGuides();
        }catch (Exception e){
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }
}

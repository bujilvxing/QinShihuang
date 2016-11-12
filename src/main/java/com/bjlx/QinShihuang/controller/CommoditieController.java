package com.bjlx.QinShihuang.controller;

import com.bjlx.QinShihuang.core.CommoditieAPI;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 商品相关
 * Created by gaomin on 2016/11/6.
 */
@Controller
public class CommoditieController {

    /**
     * 取得商品列表(特产等)1016
     * @return
     */
    @RequestMapping(value = "/app/marketplace/commodities",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getCommoditysByPage(String category,Integer offset,Integer limit ){
        Integer defaultOffset = 0;
        Integer defaultLimit = 200;
        try{
            return CommoditieAPI.getCommoditysByPage(category,offset,limit);
        }catch(Exception ex){
            return QinShihuangResult.getResult(ErrorCode.ServerException);
        }
    }

    /**
     * 取得商品详情(特产等)1017
     * @param commodityId
     * @return
     */
    @RequestMapping(value = "/app/marketplace/commodities/{commodityId:\\d+}",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public @ResponseBody String getCommodity(@PathVariable(value = "commodityId") Long commodityId){
        try{
            return CommoditieAPI.getCommodity(commodityId);
        }catch (Exception ex){
            return QinShihuangResult.getResult(ErrorCode.ServerException);
        }
    }
}

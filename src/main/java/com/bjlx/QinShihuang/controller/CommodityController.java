package com.bjlx.QinShihuang.controller;

import com.bjlx.QinShihuang.core.CommodityAPI;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品控制器
 * Created by pengyt on 2016/11/12.
 */
@Controller
public class CommodityController {

    /**
     * 取得商品详情(特产等)1017
     * @param commodityId
     * @return
     */
    @RequestMapping(value = "/app/marketplace/commodities/{commodityId}",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getCommodity(@PathVariable(value = "commodityId") String commodityId){
        try{
            return CommodityAPI.getCommodityById(commodityId);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.ServerException);
        }
    }
}

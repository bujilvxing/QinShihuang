package com.bjlx.QinShihuang.controller;

import com.bjlx.QinShihuang.core.GuideAPI;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 攻略相关
 * Created by Administrator on 2016/11/6.
 */
@Controller
public class GuideController {

    /**
     * 取得攻略列表1018
     * @return
     */
    @RequestMapping(value = "/app/guides",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public @ResponseBody String getGuides(){
        try{
            return GuideAPI.getGuides();
        }catch (Exception e){
            return QinShihuangResult.getResult(ErrorCode.ServerException);
        }
    }

    /**
     * 取得攻略详情1019
     * @param guideId 攻略id
     * @return
     */
    @RequestMapping(value = "/app/guides/{guideId:\\d+}",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public @ResponseBody String getGuide(@PathVariable(value = "guideId") Long guideId){
        try{
            return GuideAPI.getGuide(guideId);
        }catch (Exception e){
            return QinShihuangResult.getResult(ErrorCode.ServerException);
        }
    }
}

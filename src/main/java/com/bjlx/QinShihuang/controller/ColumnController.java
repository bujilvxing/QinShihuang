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
     * 取得专栏1014
     * @return
     */
    @RequestMapping(value = "/app/misc/columns",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public @ResponseBody String getColumns(){
        try {
            return ColumnAPI.getColumns();
        }catch (Exception e){
            return QinShihuangResult.getResult(ErrorCode.ServerException);
        }
    }

    /**
     * 取得首页1015
     * @return
     */
    @RequestMapping(value = "/app/misc/banners",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public @ResponseBody String getBanners(){
        try{
            return ColumnAPI.getBanners();
        }catch (Exception e){
            return QinShihuangResult.getResult(ErrorCode.ServerException);
        }
    }
}

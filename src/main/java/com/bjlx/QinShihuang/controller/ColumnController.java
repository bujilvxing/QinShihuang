package com.bjlx.QinShihuang.controller;

import com.bjlx.QinShihuang.core.ColumnAPI;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * Created by gaomin on 2016/11/6.
 */
@Controller
public class ColumnController {

    /**
     * 
     * @return
     * */
    @RequestMapping(value="/app/misc/columns",method= RequestMethod.GET,produces = "application/json:charset=utf-8")
    public @ResponseBody String GetSpecialColumns()
    {
        try{
            return ColumnAPI.getColumns();
        }catch (Exception e1){
            return QinShihuangResult.getResult(ErrorCode.ServerException);
        }
    }
}

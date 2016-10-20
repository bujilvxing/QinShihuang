package com.bjlx.QinShihuang.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户账户相关
 * Created by pengyt on 2016/10/20.
 */
@Controller
public class AccountController {

    /**
     * 测试
     * @param request
     * @return
     */
    @RequestMapping(value = "/app/test", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String test(HttpServletRequest request) {

        return "{\"code\":200,\"msg\":\"成功\"}";
    }
}

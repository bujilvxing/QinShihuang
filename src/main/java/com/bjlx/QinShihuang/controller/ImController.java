package com.bjlx.QinShihuang.controller;

import com.bjlx.QinShihuang.requestmodel.MsgReq;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 即时消息控制器
 * Created by pengyt on 2016/11/8.
 */
@Controller
public class ImController {

    @RequestMapping(value = "/app/messages", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String sendMsg(@RequestHeader("userId") Long userId, @RequestHeader("key") String key, @RequestBody MsgReq msgReq) {
        return null;
    }
}

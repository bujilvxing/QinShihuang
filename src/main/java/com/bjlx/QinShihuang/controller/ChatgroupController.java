package com.bjlx.QinShihuang.controller;

<<<<<<< HEAD
import org.springframework.stereotype.Controller;

/**
 * qunzu
 * Created by Nov on 2016/11/6.
=======
import com.bjlx.QinShihuang.core.ChatgroupAPI;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 群组控制器
 * Created by pengyt on 2016/11/13.
>>>>>>> 65e5d0db0648c03d4bab3589b0a78a8a8a8c7024
 */
@Controller
public class ChatgroupController {

<<<<<<< HEAD
=======
    /**
     * 取得群组信息1070
     * @param chatgroupId 群组id
     * @param key 不羁旅行令牌
     * @return 群组信息
     */
    @RequestMapping(value = "/app/chatgroups/{chatgroupId:\\d+}", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getChatgroup(@PathVariable Long chatgroupId, @RequestHeader("key") Long userId, @RequestHeader("key") String key) {
        // 参数校验
        try {
            return ChatgroupAPI.getChatgroup(chatgroupId, userId, key);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }
>>>>>>> 65e5d0db0648c03d4bab3589b0a78a8a8a8c7024
}

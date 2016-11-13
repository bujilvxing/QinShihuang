package com.bjlx.QinShihuang;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用于测试
 * Created by xiaozhi on 2016/10/21.
 */
public class Sandbox {

    public static void sendMsg() {
        /**
         * 个推
         * AppId:KM4CsH2Yam6gcPzLUJaUh8
         * AppSecret:Mk5CgJ7xC18HNlOuATO026
         * MasterSecret:Wl4SJGq5kuABxz7PGg42U3
         * AppKey:x0Mte9wA5I9X0fiIMQD1x4
         */

        String appId = "KM4CsH2Yam6gcPzLUJaUh8";
        String appSecret = "Mk5CgJ7xC18HNlOuATO026";
        String masterSecret = "Wl4SJGq5kuABxz7PGg42U3";
        String appKey = "x0Mte9wA5I9X0fiIMQD1x4";

        String url = "http://sdk.open.api.igexin.com/apiex.htm";

        IGtPush push = new IGtPush(url, appKey, masterSecret);

        // 定义"点击链接打开通知模板"，并设置标题、内容、链接
        LinkTemplate template = new LinkTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
//        template.setLogo("http://oe7hx2tam.bkt.clouddn.com/default_user_avatar.jpg");
        template.setTitle("小指");
        template.setText("好爱你啊~");
        template.setUrl("http://www.baidu.com");

        List<String> appIds = new ArrayList<String>();
        appIds.add(appId);

        // 定义"AppMessage"类型消息对象，设置消息内容模板、发送的目标App列表、是否支持离线发送、以及离线消息有效期(单位毫秒)
        AppMessage message = new AppMessage();
        message.setData(template);
        message.setAppIdList(appIds);
        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 600);

        IPushResult ret = push.pushMessageToApp(message);
        System.out.println(ret.getResponse().toString());
    }

    public static void main(String[] args) {
        List<Long> participants = Arrays.asList(1L,2L);
        List<Long> filtered = participants.stream().filter(item -> !item.equals(1L)).collect(Collectors.toList());
        for(Long id : filtered) {
            System.out.println(id);
        }
    }
}

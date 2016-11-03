package com.bjlx.QinShihuang.core.formatter.account;

import com.bjlx.QinShihuang.core.formatter.activity.ActivityBasicSerializer;
import com.bjlx.QinShihuang.core.formatter.misc.ImageItemSerializer;
import com.bjlx.QinShihuang.core.formatter.misc.TravelNoteBasicSerializer;
import com.bjlx.QinShihuang.core.formatter.trace.TraceBasicSerializer;
import com.bjlx.QinShihuang.core.formatter.tripplan.TripPlanBasicSerializer;
import com.bjlx.QinShihuang.model.account.OAuthInfo;
import com.bjlx.QinShihuang.model.account.PhoneNumber;
import com.bjlx.QinShihuang.model.account.RealNameInfo;
import com.bjlx.QinShihuang.model.account.UserInfo;
import com.bjlx.QinShihuang.model.activity.Activity;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.misc.TravelNote;
import com.bjlx.QinShihuang.model.trace.Trace;
import com.bjlx.QinShihuang.model.tripplan.TripPlan;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Created by pengyt on 2016/10/28.
 */
public class UserInfoFormatter {
    public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(UserInfo.class, new UserInfoSerializer());
        module.addSerializer(ImageItem.class, new ImageItemSerializer());
        module.addSerializer(PhoneNumber.class, new PhoneNumberSerializer());
        module.addSerializer(RealNameInfo.class, new RealNameInfoSerializer());
        module.addSerializer(OAuthInfo.class, new OAuthInfoSerializer());
        module.addSerializer(Trace.class, new TraceBasicSerializer());
        module.addSerializer(Activity.class, new ActivityBasicSerializer());
        module.addSerializer(TravelNote.class, new TravelNoteBasicSerializer());
        module.addSerializer(TripPlan.class, new TripPlanBasicSerializer());
        mapper.registerModule(module);
        return mapper;
    }
}

package com.bjlx.QinShihuang.model.activity;

import com.bjlx.QinShihuang.model.misc.Contact;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Transient;

import java.util.List;

/**
 * 参与人
 * Created by xiaozhi on 2016/12/6.
 */
@Embedded
public class Joiner extends Contact {

    @Transient
    public final static String fd_userId = "userId";
    @Transient
    public final static String fd_name = "name";
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 参与者姓名
     */
    private String name;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Joiner() {

    }

    public Joiner(Long userId, String name, List<String> phoneList, List<String> cellphoneList, String qq, String weixin, String sina, String fax, String email, String website) {
        super(phoneList, cellphoneList, qq, weixin, sina, fax, email, website);
        this.userId = userId;
    }
}

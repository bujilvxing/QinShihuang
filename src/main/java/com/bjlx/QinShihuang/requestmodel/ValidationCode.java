package com.bjlx.QinShihuang.requestmodel;

/**
 * 接收验证码接口相关参数
 * 所有字段定义成Object对象类型
 *
 * Created by xiaozhi on 2016/10/21.
 */
public class ValidationCode {

    /**
     * 登录账户
     * 可能是手机号，邮箱号
     */
    private String account;

    /**
     * 验证码的用途
     * 1表示新用户注册；2表示用户绑定手机号；3表示用户找回密码
     */
    private Integer action;

    /**
     * 验证码
     */
    private String validationCode;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public String getValidationCode() {
        return validationCode;
    }

    public void setValidationCode(String validationCode) {
        this.validationCode = validationCode;
    }
}

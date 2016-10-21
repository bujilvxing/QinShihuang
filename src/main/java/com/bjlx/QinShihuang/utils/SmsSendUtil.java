package com.bjlx.QinShihuang.utils;

import com.cloopen.rest.sdk.CCPRestSmsSDK;

import java.util.HashMap;

/**
 * 发送验证码工具
 * @author xiaozhi
 *
 */
public class SmsSendUtil {
	
	private static CCPRestSmsSDK restAPI;
	
	private static String templateId ;

	/**
	 * 发送验证码初始化
	 */
	static{
		// 初始化SDK
		restAPI = new CCPRestSmsSDK();

		/**
		 * 初始化服务器地址和端口
		 * 沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");
		 * 生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883");
		 */
		restAPI.init("app.cloopen.com", "8883");

		/**
		 * 初始化主帐号和主帐号令牌,对应官网开发者主账号下的ACCOUNT SID和AUTH TOKEN
		 * ACOUNT SID和AUTH TOKEN在登录官网后，在"应用-管理控制台"中查看开发者主账号获取
		 * 参数顺序：第一个参数是ACOUNT SID，第二个参数是AUTH TOKEN。
		 */
		restAPI.setAccount("aaf98f8951af2ba80151c359def7504b", "91891df1e2ad4e88be1f7505265b80f1");

		/**
		 * 初始化应用ID
		 * 测试开发可使用"测试Demo"的APP ID，正式上线需要使用自己创建的应用的App ID
		 * 应用ID的获取：登陆官网，在"应用-应用列表"，点击应用名称，看应用详情获取APP ID
		 */
		restAPI.setAppId("aaf98f8951af2ba80151c35f298a508d");
		
		/**
		 * 模板编号
		 */
		templateId = "58156";
	}
	
	/**
	 * 通过模板发送消息
	 * @param mobile 手机号
	 * @param data 消息数据
	 */
	public static int sendMessageByTemplate(String mobile, String[] data) {
		HashMap<String, Object> result = restAPI.sendTemplateSMS(mobile, templateId, data);
		Object statusCodeObj = result.get("statusCode");
		if(statusCodeObj == null) {
			return -1;
		} else {
			String statusCode = statusCodeObj.toString();
			switch(statusCode) {
				case "000000": return 1;
				case "160038": return 2;
				case "160039": return 3;
				case "160040": return 4;
				case "160041": return 5;
				default : return 6;
			}
		}
	}
}
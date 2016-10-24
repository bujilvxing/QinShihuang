package com.bjlx.QinShihuang.utils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Map;

public class OctopusToolBase {
	protected Boolean isExperimenting = false;
	public static final String header_banner = "$======================================================$";
	private OctopusMailer mailer = null;

	public static void shellRun(String cmd) {
		try {
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/***
	 * This function is used for retrieving environment parameter value
	 * @param paramName
	 * @return
	 * @author yangdo
	 */
	public static String param(String paramName) {
		return System.getenv().get(paramName);
	}

	/***
	 * Simply sleep for specified seconds
	 * @param second
	 * @author yangdo
	 */
	public static void sleep(int second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/***
	 * To display a string map in out put log
	 * @param map
	 * @author yangdo
	 */
	public void printStringMap(Map<String, String> map) {
		System.out.println("");
		System.out.println("==================Map Begin==================");
		for (String key : map.keySet()) {
			System.out.println(key + " => " + map.get(key));
		}
		System.out.println("==================Map  End ==================");
		System.out.println("");
	}



	/***
	 * Generate a file name that has the same main part with the class file
	 * yet has a different surfix.
	 * @param surfix
	 * @return The expanded file name
	 */
	public String expandFileName(String surfix) {
		String resPath = getClass().getPackage().getName().replace('.', '/');
		resPath = getClass().getClassLoader().getResource(resPath).getPath()
				+ "/" + getClass().getSimpleName() + surfix;
		return resPath;
	}

	public String getResPath(String resFile) {
		String resPath = getClass().getPackage().getName().replace('.', '/') + "/" + resFile;
		URL res = getClass().getClassLoader().getResource(resPath);
		if (res == null)
			throw new RuntimeException("Res file not found: [" + resPath + "]");
		resPath = res.getPath();
		return resPath;
	}

	public String getResPath(Class<?> c, String resFile) {
		String resPath = c.getPackage().getName().replace('.', '/') + "/" + resFile;
		URL res = getClass().getClassLoader().getResource(resPath);
		if (res == null)
			throw new RuntimeException("Res file not found: [" + resPath + "]");
		resPath = res.getPath();
		return resPath;
	}




	/***
	 * This function is for running simple sequential function calls which is
	 * composed in a string. It's often a cell content of a spread sheet.
	 * The script is something like this: 
	 * fun1(100,200);fun2(aaa,bbb);fun3();
	 * Yet the implementation of each available function should be started
	 * with prefix 'api_'. Thus, there should be member functions declared as 
	 * following according to the example above:
	 * void api_fun1(String s1,String s2)
	 * void api_fun2(String s1,String s2)
	 * void api_fun3()
	 * If we need to represent an empty string parameter,
	 * please use a space char, something like fun1(100, ), or fun1( ). Without
	 * the space char, it will be regarded as no parameter.
	 * @param script
	 * @return
	 * @author yangdo
	 */
	public boolean runTinyScript(String script) {
		script = script.trim();
		if (!script.isEmpty()) {
			String[] scripts = script.split(";");
			for (String line : scripts) {
				line = line.trim();
				if (line.isEmpty())
					continue;
				int idx1 = line.indexOf("(");
				int idx2 = line.lastIndexOf(")");
				if ((idx2 > idx1) && (idx1 > 0)) {
					String methodName = line.substring(0, idx1).trim();
					if (methodName.isEmpty()) {
						System.out.println("Function name missed: " + line);
						return false;
					}
					String param = line.substring(idx1 + 1, idx2);
					String[] params;
					Class<?> paramTypes[];
					if (param.isEmpty()) {
						paramTypes = new Class<?>[0];
						params = new String[0];
					} else {
						params = param.split(",");
						for (int i = 0; i < params.length; i++)
							params[i] = params[i].trim();
						paramTypes = new Class<?>[params.length];
						for (int i = 0; i < paramTypes.length; i++)
							paramTypes[i] = String.class;
					}
					try {
						Method method = null;
						Class<?> _class = getClass();
						while (method == null) {
							try {
								method = _class.getMethod(
										"api_" + methodName, paramTypes
										);
							} catch (NoSuchMethodException e) {
							}
							_class = _class.getSuperclass();
							if (_class == Object.class)
								break;
						}
						if (method == null) {
							System.out.println("Function not found: " + methodName);
							return false;
						}

						method.invoke(this, (Object[]) params);
					} catch (Exception e) {
						e.printStackTrace();
						return false;
					}
				} else {
					System.out
							.println("Bad expression syntax: " + line);
					return false;
				}
			}
		}
		return true;
	}

	public Object invoke(Object delegate, Method method, Object[] args)
			throws Throwable {
		int paramCount = method.getParameterTypes().length;
		Object[] mtdArgs = new Object[paramCount];
		System.arraycopy(args, 0, mtdArgs, 0, paramCount);
		return method.invoke(delegate, mtdArgs);
	}

	/***
	 * Call an evaluation function for making decisions.
	 * @param methodName
	 * @param param
	 * @return
	 */
	public boolean invokeEvaluationMethod(String methodName, String param) {
		Class<?> paramTypes[] = new Class<?>[1];
		paramTypes[0] = String.class;
		String[] params = { param };
		try {
			Method method = null;
			Class<?> _class = getClass();
			while (method == null) {
				try {
					method = _class.getMethod(
							"eval_" + methodName, paramTypes
							);
				} catch (NoSuchMethodException e) {
				}
				_class = _class.getSuperclass();
				if (_class == Object.class)
					break;
			}
			if (method == null) {
				throw new RuntimeException("Function not found: " + methodName);
			}
			return (Boolean) method.invoke(this, (Object[]) params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Excetions occurred when executing evaluate method");
		}
	}

	/***
	 * We assume if we have a method's name starts with 2 underscores, it's an
	 * experimenting methods
	 */
	public void detectExperimentalMethods() {
		for (Method m : getClass().getMethods()) {
			if (m.getName().startsWith("__")) {
				isExperimenting = true;
				System.out
						.println("====================================================");
				System.out
						.println("============Experimental Methods Detected===========");
				System.out
						.println("====================================================");
				return;
			}
		}
	}

	/***
	 * Run all the experimenting methods, whose name starts with 2 underscores
	 */
	public void testExperimentalMethods() {
		for (Method method : getClass().getMethods()) {
			if (method.getName().startsWith("__")) {
				isExperimenting = true;
				try {
					int paramCount = method.getParameterTypes().length;
					Object[] mtdArgs = new Object[paramCount];
					for (int i = 0; i < method.getParameterTypes().length; i++)
						mtdArgs[i] = null;
					System.out.println("=>>>>>>>Experimenting with function '"
							+ method.getName() + "'");
					method.invoke(this, mtdArgs);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}


	public void setupMailer(String mailServerHost, String mailServerPort, String userName, String password) {
		mailer = new OctopusMailer(mailServerHost, mailServerPort, userName, password);
	}

	public void setupMailer(String mailServerHost, String userName, String password) {
		mailer = new OctopusMailer(mailServerHost, userName, password);
	}

	public boolean sendEmail(String mailSubject, String mailBody, String fromAddress, String toAddress,
			String ccAddress, String attatchment) {
		if (mailer == null)
			throw new RuntimeException("Mailer has not been initialized!");
		return mailer.sendHtmlMail(mailSubject, mailBody, fromAddress, toAddress, ccAddress, attatchment);
	}

	public boolean sendEmail(String mailSubject, String mailBody, String fromAddress, String toAddress,
			String ccAddress) {
		if (mailer == null)
			throw new RuntimeException("Mailer has not been initialized!");
		return mailer.sendHtmlMail(mailSubject, mailBody, fromAddress, toAddress, ccAddress);
	}

	public boolean sendSimpleEmail(String mailSubject, String mailBody, String fromAddress,
			String toAddress, String ccAddress) {
		if (mailer == null)
			throw new RuntimeException("Mailer has not been initialized!");
		return mailer.sendTextMail(mailSubject, mailBody, fromAddress, toAddress, ccAddress);
	}

	public static void sendEmail(String email) {
		OctopusToolBase instance = new OctopusToolBase();
//		instance.setupMailer("smtp.163.com", "25", "pengyt19890703@163.com", "19890703pyt0422");
		instance.setupMailer("smtp.exmail.qq.com", "25", "service@bujilvxing.com", "BuJiLvXing168");
		// bihbohnlzbhccahh
		instance.sendSimpleEmail("不羁旅行验证码", "您的验证码为:623309", "service@bujilvxing.com", email, "");
	}
}

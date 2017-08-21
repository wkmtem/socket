package socket.server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import socket.Request;

/**
 * 
 * @Class Name: ServiceRegister
 * @Description: 注册类：通过register()注册服务，通过getService()获取服务名对应的方法
 * @author: wkm
 * @Company: www.compass.com
 * @Create date: 2017年7月20日下午4:39:21
 * @version: 2.0
 */
public class ServiceRegister {

	public int arg1;
	public int arg2;
	public Map<String, String> map = null;

	public ServiceRegister(Request request) {
		this.arg1 = request.getArg1();
		this.arg2 = request.getArg2();
		this.init();
	}

	public void init() {
		map = new HashMap<>();
	}

	/**
	 * 注册服务
	 */
	public void resigte(String servicename) { 
		if (map.containsKey(servicename)) {
			 System.out.println("service is already exist!do have to resigter"); 
		} else { 
			map.put(servicename,servicename); 
		} 
	}
	 
	
	/**
	 * 反射获取服务类方法
	 */
	public String getServicebyReflect(String callName) throws ClassNotFoundException {
		
		Object obj = null;
		Method method;
		Class<?>[] classes = new Class<?>[2];
		classes[0] = int.class;
		classes[1] = int.class;

		System.out.println("反射调用方法 >>>>>>>>>> " + callName);
		Class<?> services = Services.class;
		
		try {
			try {
				// 反射获取Services类的add方法
				if (callName.equals("add")) {
					method = services.getMethod(callName, classes);
					obj = method.invoke(services.newInstance(), arg1, arg2);
					System.out.println(callName + " 方法返回值 >>>>>>>>>> " + method.invoke(services.newInstance(), arg1, arg2));
					
				// 反射获取Services类的sayHello方法
				} else if(callName.equals("sayHello")){
					method = services.getMethod(callName);
					obj = method.invoke(services.newInstance());
					System.out.println(callName + " 方法返回值 >>>>>>>>>> " + method.invoke(services.newInstance()));
					
				// 反射获取Services类的其它方法	
				} else {
					method = services.getMethod(callName);
					obj = method.invoke(services.newInstance());
					System.out.println(callName + " 方法返回值 >>>>>>>>>> " + method.invoke(services.newInstance()));
				}
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | InstantiationException e) {
				System.out.println("method invoke error!");
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			System.out.println("No such reflect method");
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return obj.toString();
	}

}

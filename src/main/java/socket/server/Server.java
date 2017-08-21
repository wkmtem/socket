package socket.server;

import socket.Request;
import socket.Response;

/**
 * 
 * @Class Name: Server
 * @Description: 
 * @author: wkm
 * @Company: www.compass.com
 * @Create date: 2017年7月20日下午6:47:38
 * @version: 2.0
 */
public class Server {

	/**
	 * 注册服务
	 */
	public String procRequest(String reqStr) { // 接收报文
		
		// 解码为对象
		Request request = this.decode(reqStr); 
		int id = request.getId();
		String command = request.getCommand();
		
		Response response = new Response();
		response.setId(id);
		
		String result = null;
		
		// 初始化注册器
		ServiceRegister serviceRegister = new ServiceRegister(request); 
		// 注册服务
		serviceRegister.resigte(command);
		// 反射获取方法并执行后的结果
		try {  
			result = serviceRegister.getServicebyReflect(command);
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        } 
		response.setResult(result);
		// 编码返回客户端
		return this.encode(response); 
	}

	/**
	 * 编码
	 */
	private String encode(Response response) {
		return "req:id" + response.getId() + "result" + response.getResult();
	}

	/**
	 * 解码
	 */
	private Request decode(String reqStr) {
		Request request = new Request();

		if (!reqStr.startsWith("req:")) {
			System.err.println("it is not a request data");
		} else {
			int id = Integer.parseInt(reqStr.substring(
					reqStr.indexOf("id") + 2, reqStr.indexOf("command")));
			String command = reqStr.substring(reqStr.indexOf("command") + 7,
					reqStr.indexOf("arg1"));
			int arg1 = Integer.parseInt(reqStr.substring(
					reqStr.indexOf("arg1") + 4, reqStr.indexOf("arg2")));
			int arg2 = Integer
					.parseInt(reqStr.substring(reqStr.indexOf("arg2") + 4));

			request.setId(id);
			request.setCommand(command);
			request.setArg1(arg1);
			request.setArg2(arg2);
		}
		return request;
	}
}

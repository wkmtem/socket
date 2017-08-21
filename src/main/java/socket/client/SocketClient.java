package socket.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import socket.Response;
import socket.server.Server;

/**
 * 
 * @Class Name: SocketClient
 * @Description: 
 * @author: wkm
 * @Company: www.compass.com
 * @Create date: 2017年7月20日下午6:44:30
 * @version: 2.0
 */
public class SocketClient {

	public static void main(String[] args) {
		
		Socket socket = null;
		PrintWriter out = null;
		
		Client client = new Client(new Server());
		client.setReqId(1);
		client.setReqCommand("add");
		//client.setReqCommand("sayHello");
		client.setReqArg1(1);
		client.setReqArg2(2);
		
		String reqStr = client.getRequest();
		
		try {
			socket = new Socket("192.168.1.189", 12345);
			out = new PrintWriter(socket.getOutputStream());
			// 写出请求数据至server
			out.println(reqStr);
			out.flush();

			// 获取server返回
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String rspStr = in.readLine();
			System.out.println("response form Server:" + rspStr);
			
			Response response = client.decode(rspStr);
			System.out.println("decode result >>>>>>>>>> " + response.getResult());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != socket) {
					socket.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}

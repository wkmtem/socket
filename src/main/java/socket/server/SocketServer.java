package socket.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 
 * @Class Name: SocketServer
 * @Description: 
 * @author: wkm
 * @Company: www.compass.com
 * @Create date: 2017年7月21日上午11:55:45
 * @version: 2.0
 */
public class SocketServer {
	
	public static void main(String[] args) throws IOException{
		
		int port = 12345;
		String address = "192.168.1.189";
		ServerSocket serverSocket = null;
		Executor executorService = null;
		Socket clientSocket = null;
		boolean flag = true;
		System.out.println("Socket Server 已启动，监听客户端连接 ...");
		
		// 创建服务器socket对象、ExecutorService实例
		try {
			serverSocket = new ServerSocket();
			// ServerSocket对象绑定的IP地址
			serverSocket.bind(new InetSocketAddress(address, port));
			// 创建ExecutorService线程池实例，是Executor的子接口，提供更高级的工具来关闭服务器（正常、非正常关闭）
			executorService = Executors.newCachedThreadPool();
			
			while(flag){
				clientSocket = serverSocket.accept(); // 阻塞
				System.out.println("发现客户端尝试连接本机：端口号 >>>>>>>>>> " + port);
				
				// ServerSocket对象未绑定IP地址，返回0.0.0.0
				System.out.println("服务端IP >>>>>>>>>> " + serverSocket.getInetAddress());
				System.out.println("服务端口 >>>>>>>>>>> " + serverSocket.getLocalPort());
				System.out.println("客户端IP >>>>>>>>>> " + clientSocket.getInetAddress());
				System.out.println("客户端口 >>>>>>>>>>> " + clientSocket.getPort());
				
				// 调用execute()方法时，首先尝试使用已有的线程，如有必要，创建一个新的线程来处理任务
				// 空闲60秒以上的线程，则从线程池移除
				// 高效的任务Executor内部排队机制，非网络系统中排队
				executorService.execute(new ServerThread(clientSocket));
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(null != serverSocket){
				serverSocket.close();
			}
		}
    }
}

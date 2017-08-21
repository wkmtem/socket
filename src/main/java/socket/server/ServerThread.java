package socket.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import org.apache.commons.lang3.StringUtils;


public class ServerThread implements Runnable {
	
	private Socket clientSocket;
	
    public ServerThread(Socket clientSocket){
        this.clientSocket = clientSocket;
    }
     
    /**
     * 
     * @Method Name: execute
     * @Description: 处理通信细节的静态方法，这里主要是方便线程池服务器的调用
     * @params:
     * @author: wkm
     * @version: 2.0
     * @Create date: 2017年7月21日上午11:23:38
     * @param client:
     */
    public static void execute(Socket clientSocket){
    	
    	boolean flag = true;
    	PrintStream out = null;
    	BufferedReader bufr = null;
    	Server server = null;
    	
        try{
        	// 获取clientSocket输入流，接收客户端数据
        	bufr = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // 获取clientSocket的输出流，返回客户端数据  
            out = new PrintStream(clientSocket.getOutputStream());
            // 注冊并调用服务
            server = new Server(); 
            
            while(flag){
                // 接收客户端数据 
                String line =  bufr.readLine();
                if(StringUtils.isBlank(line)){
                    flag = false;
                }else{
                    if("bye".equals(line)){
                        flag = false;
                    }else{
                    	System.out.println("接收到客户端数据 >>>>>>>>>> " + line);
                        // 返回客户端数据 
                        out.println(server.procRequest(line));
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
        	try {
        		if (null != out) {
        			out.close();
        		}
        		if (null != bufr) {
        			bufr.close();
        		}
        		if (null != clientSocket) {
        			clientSocket.close();
        		}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
        }
    }
    
    @Override
    public void run() {
        execute(clientSocket);
    }
}

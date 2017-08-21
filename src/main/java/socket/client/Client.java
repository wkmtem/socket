package socket.client;

import socket.Request;
import socket.Response;
import socket.server.Server;

public class Client {

	public Server remoteServer;
	Request request = new Request(); 
	
	public Client(Server remoteServer){
		this.remoteServer = remoteServer;
	}
	
	public void setReqId(int id) {  
		request.setId(id);  
	}  
	public void setReqCommand(String command) {  
		request.setCommand(command);  
	} 
	public void setReqArg1(int arg1) {
		request.setArg1(arg1);
	}
	public void setReqArg2(int arg2) {
		request.setArg2(arg2);
	}
	public String getRequest() {
		return request.getRequest(); 
	}
	
	public String getResponse(String reqStr) {  
		Response response = this.decode(remoteServer.procRequest(reqStr));
		return response.getResult();
	} 
	
	public String sayHello(){
		Request request = new Request(1, "sayHello");
		return this.getResponse(request.getRequest());
	}
	
	public String add(int a, int b) {  
		Request request = new Request(2, "add");
		request.setArg1(a);  
		request.setArg2(b);  
	    return getResponse(request.getRequest());  
	}  
	
	public Response decode(String rspStr) {  
		Response response = new Response();  
		
	    if(!rspStr.startsWith("req:")) {  
	    	System.out.println("it is not a response data");  
	    } else {  
	    	response.setId(Integer.parseInt(rspStr.substring(rspStr.indexOf("id") + 2, rspStr.indexOf("result")))); 
	        response.setResult(rspStr.substring(rspStr.indexOf("result") + 6));
	    }  
	    return response;  
	}  
}

package socket;

public class Request {
	public int id;  
	public String command;
	public int arg1;
	public int arg2;
	
	public Request() {};
	
	public Request(int id, String command) {
		this.id = id;
		this.command = command;
	}
	
	public String getRequest() {
		return "req:id" + id + "command" + command + "arg1" + arg1 + "arg2" + arg2;  
	}
	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public int getArg1() {
		return arg1;
	}
	public void setArg1(int arg1) {
		this.arg1 = arg1;
	}
	public int getArg2() {
		return arg2;
	}
	public void setArg2(int arg2) {
		this.arg2 = arg2;
	} 
}

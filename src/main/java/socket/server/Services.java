package socket.server;

public class Services {

	public String sayHello() {
		return "Hello";
	}

	public String add(int arg1, int arg2) {
		return Integer.toString(arg1 + arg2);
	}
}

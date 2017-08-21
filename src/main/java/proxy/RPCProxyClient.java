package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 
 * @Class Name: RPCProxyClient
 * @Description: RPC客户端
 * @author: wkm
 * @Company: www.compass.com
 * @Create date: 2017年7月20日上午10:02:09
 * @version: 2.0
 */
public class RPCProxyClient implements InvocationHandler{

	private Object obj;
	
	public RPCProxyClient(Object obj) {
		this.obj = obj;
	}
	
	/**
	 * 获取被代理对象
	 */
	public static Object getProxy(Object obj) {
		// ClassLoader loader, Class<?>[] interfaces, InvocationHandler handler
		return Proxy.newProxyInstance(
				obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(), 
                new RPCProxyClient(obj));
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		return new Object();
	}

}

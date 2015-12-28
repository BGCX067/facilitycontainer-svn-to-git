package sc.facilitycontainer.support.proxy;

import sc.facilitycontainer.support.FactoryComponent;
import sc.utils.proxy.Decorator;
import sc.utils.proxy.ProxyManager;
import sc.utils.proxy.DefaultProxyManager;

/**
 * 代理工厂组件.
 * 改组件将会返回某对象行为增强的动态代理.将行为的修饰放在Decorator接口之中.
 *每次getObject()都将返回一个新的代理对象.
 * 
 * @author suchen
 * @time 2008-9-1 上午11:18:31
 * @email xiaochen_su@126.com
 */
public class ProxyFactoryComponent implements FactoryComponent {
	private Object target = null;
	private Decorator decorator = null;
	private String[] methodNames = null;
	
	private ProxyManager proxyManager = null;
	private Object proxy = null;
	
	public ProxyFactoryComponent() {
		
	} 
	
	public Object getObject() {
		// TODO Auto-generated method stub
		if(proxyManager == null) {
			synchronized(this) {
				if(proxyManager == null) {
					proxyManager = SimpleProxyManagerFactory.createProxyManager(methodNames, decorator, target);
					proxy = proxyManager.getProxy();
				}
			}
		}
		
		return proxy;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public Decorator getDecorator() {
		return decorator;
	}

	public void setDecorator(Decorator decorator) {
		this.decorator = decorator;
	}

	public String[] getMethodNames() {
		return methodNames;
	}

	public void setMethodNames(String[] methodNames) {
		this.methodNames = methodNames;
	}

	//	update	2009-05-18 21:22:20
}

class SimpleProxyManagerFactory {
	public static ProxyManager createProxyManager(String[] methodNames, Decorator decorator, Object target) {
		return new DefaultProxyManager(methodNames, decorator, target);
	}
}
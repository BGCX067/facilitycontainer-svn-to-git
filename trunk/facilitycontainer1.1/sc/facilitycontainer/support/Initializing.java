/*
 * facilitycontainer1.1
 * 该软件包 提供钩子使用户可以干预容器构造对管理对象的方式.
 * 
 */
package sc.facilitycontainer.support;

/**
 * 初始化接口
 * 		实现该接口的组件,容器会在初始化组件时刻调用该接口的初始化方法.
 * 
 * @author suchen
 * @time 2008-4-5 下午03:09:16
 * @email xiaochen_su@126.com
 */
public interface Initializing {
	
	/**
	 * 组件初始化时想做的事情放在这里.
	 */
	public void initializing();
}

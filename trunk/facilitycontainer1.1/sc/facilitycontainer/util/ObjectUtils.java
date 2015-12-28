/*
 * facilitycontainer1.1
 * 该软件包提供 供容器使用的静态方法, 处理类型,对象字符串等. 
 */
package sc.facilitycontainer.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 对象工具类.
 *	1.以下注入依赖的方法都要求参数的顺序.
 *	 
 * @author suchen
 * @time 2008-4-13 上午09:36:09
 * @email xiaochen_su@126.com
 */
public final class ObjectUtils {
	private ObjectUtils() {
		
	}
	
	/**
	 * 向组件中的指定的单个方法注入依赖.
	 * 
	 * @param component Object 组件.
	 * @param method Method 需要依赖的方法对象.
	 * @param params Object[]方法对象所依赖的参数.
	 * 
	 * @throws IllegalArgumentException	注入依赖时可能会发生该异常.
	 * @throws IllegalAccessException 注入依赖时可能会发生该异常.
	 * @throws InvocationTargetException 注入依赖时可能会发生该异常.
	 */
	public static void dependencyInjection(Object component, Method method, Object[] params) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		method.invoke(component, params);
	}
	
	/**
	 * 向组件中的指定的多个方法注入依赖.
	 * 
	 * @param component Object 组件.
	 * @param methods Method[] 需要依赖的多个方法对象.
	 * @param paramGroup Object[][] 方法对象组.
	 * 
	 * @throws IllegalArgumentException 注入依赖时可能会发生该异常.
	 * @throws IllegalAccessException 注入依赖时可能会发生该异常.
	 * @throws InvocationTargetException 注入依赖时可能会发生该异常.
	 */
	public static void dependencyInjection(Object component, Method[] methods, Object[][] paramGroup) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		for(int i = 0; i < methods.length; i++) {
			methods[i].invoke(component, paramGroup[i]);
		}
	}
	
	/**
	 * 相组件中的多个方法注入依赖,每个方法仅仅依赖一个对象;用来执行setter方法的注入依赖.
	 * 
	 * @param component Object 组件.
	 * @param methods Method[] 需要依赖的多个方法对象.
	 * @param params Object[] 方法对象组.
	 * 
	 * @throws IllegalArgumentException 注入依赖时可能会发生该异常.
	 * @throws IllegalAccessException 注入依赖时可能会发生该异常.
	 * @throws InvocationTargetException 注入依赖时可能会发生该异常.
	 */
	public static void dependencyInjection(Object component, Method[] methods, Object[] params) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		for(int i = 0; i < methods.length; i++) {
			methods[i].invoke(component, params[i]);
		}
	}
}

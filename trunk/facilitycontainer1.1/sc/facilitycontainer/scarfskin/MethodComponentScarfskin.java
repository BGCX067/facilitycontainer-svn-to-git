/*
 * facilitycontainer1.1
 * 该软件包提供外皮对象的基本实现.
 */
package sc.facilitycontainer.scarfskin;

import java.lang.reflect.Method;
import java.util.Map;

import sc.facilitycontainer.ComponentScarfskin;
import sc.facilitycontainer.FacilityContainer;

import sc.facilitycontainer.Parameter;

/**
 * 可以调用各种方法的外皮对象.
 * 		1.该实现方法执行的顺序根据methodCache.ketSet().iterator()迭代器中返回的顺序而定.
 * 		2.当第一次成功的找到并执行methodCache中的方法,转换后的结果将会被缓存.
 * 
 * @author suchen
 * @time 2008-4-13 上午09:58:21
 * @email xiaochen_su@126.com
 */
public class MethodComponentScarfskin extends AbstractMethodComponentScarfskin {

	/** 方法缓存 */
	private Map<String, Parameter[]> methodCache = null;
	/** 要执行的方法 */
	private Method[] dependentyMethods = null;
	/** 要执行的方法所依赖的参数组 */
	private Object[][] dependentyEntitys = null;
	/** 参数管理器 */
	private static final ManagerParameter METHOD_MANAGER_PARAMETER = new MethodComponentScarfskinManagerParameter();
	
	//	//////////////////
	//	构造函数	
	//	//////////////////
	
	/**
	 * 创建一个可以操作任何可访问的方法的组件外皮对象.
	 * 		根据委托外皮对象的组件所包含的类型中根据形参列表和名称寻找方法并执行它.
	 * 
	 * @param componentScarfskin ComponentScarfskin 委托外皮对象.
	 * @param methodCache Map<String, Parameter[]> 方法缓存.
	 */
	public MethodComponentScarfskin(ComponentScarfskin componentScarfskin, Map<String, Parameter[]> methodCache) {
		super(componentScarfskin);
		
		this.methodCache = methodCache;
	}
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.scarfskin.AbstractMethodComponentScarfskin#manageParameter(sc.facilitycontainer.FacilityContainer)
	 */
	@Override
	protected void manageParameter(FacilityContainer facilityContainer) throws Exception {
		// TODO Auto-generated method stub
		Object[] array = (Object[])METHOD_MANAGER_PARAMETER.manageParameter(facilityContainer, new Object[]{methodCache, componentType});
		
		dependentyMethods = (Method[]) array[0];
		dependentyEntitys = (Object[][]) array[1];
			
	}

	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.scarfskin.AbstractMethodComponentScarfskin#dependentyEntitys()
	 */
	@Override
	protected Object[][] dependentyEntitys() {
		// TODO Auto-generated method stub
		return dependentyEntitys;
	}

	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.scarfskin.AbstractMethodComponentScarfskin#dependentyMethods()
	 */
	@Override
	protected Method[] dependentyMethods() {
		// TODO Auto-generated method stub
		return dependentyMethods;
	}
	
	//	update 2009-04-13 17:55:44
}

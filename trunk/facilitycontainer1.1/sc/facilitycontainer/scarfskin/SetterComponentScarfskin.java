/*
 * facilitycontainer1.1
 * 该软件包提供外皮对象的基本实现.
 */
package sc.facilitycontainer.scarfskin;

import java.lang.reflect.Method;

import sc.facilitycontainer.ComponentScarfskin;
import sc.facilitycontainer.FacilityContainer;
import sc.facilitycontainer.Parameter;


/**
 * 拥有Setter注射依赖的方式外皮对象.
 * 
 * @author suchen
 * @time 2008-4-5 下午08:58:24
 * @email xiaochen_su@126.com
 */
public class SetterComponentScarfskin extends AbstractMethodComponentScarfskin {
	/** 该组件所依赖的参数对象 */
	private Parameter[] parameters = null;
	/** 依赖的实体 */
	private Object[][] dependentyEntitys = null;
	/** 依赖的方法 */
	private Method[] dependentyMethods = null;
	/** 参数管理器 */
	private static final ManagerParameter SETTER_MANAGER_PARAMETER = new SetterComponentScarfskinManagerParameter();
	
	// ///////////////////////////////
	// 构造函数
	// ///////////////////////////////
	
	/**
	 * 构造一个拥有Setter注射依赖的方式外皮对象.
	 * 		根据参数对象和他的名称得到方法对象执行注入操作.
	 * 		
	 * @param componentScarfskin ComponentScarfskin 委托外皮对象.
	 * @param parameters Parameter[] 参数对象. 请确保其中的参数返回的名称是有意义的.
	 */
	public SetterComponentScarfskin(ComponentScarfskin componentScarfskin, Parameter[] parameters) {
		super(componentScarfskin);
		
		this.parameters = parameters;
		this.dependentyEntitys = new Object[parameters.length][];
		this.dependentyMethods = new Method[parameters.length];
	}

	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.scarfskin.AbstractMethodComponentScarfskin#manageParameter(sc.facilitycontainer.FacilityContainer)
	 */
	@Override
	protected void manageParameter(FacilityContainer facilityContainer) throws Exception {
		// TODO Auto-generated method stub
		Object[] array = (Object[])SETTER_MANAGER_PARAMETER.manageParameter(facilityContainer, new Object[]{parameters, componentType});
		
		dependentyMethods = (Method[]) array[0];
		dependentyEntitys = (Object[][]) array[1];
				
	}
	
	@Override
	protected Object[][] dependentyEntitys() {
		// TODO Auto-generated method stub
		return dependentyEntitys;
	}

	@Override
	protected Method[] dependentyMethods() {
		// TODO Auto-generated method stub
		return dependentyMethods;
	}

	//	update 2009-04-13 17:55:44
}

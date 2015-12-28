/*
 * facilitycontainer1.1
 * 该软件包 提供参数对象的基本实现. 包含了对 基本类型,集合类型,引用类型的抽象形式.
 */
package sc.facilitycontainer.parameter;

import sc.facilitycontainer.FacilityContainer;
import sc.facilitycontainer.Parameter;
import sc.facilitycontainer.exception.ParameterException;

/**
 * 别名参数装饰器。
 * 	根据名称与包装的参数对象进行逻辑比较.
 * 
 * @author suchen
 * @time 2008-4-15 下午09:21:37
 * @email xiaochen_su@126.com
 */
public class ByNameParameter implements Parameter {
	/** 委托参数 */
	private Parameter parameter = null;
	/** 参数别名,用在map.key,setter方法 */
	private String name;
	
	/**
	 * 给参数对象取一个别名.
	 * 
	 * @param parameter Parameter 参数对象.s
	 * @param name String 参数对象的别名.
	 */
	public ByNameParameter(Parameter parameter, String name) {
		this.parameter = parameter;
		this.name = name;
	}
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.Parameter#getByName()
	 */
	public String getByName() {
		// TODO Auto-generated method stub
		return name;
	}

	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.Parameter#getEntityType(sc.facilitycontainer.FacilityContainer)
	 */
	public Class<?> getEntityType(FacilityContainer facilityContainer) {
		// TODO Auto-generated method stub
		return parameter.getEntityType(facilityContainer);
	}
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.Parameter#getExpectType(sc.facilitycontainer.FacilityContainer)
	 */
	public Class<?> getExpectType(FacilityContainer facilityContainer) {
		// TODO Auto-generated method stub
		return parameter.getExpectType(facilityContainer);
	}
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.Parameter#getParamEntity(sc.facilitycontainer.FacilityContainer)
	 */
	public Object getParamEntity(FacilityContainer facilityContainer) throws ParameterException {
		// TODO Auto-generated method stub
		return parameter.getParamEntity(facilityContainer);
	}
	
	//	////////////////
	//	逻辑方法
	//	////////////////
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return " [by name parameter] " + name + "#" + parameter;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object anObject) {
		if(this == anObject) {
			return true;
		}
		
		if(!(anObject instanceof ByNameParameter)) {
			return false;
		}
		
		ByNameParameter temp = (ByNameParameter) anObject;
	
		return 	temp.name.equals(name) && temp.parameter.equals(parameter);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		int result = 17;
		
		result = result * name.hashCode();
		result = result * parameter.hashCode();
		
		return result;
	}
	
}

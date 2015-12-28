/*
 * facilitycontainer1.1
 * 该软件包 提供参数对象的基本实现. 包含了对 基本类型,集合类型,引用类型的抽象形式.
 */
package sc.facilitycontainer.parameter;

import sc.facilitycontainer.FacilityContainer;

/**
 * 基本的参数对象形式.
 * 
 * @author suchen
 * @time 2008-4-4 下午05:26:50
 * @email xiaochen_su@126.com
 */
public final class BasicParameter extends AbstractParameter {
	/** 组件的实体 "参数" */
	private Object parameter = null;
	
	//	///////////////////////////
	//	构造函数
	//	///////////////////////////

	/**
	 * 创建一个基本参数对象, 使实体类型与预期类型一致.
	 * 
	 * @param parameter Object 组件的实体.
	 */
	public BasicParameter(Object parameter) {
		this(parameter, parameter.getClass());
	}
	
	/**
	 * 创建一个基本参数对象, 客户提供预期类型.
	 * 
	 * @param parameter Object 组件的实体.
	 * @param expectType Class 预期的类型.
	 */
	public BasicParameter(Object parameter, Class<?> expectType) {
		super(parameter.getClass(), expectType);
		
		this.parameter = parameter;
	}
		
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.Parameter#getParamEntity(sc.facilitycontainer.FacilityContainer)
	 */
	public Object getParamEntity(FacilityContainer facilityContainer) {
		// TODO Auto-generated method stub
		return parameter;
	}
	
	//	/////////////////////////
	//	逻辑方法
	//	/////////////////////////
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.parameter.AbstractParameter#toString()
	 */
	public String toString() {
		return super.toString() + " entity " + parameter;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object anObject) {
		if(super.equals(anObject)) {
			return true;
		}
		
		if(!(anObject instanceof BasicParameter)) {
			return false;
		}
		
		BasicParameter temp = (BasicParameter) anObject;
		
		return temp.parameter.equals(this.parameter);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		int result = 17;
		
		result = result * 37 + parameter.hashCode();
		
		return result;
	}

}

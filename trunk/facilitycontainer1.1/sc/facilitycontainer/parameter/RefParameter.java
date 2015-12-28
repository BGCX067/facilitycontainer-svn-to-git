/*
 * facilitycontainer1.1
 * 该软件包 提供参数对象的基本实现. 包含了对 基本类型,集合类型,引用类型的抽象形式.
 */
package sc.facilitycontainer.parameter;

import sc.facilitycontainer.FacilityContainer;
import sc.facilitycontainer.Naming;
import sc.facilitycontainer.Parameter;
import sc.facilitycontainer.exception.ComponentNotFoundException;
import sc.facilitycontainer.exception.ParameterException;

/**
 * 引用参数装饰器.
 *	根据命名对象进行逻辑比较. 
 * @author suchen
 * @time 2008-4-6 下午03:05:45
 * @email xiaochen_su@126.com
 */
public final class RefParameter implements Parameter {
	/** 引用的命名*/
	private Naming ref = null;

	/**
	 * 在某一个空间中的命名.
	 * 
	 * @param ref Naming 命名.
	 */
	public RefParameter(Naming ref) {
		this.ref = ref;
	}
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.Parameter#getByName()
	 */
	public String getByName() {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();
	}
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.Parameter#getParamEntity(sc.facilitycontainer.FacilityContainer)
	 */
	public Object getParamEntity(FacilityContainer facilityContainer) throws ParameterException {
		// TODO Auto-generated method stub		
		Object component = facilityContainer.getComponentByKey(ref);
		
		if(component == null) {
			throw new ComponentNotFoundException( ref.toString() + " not found " );
		}
	
		return component;
	}

	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.Parameter#getEntityType(sc.facilitycontainer.FacilityContainer)
	 */
	public Class<?> getEntityType(FacilityContainer facilityContainer) {
		// TODO Auto-generated method stub
		return facilityContainer.getComponentScarfskinByKey(ref).getComponentType();
	}

	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.Parameter#getExpectType(sc.facilitycontainer.FacilityContainer)
	 */
	public Class<?> getExpectType(FacilityContainer facilityContainer) {
		// TODO Auto-generated method stub
		return facilityContainer.getComponentScarfskinByKey(ref).getComponentType();
	}
	
	//	//////////////////
	//	逻辑方法
	//	//////////////////

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getClass().getName() + " ###ref### " + ref; 
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object anObject) {
		if(this == anObject) {
			return true;
		}
		
		if( !(anObject instanceof RefParameter)) {
			return false;
		}
		
		RefParameter temp = (RefParameter) anObject;
		
		return temp.ref.equals(ref);
	} 
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		int result = 17;
		
		result = result * ref.hashCode();
		
		return result;
	}
}

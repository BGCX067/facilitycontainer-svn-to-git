/*
 * facilitycontainer1.1
 * 该软件包 提供参数对象的基本实现. 包含了对 基本类型,集合类型,引用类型的抽象形式.
 */
package sc.facilitycontainer.parameter;

import sc.facilitycontainer.FacilityContainer;
import sc.facilitycontainer.Parameter;

/**
 * 抽象的参数 提供骨架实现.
 * 		1.每个参数实现(非装饰器)继承该类型获得基本的实现.
 * 		2.装饰器实现没有必要继承这个类型来获得构造函数上所带来不必要的麻烦;仅仅委托给上一层的实现来工作就可以.
 * 		
 * @author suchen
 * @time 2008-4-4 下午05:16:26
 * @email xiaochen_su@126.com
 */
public abstract class AbstractParameter implements Parameter {
	/** 实际的参数类型 */
	private Class<?> entityType = null;
	/** 预期的参数类型 */
	private Class<?> expectType = null;
	
	//	////////////////////////
	//	构造函数
	//	////////////////////////
			
	/**
	 * 基本的构造函数.
	 * 		
	 * @param entityType Class<?> 代表某一组件的实体类型.
	 * @param expectType Class<?> 预期类型,用来和目标进行类型的匹配.
	 */
	public AbstractParameter(Class<?> entityType, Class<?> expectType) {
		this.entityType = entityType;
		this.expectType = expectType;
	} 
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.Parameter#getExpectType(sc.facilitycontainer.FacilityContainer)
	 */
	public Class<?> getExpectType(FacilityContainer facilityContainer) {
		// TODO Auto-generated method stub
		return expectType;
	}
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.Parameter#getEntityType(sc.facilitycontainer.FacilityContainer)
	 */
	public Class<?> getEntityType(FacilityContainer facilityContainer) {
		// TODO Auto-generated method stub
		return entityType;
	}
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.Parameter#getByName()
	 */
	public String getByName() {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();
	}

}

/*
 * facilitycontainer1.1
 * 	包中提供了各种抽象,如组件外皮,容器,明明以及参数等等.
 */
package sc.facilitycontainer;

import sc.facilitycontainer.exception.ComponentScarfskinException;

/**
 * 组件外皮 包裹着构造一个组件所需依赖的外皮.
 * 
 * @author suchen
 * @time 2008-4-5 下午03:24:00
 * @email xiaochen_su@126.com
 */
public interface ComponentScarfskin {
	
	/**
	 * 返回组件类型.
	 * 
	 * @return Class 组件类型.
	 */
	public Class<?> getComponentType();
	
	/**
	 * 在需要时从参数中寻找有存在的依赖创建并返回组件.
	 * 
	 * @param facilityContainer FacilityContainer 简易容器.
	 * 
	 * @return Object 组件.
	 * 
	 * @throws ComponentScarfskinException
	 */
	public Object getComponent(FacilityContainer facilityContainer) throws ComponentScarfskinException;
	
	/**
	 * 返回引用次数.
	 * 
	 * @return long 组件的引用次数.
	 */
	public long getRefCount();
	
	/**
	 * 返回创建时间.
	 * 
	 * @return long 组件的创建时间.
	 */
	public long getCreateTime();
	
	/**
	 * 返回激活时间.
	 * 
	 * @return long 组件的激活时间.
	 */
	public long getActivationTime();
	
	/**
	 * 返回命名对象.
	 * 
	 * @return Naming 在容器中确定组件外皮的唯一标识.
	 */
	public Naming getNaming();
	
	/**
	 * 该组件是否是sc.facilitycontainer.support.Initializing的实现.
	 * 
	 * @return boolean 如果是Initializing的实现返回true,反之返回false;
	 */
	public boolean isInitializing();
	
	/**
	 * 是否单体保存对象.
	 * 		每次返回的对象是否不重新创建.
	 * 
	 * @return boolean 如果为真容器每次仅会创建一次组件的实体,反之为每次都将新创建.
	 */
	public boolean isSingle();
}

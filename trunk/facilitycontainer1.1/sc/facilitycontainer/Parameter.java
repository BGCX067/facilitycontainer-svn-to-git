/*
 * facilitycontainer1.1
 * 	包中提供了各种抽象,如组件外皮,容器,明明以及参数等等.
 */
package sc.facilitycontainer;

import sc.facilitycontainer.exception.ParameterException;


/**
 * 参数类型 依赖的抽象载体.
 * 		
 * 		1.每个参数都可以返回实体.
 * 		2.每个参数都有名字,但有时候没有意义.
 * 		3.每个参数都可以返回预期类型expectType和参数所包含的组件的实体类型entityType.
 * 		4.该参数具有逻辑相等性.
 * 		
 * @author sc
 * @email xiaochen_su@126.com
 * @time 2008-3-31 下午03:36:53
 */
public interface Parameter {
	
	/**
	 * 返回参数实体.
	 * 
	 * @param facilityContainer FacilityContainer 该参数实体有可能依赖容器的其他实体.
	 * 
	 * @return Object 参数实体.
	 */
	public Object getParamEntity(FacilityContainer facilityContainer) throws ParameterException;
	
	/**
	 * 返回预期类型.
	 * 		用来更加精确的选择方法对象时刻使用.
	 * @return Class<?> 组件的预期类型.
	 */
	public Class<?> getExpectType(FacilityContainer facilityContainer);
	
	/**
	 * 返回实体类型.
	 * 		
	 * @return Class<?> 组件的类型.
	 */
	public Class<?> getEntityType(FacilityContainer facilityContainer);
	
	/**
	 * 返回参数的别名.
	 * 			
	 * @return String 参数的别名.
	 */
	public String getByName();
}

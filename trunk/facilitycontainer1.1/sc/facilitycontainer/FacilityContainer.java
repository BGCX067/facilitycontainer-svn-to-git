/*
 * facilitycontainer1.1
 * 	包中提供了各种抽象,如组件外皮,容器,明明以及参数等等.
 */
package sc.facilitycontainer;

/**
 * 简易的容器
 * 		1.提供容器最基本的返回方法.和一些不能够改变容器空间的方法.
 * 		
 * @author sc
 * @email xiaochen_su@126.com
 * @time 2008-3-31 下午03:29:44
 */
public interface FacilityContainer {
	
	/**
	 * 判断容器是否包含指定命名.
	 * 
	 * @param naming Naming 命名.
	 * 
	 * @return boolean 如果命名存在返回true, 反之返回false;
	 */
	public boolean containsNaming(Naming naming);
	
	/**
	 * 判断容器是否包含指定 组件.
	 * 
	 * @param component Object 组件.
	 * 
	 * @return boolean 如果组件存在返回true, 反之返回false;
	 */
	public boolean containsComponent(Object component);
	
	/**
	 * 根据唯一标识返回组件.
	 * 
	 * @param naming Naming 在容器空间中唯一标识组件的命名对象.
	 * 
	 * @return Object 组件实体.
	 */
	public Object getComponentByKey(Naming naming);
	
	/**
	 * 根据字符串返回组件.
	 * 
	 * @param naming String 字符串.
	 * 
	 * @return Object 组件实体.
	 */
	public Object getComponentByKey(String naming);
	/**
	 * 根据引用次数返回组件.
	 * 
	 * @param refCount long 引用次数.
	 * @param state int 范围状态.返回<,>,>=,==,<=引用次数的组件. 状态的具体常量值参见sc.facilitycontainer.ConstantContext.
	 * 
	 * @return Object[] 根据引用次数在指定范围状态下的组件实体数组.
	 */
	public Object[] getComponentByRefCount(long refCount, int state);
	
	/**
	 * 根据创建时间返回组件.
	 * 
	 * @param createTime long 创建时间.
	 * @param state int 范围状态.返回<,>,>=,==,<=建时间的组件. 状态的具体常量值参见sc.facilitycontainer.ConstantContext.
	 * 
	 * @return Object[] 根据创建时间在指定范围状态下的组件实体数组.
	 */
	public Object[] getComponentByCreateTime(long createTime, int state);

	/**
	 * 根据激活时间返回组件.
	 * 
	 * @param activationTime long 激活时间.
	 * @param state int 范围状态.返回<,>,>=,==,<=激活时间的组件. 状态的具体常量值参见sc.facilitycontainer.ConstantContext.
	 * 
	 * @return Object[] 根据激活时间在指定范围状态下的组件实体数组.
	 */
	public Object[] getComponentByActivationTime(long activationTime, int state);
	
	/**
	 * 根据唯一标识返回组件外皮.
	 * 
	 * @param naming Naming 在容器空间中唯一标识组件的命名对象.
	 * 
	 * @return ComponentScarfskin 组件外皮.
	 */
	public ComponentScarfskin getComponentScarfskinByKey(Naming naming);
	
	/**
	 * 根据字符串返回组件外皮.
	 * 
	 * @param naming String 字符串.
	 * 
	 * @return ComponentScarfskin 组件外皮.
	 */
	public ComponentScarfskin getComponentScarfskinByKey(String naming);
	
	/**
	 * 根据引用次数返回组件外皮.
	 * 
	 * @param refCount long 引用次数.
	 * @param state int 范围状态.返回<,>,>=,==,<=引用次数的组件. 状态的具体常量值参见sc.facilitycontainer.ConstantContext.
	 * 
	 * @return ComponentScarfskin[] 根据引用次数在指定范围状态下的组件外皮数组.
	 */
	public ComponentScarfskin[] getComponentScarfskinByRefCount(long refCount, int state);
	
	/**
	 * 根据创建时间返回组件外皮.
	 * 
	 * @param createTime long 创建时间.
	 * @param state int 范围状态.返回<,>,>=,==,<=建时间的组件. 状态的具体常量值参见sc.facilitycontainer.ConstantContext.
	 * 
	 * @return ComponentScarfskin[] 根据创建时间在指定范围状态下的组件外皮数组.
	 */
	public ComponentScarfskin[] getComponentScarfskinByCreateTime(long createTime, int state);

	/**
	 * 根据激活时间返回组件外皮.
	 * 
	 * @param activationTime long 激活时间.
	 * @param state int 范围状态.返回<,>,>=,==,<=激活时间的组件. 状态的具体常量值参见sc.facilitycontainer.ConstantContext.
	 * 
	 * @return ComponentScarfskin[] 根据激活时间在指定范围状态下的组件外皮数组.
	 */
	public ComponentScarfskin[] getComponentScarfskinByActivationTime(long activationTime, int state);
	
	
	
}

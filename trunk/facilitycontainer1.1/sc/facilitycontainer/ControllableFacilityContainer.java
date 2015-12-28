/*
 * facilitycontainer1.1
 * 	包中提供了各种抽象,如组件外皮,容器,明明以及参数等等.
 */
package sc.facilitycontainer;

/**
 * 可以控制的容器类型
 * 		1.提供一些可以改变容器空间的方法.
 * 
 * @author sc
 * @email xiaochen_su@126.com
 * @time 2008-3-31 下午03:35:59
 */
public interface ControllableFacilityContainer extends FacilityContainer {
	
	/**
	 * 注册组件外皮对象.
	 * 
	 * @param componentScarfskin ComponentScarfskin
	 * 
	 * @return boolean 如果注册成功返回true,反之返回false.
	 */
	public boolean registerComponent(ComponentScarfskin componentScarfskin);

	/**
	 * 解除休眠的开始方法.
	 * 		该状态下可以对容器进行任何操作.
	 */
	public void start();
	
	/**
	 * 休眠容器的方法.
	 * 		该状态下不能够对容器进行任何操作.
	 */
	public void sleep();
	
	/**
	 * 停止容器的方法.
	 * 		销毁容器的方法.
	 */
	public void stop();
	
	/**
	 * 是否为开始状态.
	 * 
	 * @return boolean 如果为开始状态返回true,反之返回false;
	 */
	public boolean isStart();
	
	/**
	 * 是否为停止状态.
	 * 
	 * @return boolean 如果为停止状态返回true,反之返回false;
	 */
	public boolean isStop();
	
	/**
	 * 是否为休眠状态.
	 * 
	 * @return boolean 如果为休眠状态返回true,反之返回false;
	 */
	public boolean isSleep();

}

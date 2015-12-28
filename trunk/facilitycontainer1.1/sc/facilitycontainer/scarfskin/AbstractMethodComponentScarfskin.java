package sc.facilitycontainer.scarfskin;

import java.lang.reflect.Method;

import sc.facilitycontainer.ComponentScarfskin;
import sc.facilitycontainer.FacilityContainer;
import sc.facilitycontainer.Naming;
import sc.facilitycontainer.exception.ComponentScarfskinException;
import sc.facilitycontainer.exception.FacilityContainerException;
import sc.facilitycontainer.support.FactoryComponent;
import sc.facilitycontainer.util.ObjectUtils;

/**
   *   非构造函数注射依赖的组件外皮.
   *   
 * @author suchen
 * @time 2008-9-1 下午09:08:44
 * @email xiaochen_su@126.com
 */
public abstract class AbstractMethodComponentScarfskin implements ComponentScarfskin {
	/**  委托组件外皮  */
	private ComponentScarfskin componentScarfskin = null;
	/**  是否已注射的缓存  */
//	private boolean injectionCache = false;
	
	//	update 2009-04-13 17:52:03	这里FactoryComponent组件的类型问题 
	Class<?> componentType = null;
	
	
	/**
	   *   创建一个通过非构造函数的方式注射依赖的组件外皮.
	   *   
	 * @param componentScarfskin ComponentScarfskin ComponentScarfskin 委托外皮对象.
	 */
	public AbstractMethodComponentScarfskin(ComponentScarfskin componentScarfskin) {
		this.componentScarfskin = componentScarfskin;
	}

	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.ComponentScarfskin#getActivationTime()
	 */
	public long getActivationTime() {
		// TODO Auto-generated method stub
		return componentScarfskin.getActivationTime();
	}

	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.ComponentScarfskin#getComponent(sc.facilitycontainer.FacilityContainer)
	 */
	public Object getComponent(FacilityContainer facilityContainer)
			throws ComponentScarfskinException {
		// TODO Auto-generated method stub
		Object component = componentScarfskin.getComponent(facilityContainer);
		
		if(FactoryComponent.class.isAssignableFrom(getComponentType())) {
			componentType = component.getClass();
		} else {
			componentType = getComponentType();
		}
		
		try {
//			if (!injectionCache) {
//				synchronized (this) {
//					if (!injectionCache) {
						
						manageParameter(facilityContainer);
						ObjectUtils.dependencyInjection(component, dependentyMethods(), dependentyEntitys());
//						injectionCache = true;
//					}
//				}
//			} else if (!isSingle()) {
//				ObjectUtils.dependencyInjection(component, dependentyMethods(),dependentyEntitys());
//			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new FacilityContainerException(e);
		}
		
		return component;
	}
	
	/**
	 	*    处理参数方法.
	 	*    
	 	* @param facilityContainer FacilityContainer 从该容器中寻找参数.
	 	* @throws Exception 处理参数过程中所发生的异常.
	 */
	protected abstract void manageParameter(FacilityContainer facilityContainer) throws Exception;
	
	/**
	   *  注射依赖时所依赖的方法.
	   *  
	 * @return Method[] 依赖的方法.
	 */
	protected abstract Method[] dependentyMethods();
	
	/**
	   *  被注射的依赖, 依赖数组的顺序要于方法一一对应.
	   *  
	 * @return Object[][] 依赖.
	 */
	protected abstract Object[][] dependentyEntitys();
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.ComponentScarfskin#getComponentType()
	 */
	public Class<?> getComponentType() {
		// TODO Auto-generated method stub
		return componentScarfskin.getComponentType();
	}

	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.ComponentScarfskin#getCreateTime()
	 */
	public long getCreateTime() {
		// TODO Auto-generated method stub
		return componentScarfskin.getCreateTime();
	}

	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.ComponentScarfskin#getNaming()
	 */
	public Naming getNaming() {
		// TODO Auto-generated method stub
		return componentScarfskin.getNaming();
	}

	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.ComponentScarfskin#getRefCount()
	 */
	public long getRefCount() {
		// TODO Auto-generated method stub
		return componentScarfskin.getRefCount();
	}

	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.ComponentScarfskin#isInitializing()
	 */
	public boolean isInitializing() {
		// TODO Auto-generated method stub
		return componentScarfskin.isInitializing();
	}

	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.ComponentScarfskin#isSingle()
	 */
	public boolean isSingle() {
		// TODO Auto-generated method stub
		return componentScarfskin.isSingle();
	}
	
	// //////////////////////////////////////////////////////////////////////////////////////
	// 逻辑方法
	// 根据方法对象,以及依赖和委托的外皮对象 定位 一个 AbstractMethodComponentScarfskin
	// ////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getClass().getSimpleName() + " " + componentScarfskin.toString(); 
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return componentScarfskin.hashCode();
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object anObject) {
		return componentScarfskin.equals(anObject);
	}
}

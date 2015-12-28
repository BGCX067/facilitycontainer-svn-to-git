package sc.facilitycontainer.scarfskin;

import sc.facilitycontainer.ComponentScarfskin;
import sc.facilitycontainer.FacilityContainer;
import sc.facilitycontainer.Naming;
import sc.facilitycontainer.exception.ComponentScarfskinException;
import sc.facilitycontainer.support.Initializing;

/**
 * 组件外皮适配器.
 * 	该外皮永远是单体的.
 * 
 * @author suchen
 * @time 2008-6-10 下午02:18:44
 * @email xiaochen_su@126.com
 */
public class ComponentScarfskinAdapter implements ComponentScarfskin {
	/** 组件 */
	private Object component = null;
	/** 激活时间 */
	private long activationTime = 0L;
	/** 创建时间 */
	private long createTime = 0L;
	/** 组件类型 */
	private Class<?> componentType = null;
	/** 命名 */
	private Naming naming = null;
	/** 引用次数 */
	private int refCount = 0;
	/** 对象锁 */
	private Object lock = new Object();
	/** 是否为Initializing的实现的验证缓存 */
	private boolean isInitializingCache = false;
	/** 是否为Initializing的实现*/
	private boolean isInitializing = false;
	
	/**
	 * 创建一个外皮适配器.
	 * 
	 * @param naming Naming 命名.
	 * @param component Object 组件.
	 */
	public ComponentScarfskinAdapter(Naming naming, Object component) {
		this.component = component;
		this.naming = naming;
		createTime = activationTime = System.currentTimeMillis();
		componentType = component.getClass();
	}
		
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.ComponentScarfskin#getActivationTime()
	 */
	public long getActivationTime() {
		// TODO Auto-generated method stub
		return activationTime;
	}
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.ComponentScarfskin#getComponent(sc.facilitycontainer.FacilityContainer)
	 */
	public Object getComponent(FacilityContainer facilityContainer) throws ComponentScarfskinException {
		// TODO Auto-generated method stub
		
		if(!facilityContainer.containsNaming(naming)) {
			return null;
		}
		synchronized(lock) {
			refCount++;
		}
		return component;
	}
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.ComponentScarfskin#getComponentType()
	 */
	public Class<?> getComponentType() {
		// TODO Auto-generated method stub
		return componentType;
	}
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.ComponentScarfskin#getCreateTime()
	 */
	public long getCreateTime() {
		// TODO Auto-generated method stub
		return createTime;
	}
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.ComponentScarfskin#getNaming()
	 */
	public Naming getNaming() {
		// TODO Auto-generated method stub
		return naming;
	}
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.ComponentScarfskin#getRefCount()
	 */
	public long getRefCount() {
		// TODO Auto-generated method stub
		return refCount;
	}
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.ComponentScarfskin#isInitializing()
	 */
	public boolean isInitializing() {
		// TODO Auto-generated method stub
		if(!isInitializingCache) {
			isInitializing = Initializing.class.isAssignableFrom(componentType);
		}
		
		return isInitializing;
	}
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.ComponentScarfskin#isSingle()
	 */
	public boolean isSingle() {
		// TODO Auto-generated method stub
		return true;
	}
	
	//	/////////////////
	//	逻辑方法
	//	/////////////////
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getClass().getSimpleName() + " " + naming.toString();
	}
	
	public int hashCode() {
		int result = 17;
		
		result = result * 37 + naming.hashCode();
		
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object anObject) {
		if ( this == anObject ) {
			return true;
		}
		
		if ( !( anObject instanceof ComponentScarfskinAdapter ) ) {
			return false;
		}

		ComponentScarfskinAdapter temp = (ComponentScarfskinAdapter) anObject;
		
		return temp.naming.equals(naming);
	}
}

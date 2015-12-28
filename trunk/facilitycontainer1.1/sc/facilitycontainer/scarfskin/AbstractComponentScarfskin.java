/*
 * facilitycontainer1.1
 * 该软件包提供外皮对象的基本实现.
 */
package sc.facilitycontainer.scarfskin;

import java.lang.reflect.InvocationTargetException;

import sc.facilitycontainer.ComponentScarfskin;
import sc.facilitycontainer.FacilityContainer;
import sc.facilitycontainer.Naming;
import sc.facilitycontainer.exception.ComponentScarfskinException;
import sc.facilitycontainer.exception.ParameterException;
import sc.facilitycontainer.support.FactoryComponent;
import sc.facilitycontainer.support.Initializing;

/**
 * 提供外皮对象的骨架实现.
 * 		该类型提供了ComponentScarfskin的便捷实现.
 * 
 * @author suchen
 * @time 2008-4-5 下午03:37:08
 * @email xiaochen_su@126.com
 */
public abstract class AbstractComponentScarfskin implements ComponentScarfskin {
	/** 组件类型 */
	private Class<?> componentType = null;
	/** 组件命名 */
	private Naming naming = null;
	/** 组件被引用的次数 */
	private long refCount = 0;
	/** 组件的创建时间 */
	private long createTime = System.currentTimeMillis();
	/** 组件的激活时间 */
	private long activationTime = -1;
	/** 是否进行过验证组件为Initializing实现 */
	private boolean initializingCache = false;
	/** 组件是否为Initializing实现 */
	private boolean isInitializing = false;
	/** 是否一单体保存 */
	private boolean isSingle = false;
	
	/** 参数是否经过处理 */
//	private boolean isManageParameter = false;
	/** 组件缓存 */
	private Object componentCache = null;
	/** 锁 */
	private Object lock = new Object();
	
	//	//////////////////////////////
	//	构造函数 附属品
	//	//////////////////////////////
	
	/**
	 * 构造基本的外皮对象.
	 * 
	 * @param componentType Class 组件类型.
	 * @param naming Naming 在容器中的命名.
	 * @param isSingle boolean 是否以单体保存.
	 */
	public AbstractComponentScarfskin(Class<?> componentType, Naming naming, boolean isSingle) {
		this.componentType = componentType;
		this.naming = naming;
		this.isSingle = isSingle;
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
	 * @see sc.facilitycontainer.ComponentScarfskin#getComponent(sc.facilitycontainer.FacilityContainer)
	 */
	public Object getComponent(FacilityContainer facilityContainer) throws ComponentScarfskinException {
		// TODO Auto-generated method stub
		
		try {
//			if(!isManageParameter) {
				manageParameter(facilityContainer);
					
//				isManageParameter = true;
//			}
			
			if(componentCache == null) {
				
				componentCache = createComponent();
		
				synchronized(lock) {
					activationTime = System.currentTimeMillis();
				}
				
				if(isInitializing()) {
					Initializing initializing = (Initializing) componentCache;
					initializing.initializing();
				}
			} else if(!isSingle) {
				
				componentCache = createComponent();
				
				if(isInitializing()) {
					Initializing initializing = (Initializing) componentCache;
					initializing.initializing();
				}
			}
			
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			//	update 2009-03-09 02:04:55
			ParameterException pe =	new ParameterException( "组件: " + naming.toString() , e);
			
			throw pe;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			throw new ComponentScarfskinException(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new ComponentScarfskinException(e);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			throw new ComponentScarfskinException(e);
		} catch (Exception e) {
			throw new ComponentScarfskinException(e);
		}
		
		synchronized(lock) {
			refCount++;
		}
		
		if(FactoryComponent.class.isAssignableFrom(getComponentType())) {
				FactoryComponent component = (FactoryComponent) componentCache;
				return component.getObject();
		}
		
		return componentCache;
	}
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.ComponentScarfskin#isInitializing()
	 */
	public boolean isInitializing() {
		// TODO Auto-generated method stub
		if(initializingCache) {
			return isInitializing;
		}
		
		if(Initializing.class.isAssignableFrom(componentType)) {
			isInitializing = true;
			initializingCache = true;
		}
		
		return isInitializing;
	}
		
	/**
	 * 创建组件的方法.
	 * 		getComponent(FacilityContainer facilityContainer) 将会调用这个方法来获得实体.
	 * 		这个方法会在manageParameter(FacilityContainer facilityContainer)之后调用, 发生异常的话createComponent()将不会被调用.
	 * 
	 * @return Object 组件实体.
	 * 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	abstract Object createComponent() throws InstantiationException, IllegalAccessException, InvocationTargetException;
	
	/**
	 * 
	 * 处理参数方法.
	 * 		getComponent(FacilityContainer facilityContainer) 将会调用这个方法来获得参数.
	 * 		这个方法会在createComponent()之前调用,发生异常的话createComponent()将不会被调用.
	 * 
	 * @param facilityContainer FacilityContainer 从该容器中提取参数.
	 * 
	 * @throws NoSuchMethodException
	 */
	abstract void manageParameter(FacilityContainer facilityContainer) throws Exception;
	
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.ComponentScarfskin#isSingle()
	 */
	public boolean isSingle() {
		return isSingle;
	}
	
	//	////////////////////////////
	//	逻辑方法
	//	////////////////////////////
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getClass().getName() + " " + naming.toString();
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
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
		if(this == anObject) {
			return true;
		}
		
		if(!(getClass().isInstance(anObject))) {
			return false;
		}
		
		AbstractComponentScarfskin temp = (AbstractComponentScarfskin) anObject;
		
		return temp.naming.equals(this.naming);
	}
	
}


/*
 * facilitycontainer1.1
 * 该软件包中提供了容器的基本实现.
 */
package sc.facilitycontainer.container;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sc.facilitycontainer.ComponentScarfskin;
import sc.facilitycontainer.ControllableFacilityContainer;
import sc.facilitycontainer.Naming;
import sc.facilitycontainer.ConstantContext;
import sc.facilitycontainer.exception.ComponentScarfskinException;
import sc.facilitycontainer.naming.SimpleNaming;

/**
 * 默认的容器实现. 提供了一个简单的容器实现. 该容器实现默认为开始状状态.
 * 
 * @author suchen
 * @time 2008-4-6 下午02:06:13
 * @email xiaochen_su@126.com
 */
public class DefaultFacilityContainer implements ControllableFacilityContainer {

	/** 外皮池 */
	private List<ComponentScarfskin> pool = new ArrayList<ComponentScarfskin>();
	/** 外皮缓存1号 存储所有的外皮对象 */
	private Map<Naming, ComponentScarfskin> cache = new HashMap<Naming, ComponentScarfskin>();
	/** 外皮缓存2号 存储所有引用着SimpleNaming外皮的对象 */
	private Map<String, ComponentScarfskin> cache2 = new HashMap<String, ComponentScarfskin>();

	/** 容器的线程锁 */
	private Object lock = new Object();
	/** 是否停止该容器 */
	private boolean isStop = false;
	/** 是否休眠该容器 */
	private boolean isSleep = false;
	/** 是否开始该容器 */
	private boolean isStart = true;

	DefaultFacilityContainer() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sc.facilitycontainer.FacilityContainer#containsComponent(java.lang.Object
	 * )
	 */
	public boolean containsComponent(Object component) {
		// TODO Auto-generated method stub
		synchronized (lock) {
			return cache.containsValue(component);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sc.facilitycontainer.FacilityContainer#containsNaming(sc.facilitycontainer
	 * .Naming)
	 */
	public boolean containsNaming(Naming naming) {
		// TODO Auto-generated method stub
		synchronized (lock) {
			return pool.contains(naming);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sc.facilitycontainer.ControllableFacilityContainer#registerComponent(
	 * sc.facilitycontainer.ComponentScarfskin)
	 */
	public boolean registerComponent(ComponentScarfskin componentScarfskin) {
		// TODO Auto-generated method stub
		synchronized (lock) {
			if (pool.contains(componentScarfskin)) {
				return false;
			}
			// System.out.println("cache == null " + (cache == null));
			// System.out.println("componentScarfskin == null " +
			// (componentScarfskin == null));
			// System.out.println("componentScarfskin.getNaming() == null " +
			// (componentScarfskin.getNaming() == null));

			cache.put(componentScarfskin.getNaming(), componentScarfskin);
			pool.add(componentScarfskin);

			if (componentScarfskin.getNaming() instanceof SimpleNaming) {
				SimpleNaming simpleNaming = (SimpleNaming) componentScarfskin
						.getNaming();

				String name = simpleNaming.toString();

				name = name.substring(name.indexOf(" ") + 1);

				cache2.put(name, componentScarfskin);
			}
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sc.facilitycontainer.ControllableFacilityContainer#sleep()
	 */
	public void sleep() {
		// TODO Auto-generated method stub
		isSleep = true;
		isStart = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sc.facilitycontainer.ControllableFacilityContainer#start()
	 */
	public void start() {
		// TODO Auto-generated method stub
		isStart = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sc.facilitycontainer.ControllableFacilityContainer#stop()
	 */
	public void stop() {
		// TODO Auto-generated method stub
		isStop = true;
		isStart = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sc.facilitycontainer.FacilityContainer#getComponentByKey(sc.facilitycontainer
	 * .Naming)
	 */
	public Object getComponentByKey(Naming naming) {
		// TODO Auto-generated method stub
		synchronized (lock) {
			ComponentScarfskin componentScarfskin = cache.get(naming);

			if (componentScarfskin != null) {
				Object obj = null;
				try {
					obj = componentScarfskin.getComponent(this);
				} catch (ComponentScarfskinException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return obj;
			}
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sc.facilitycontainer.FacilityContainer#getComponentByActivationTime(long,
	 * int)
	 */
	public Object[] getComponentByActivationTime(long activationTime, int state) {
		// TODO Auto-generated method stub
		ComponentScarfskin[] componentScarfskins = getComponentScarfskinByCreateTime(
				activationTime, state);

		return getEntity(componentScarfskins);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sc.facilitycontainer.FacilityContainer#getComponentByCreateTime(long,
	 * int)
	 */
	public Object[] getComponentByCreateTime(long createTime, int state) {
		// TODO Auto-generated method stub
		ComponentScarfskin[] componentScarfskins = getComponentScarfskinByCreateTime(
				createTime, state);

		return getEntity(componentScarfskins);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sc.facilitycontainer.FacilityContainer#getComponentByRefCount(long,
	 * int)
	 */
	public Object[] getComponentByRefCount(long refCount, int state) {
		// TODO Auto-generated method stub
		ComponentScarfskin[] componentScarfskins = getComponentScarfskinByRefCount(
				refCount, state);

		return getEntity(componentScarfskins);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sc.facilitycontainer.FacilityContainer#getComponentScarfskinByActivationTime
	 * (long, int)
	 */
	public ComponentScarfskin[] getComponentScarfskinByActivationTime(
			long activationTime, int state) {
		// TODO Auto-generated method stub
		List<ComponentScarfskin> temp = new ArrayList<ComponentScarfskin>();

		synchronized (lock) {
			for (int i = 0; i < pool.size(); i++) {
				ComponentScarfskin componentScarfskin = pool.get(i);

				if (compare(componentScarfskin.getActivationTime(),
						activationTime, state)) {
					temp.add(componentScarfskin);
				}
			}
		}

		return temp.toArray(new ComponentScarfskin[temp.size()]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sc.facilitycontainer.FacilityContainer#getComponentScarfskinByCreateTime
	 * (long, int)
	 */
	public ComponentScarfskin[] getComponentScarfskinByCreateTime(
			long createTime, int state) {
		// TODO Auto-generated method stub
		List<ComponentScarfskin> temp = new ArrayList<ComponentScarfskin>();

		synchronized (lock) {
			for (int i = 0; i < pool.size(); i++) {
				ComponentScarfskin componentScarfskin = pool.get(i);

				if (compare(componentScarfskin.getCreateTime(), createTime,
						state)) {
					temp.add(componentScarfskin);
				}
			}
		}

		return temp.toArray(new ComponentScarfskin[temp.size()]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sc.facilitycontainer.FacilityContainer#getComponentScarfskinByKey(sc.
	 * facilitycontainer.Naming)
	 */
	public ComponentScarfskin getComponentScarfskinByKey(Naming naming) {
		// TODO Auto-generated method stub
		synchronized (lock) {
			return cache.get(naming);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sc.facilitycontainer.FacilityContainer#getComponentScarfskinByRefCount
	 * (long, int)
	 */
	public ComponentScarfskin[] getComponentScarfskinByRefCount(long refCount,
			int state) {
		// TODO Auto-generated method stub
		List<ComponentScarfskin> temp = new ArrayList<ComponentScarfskin>();

		synchronized (lock) {
			for (int i = 0; i < pool.size(); i++) {
				ComponentScarfskin componentScarfskin = pool.get(i);

				if (compare(componentScarfskin.getRefCount(), refCount, state)) {
					temp.add(componentScarfskin);
				}
			}
		}

		return temp.toArray(new ComponentScarfskin[temp.size()]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sc.facilitycontainer.ControllableFacilityContainer#isSleep()
	 */
	public boolean isSleep() {
		// TODO Auto-generated method stub
		return isSleep;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sc.facilitycontainer.ControllableFacilityContainer#isStart()
	 */
	public boolean isStart() {
		// TODO Auto-generated method stub
		return isStart;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sc.facilitycontainer.ControllableFacilityContainer#isStop()
	 */
	public boolean isStop() {
		// TODO Auto-generated method stub
		return isStop;
	}

	/**
	 * 根据状态进行数值逻辑比较的方法. 数值逻辑参考sc.facilitycontainer.ConstantContext;
	 * 
	 * @param a
	 *            long 参数a.
	 * @param b
	 *            long 参数b.
	 * @param state
	 *            int 状态.
	 * 
	 * @return boolean 比较后的逻辑值.
	 */
	private boolean compare(long a, long b, int state) {
		switch (state) {
		case ConstantContext.DAYU:
			return a > b;
		case ConstantContext.DENGYU:
			return a == b;
		case ConstantContext.DAYUDENGYU:
			return a >= b;
		case ConstantContext.XIAOYU:
			return a < b;
		case ConstantContext.XIAOYUDENGYU:
			return a <= b;
		default:
			return false;
		}
	}

	/**
	 * 返回一组实体.
	 * 
	 * @param componentScarfskins
	 *            ComponentScarfskin[] 返回该组中的全部实体.
	 * 
	 * @return Object[] 实体组合.
	 */
	private Object[] getEntity(ComponentScarfskin[] componentScarfskins) {
		Object[] entity = new Object[componentScarfskins.length];

		for (int i = 0; i < entity.length; i++) {
			try {
				entity[i] = componentScarfskins[i].getComponent(this);
			} catch (ComponentScarfskinException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sc.facilitycontainer.FacilityContainer#getComponentScarfskinByKey(java
	 * .lang.String)
	 */
	public ComponentScarfskin getComponentScarfskinByKey(String naming) {
		// TODO Auto-generated method stub
		synchronized (lock) {
			return cache2.get(naming);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sc.facilitycontainer.FacilityContainer#getComponentByKey(java.lang.String
	 * )
	 */
	public Object getComponentByKey(String naming) {
		// TODO Auto-generated method stub

		synchronized (lock) {
			ComponentScarfskin componentScarfskin = cache2.get(naming);

			if (componentScarfskin != null) {
				Object obj = null;
				try {
					obj = componentScarfskin.getComponent(this);
				} catch (ComponentScarfskinException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return obj;
			}
		}

		return null;
	}

}

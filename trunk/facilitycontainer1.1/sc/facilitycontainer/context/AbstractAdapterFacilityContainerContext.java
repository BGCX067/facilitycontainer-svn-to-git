package sc.facilitycontainer.context;

import java.io.IOException;

import sc.facilitycontainer.ComponentScarfskin;
import sc.facilitycontainer.ControllableFacilityContainer;
import sc.facilitycontainer.Naming;
import sc.utils.io.Resource;

/**
 * FacilityContainerContext的适配器骨架实现.
 * 
 * @author suchen
 * @time 2008-5-20 下午10:48:35
 * @email xiaochen_su@126.com
 */
public abstract class AbstractAdapterFacilityContainerContext implements FacilityContainerContext {

	/** 容器委托 */
	private ControllableFacilityContainer facilityContainer = null;
	/** 上下文实现策略委托 */
	private ContextStrategy contextStrategy = new DefaultContextStrategy(); 
	
	/**
	 * 创建一个适配器骨架实现.
	 * 
	 * @param xmlResource Resource 代表facilitycontainer.xml的Resource对象.
	 * 
	 * @throws IOException IOException 操作资源时发生的资源.
	 */
	public AbstractAdapterFacilityContainerContext(Resource xmlResource) throws IOException {
		this(xmlResource, null);
	}
	
	/**
	 * 创建一个适配器骨架实现.
	 * 
	 * @param xmlResource Resource 代表facilitycontainer.xml的Resource对象
	 * @param propertiesFileResource Resource 代表创建容器的策略.properties的Resource对象
	 * 
	 * @throws IOException 操作资源时发生的资源.
	 */
	public AbstractAdapterFacilityContainerContext(Resource xmlResource, Resource propertiesFileResource) throws IOException {
		facilityContainer = contextStrategy.getControllableFacilityContainer(propertiesFileResource);
		contextStrategy.init(this, xmlResource);
	}
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.FacilityContainer#containsComponent(java.lang.Object)
	 */
	public boolean containsComponent(Object component) {
		// TODO Auto-generated method stub
		return facilityContainer.containsComponent(component);
	}

	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.FacilityContainer#containsNaming(sc.facilitycontainer.Naming)
	 */
	public boolean containsNaming(Naming naming) {
		// TODO Auto-generated method stub
		return facilityContainer.containsNaming(naming);
	}
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.ControllableFacilityContainer#isSleep()
	 */
	public boolean isSleep() {
		// TODO Auto-generated method stub
		return facilityContainer.isSleep();
	}

	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.ControllableFacilityContainer#isStart()
	 */
	public boolean isStart() {
		// TODO Auto-generated method stub
		return facilityContainer.isStart();
	}

	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.ControllableFacilityContainer#isStop()
	 */
	public boolean isStop() {
		// TODO Auto-generated method stub
		return facilityContainer.isStop();
	}

	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.ControllableFacilityContainer#registerComponent(sc.facilitycontainer.ComponentScarfskin)
	 */
	public boolean registerComponent(ComponentScarfskin componentScarfskin) {
		// TODO Auto-generated method stub
		return facilityContainer.registerComponent(componentScarfskin);
	}

	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.ControllableFacilityContainer#sleep()
	 */
	public void sleep() {
		// TODO Auto-generated method stub
		facilityContainer.sleep();
	}
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.ControllableFacilityContainer#start()
	 */
	public void start() {
		// TODO Auto-generated method stub
		facilityContainer.start();
	}
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.ControllableFacilityContainer#stop()
	 */
	public void stop() {
		// TODO Auto-generated method stub
		facilityContainer.stop();
	}

	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.FacilityContainer#getComponentByActivationTime(long, int)
	 */
	public Object[] getComponentByActivationTime(long activationTime, int state) {
		// TODO Auto-generated method stub
		return facilityContainer.getComponentByActivationTime(activationTime, state);
	}

	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.FacilityContainer#getComponentByCreateTime(long, int)
	 */
	public Object[] getComponentByCreateTime(long createTime, int state) {
		// TODO Auto-generated method stub
		return facilityContainer.getComponentByCreateTime(createTime, state);
	}

	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.FacilityContainer#getComponentByKey(sc.facilitycontainer.Naming)
	 */
	public Object getComponentByKey(Naming naming) {
		// TODO Auto-generated method stub
		return facilityContainer.getComponentByKey(naming);
	}

	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.FacilityContainer#getComponentByRefCount(long, int)
	 */
	public Object[] getComponentByRefCount(long refCount, int state) {
		// TODO Auto-generated method stub
		return facilityContainer.getComponentByRefCount(refCount, state);
	}
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.FacilityContainer#getComponentScarfskinByActivationTime(long, int)
	 */
	public ComponentScarfskin[] getComponentScarfskinByActivationTime(long activationTime, int state) {
		// TODO Auto-generated method stub
		return facilityContainer.getComponentScarfskinByActivationTime(activationTime, state);
	}
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.FacilityContainer#getComponentScarfskinByCreateTime(long, int)
	 */
	public ComponentScarfskin[] getComponentScarfskinByCreateTime(long createTime, int state) {
		// TODO Auto-generated method stub
		return facilityContainer.getComponentScarfskinByCreateTime(createTime, state);
	}
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.FacilityContainer#getComponentScarfskinByKey(sc.facilitycontainer.Naming)
	 */
	public ComponentScarfskin getComponentScarfskinByKey(Naming naming) {
		// TODO Auto-generated method stub
		return facilityContainer.getComponentScarfskinByKey(naming);
	}
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.FacilityContainer#getComponentScarfskinByRefCount(long, int)
	 */
	public ComponentScarfskin[] getComponentScarfskinByRefCount(long refCount, int state) {
		// TODO Auto-generated method stub
		return facilityContainer.getComponentScarfskinByRefCount(refCount, state);
	}
	
	public Object getComponentByKey(String naming) {
		// TODO Auto-generated method stub
		return facilityContainer.getComponentByKey(naming);
	}

	public ComponentScarfskin getComponentScarfskinByKey(String naming) {
		// TODO Auto-generated method stub
		return facilityContainer.getComponentScarfskinByKey(naming);
	}

	
	//	////////////////
	//	逻辑方法
	//	////////////////
	
	public String toString() {
		return getClass().getName();
	}
}

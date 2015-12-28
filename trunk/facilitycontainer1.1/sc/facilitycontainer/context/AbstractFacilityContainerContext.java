/*
 * facilitycontainer1.1
 * 该软件包中提供了上下文的基本实现. 上下文是容器与资源和解析xml的组织者,使用户的外观接口.
 */
package sc.facilitycontainer.context;

import java.io.IOException;

import sc.facilitycontainer.ComponentScarfskin;
import sc.facilitycontainer.ControllableFacilityContainer;
import sc.facilitycontainer.Naming;
import sc.utils.io.Resource;

/**
 * 容器上下文骨架实现.
 * 		根据xml文件炎症并解析生成对象且放入容器.
 * 		根据properties文件创造指定的容器.
 * 
 * 上下文与资源形成的并行层次结构.
 * 
 * 	<table>
 * 		<tr><td>Context</td><td>&nbsp;&nbsp;</td><td>Resource</td></tr>
 * 		<tr><td>&nbsp;&nbsp;AbstractContext</td><td></td><td>&nbsp;&nbsp;AbstractResource</td></tr>
 * 		<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;A_Context&nbsp;</td><td>-----------></td><td>&nbsp;&nbsp;&nbsp;&nbsp;A_Resource</td></tr>
 * 		<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;B_Context&nbsp;</td><td>-----------></td><td>&nbsp;&nbsp;&nbsp;&nbsp;B_Resource</td></tr>
 * 		<tr><td>&nbsp;&nbsp;&nbsp;C_Context&nbsp;</td><td>-----------></td><td>&nbsp;&nbsp;&nbsp;&nbsp;C_Resource</td></tr>
 * 	</table>
 *          
 * @author suchen
 * @time 2008-4-14 下午10:31:39
 * @email xiaochen_su@126.com
 */
public abstract class AbstractFacilityContainerContext implements FacilityContainerContext {
	/** 容器委托 */
	private ControllableFacilityContainer facilityContainer = null;
	/** 上下文实现策略委托 */
	private ContextStrategy contextStrategy = new DefaultContextStrategy();
	
	/**
	 * 根据资源名称和容器策略创建一个上下文对象.
	 * 
	 * @param xmlResource String 资源名称 *.xml文件 .
	 * 
	 * @throws IOException 操作上下文所依赖的文件时所发生的异常.
	 */
	public AbstractFacilityContainerContext(String xmlResource) throws IOException {
		this(xmlResource, null);
	}
	
	/**
	 * 根据资源名称和容器策略创建一个上下文对象.
	 * 
	 * @param xmlResource String 资源名称 *.xml文件 .
	 * @param containerStrategy String 容器策略 *.properties 文件.
	 * 
	 * @throws IOException 操作上下文所依赖的文件时所发生的异常.
	 */
	public AbstractFacilityContainerContext(String xmlResource, String propertiesResource) throws IOException {		
		facilityContainer = contextStrategy.getControllableFacilityContainer(propertiesResource == null ? null : createResource(propertiesResource));
		contextStrategy.init(this, createResource(xmlResource));
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

	/**
	 * 创建资源对象的口子.
	 *  
	 * @param resourceStr 资源名称.
	 * 
	 * @return Resource 定位后可用的资源.
	 */
	protected abstract Resource createResource(String resourceStr) throws IOException;
	
}
 
/*
 * facilitycontainer1.1
 * 该软件包中提供了上下文的基本实现. 上下文是容器与资源和解析xml的组织者,使用户的外观接口.
 */
package sc.facilitycontainer.context;

import java.io.IOException;
import java.net.MalformedURLException;

import sc.utils.io.Resource;
import sc.utils.io.impl.URLResource;

/**
 * URL容器上下文.
 * 		通过URL定位的资源创建容器.
 * 
 * @author suchen
 * @time 2008-4-14 下午10:39:31
 * @email xiaochen_su@126.com
 */
public final class URLFacilityContainerContext extends AbstractFacilityContainerContext {
	
	/**
	 * 构造一个URL容器上下文.
	 * 
	 * @param resourceStr String 资源名称 *.xml文件 .
	 * 
	 * @throws IOException操作上下文所依赖的文件时所发生的异常.
	 */
	public URLFacilityContainerContext(String resourceStr) throws IOException {
		super(resourceStr);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 构造一个URL容器上下文.
	 * 
	 * @param resourceStr String 资源名称 *.xml文件 .
	 * @param containerStrategy String 容器策略 *.properties 文件.
	 * 
	 * @throws IOException 操作上下文所依赖的文件时所发生的异常.
	 */
	public URLFacilityContainerContext(String resourceStr, String containerStrategy) throws IOException {
		super(resourceStr, containerStrategy);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.context.AbstractFacilityContainerContext#createResource(java.lang.String)
	 */
	@Override
	protected Resource createResource(String resourceStr) throws MalformedURLException {
		// TODO Auto-generated method stub
		return new URLResource(resourceStr);
	}

}

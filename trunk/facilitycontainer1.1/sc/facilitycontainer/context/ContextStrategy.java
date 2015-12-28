package sc.facilitycontainer.context;

import java.io.IOException;

import sc.facilitycontainer.ControllableFacilityContainer;
import sc.utils.io.Resource;

/**
 * 上下文实现策略
 * 
 * @author suchen
 * @time 2008-5-20 下午10:25:17
 * @email xiaochen_su@126.com
 */
interface ContextStrategy {
	
	/**
	 * 创建容器策略.
	 * 		根据策略创建容器.
	 * 
	 * @param propertiesResource Resource 表示容器构建策略的.properties文件.
	 * 
	 * @return ControllableFacilityContainer 创建好的容器.
	 * 
	 * @throws IOException 操作上下文所依赖的文件时所发生的异常.
	 */
	public ControllableFacilityContainer getControllableFacilityContainer(Resource propertiesResource) throws IOException;
	
	/**
	 * 
	 * @param facilityContainerContext
	 * 
	 * @param xmlResource
	 */
	public void init(FacilityContainerContext facilityContainerContext, Resource xmlResource) throws IOException;
}

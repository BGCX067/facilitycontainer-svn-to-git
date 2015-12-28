/*
 * facilitycontainer1.1
 * 该软件包中提供了容器的基本实现.
 */
package sc.facilitycontainer.container;

//import java.util.Iterator;
import java.util.Map;
//import java.util.Set;

import sc.facilitycontainer.ControllableFacilityContainer;

/**
 * 容器静态工厂.
 * @author suchen
 * @time 2008-4-14 下午10:53:45
 * @email xiaochen_su@126.com
 */
public final class StaticContainerFactory {
	private StaticContainerFactory() {
		
	}
	
	/**
	 * 根据配置创建容器.
	 * 
	 * @param config Properties 
	 * 
	 * @return ControllableFacilityContainer
	 */
	@SuppressWarnings("unchecked")
	public static ControllableFacilityContainer createContainer(Map config) {
		
		DefaultFacilityContainer dfc = new DefaultFacilityContainer();
		
		if(config == null) {
			return dfc;
		}
		
//		Set keySet = config.keySet();
//		
//		Iterator iterator = keySet.iterator();
//		
//		while(iterator.hasNext()) {
//			String key = (String)iterator.next();
//			config.get(key);
//		}
		
		return dfc;
	}
	
}

package sc.facilitycontainer.scarfskin;

import sc.facilitycontainer.FacilityContainer;

/**
 * 处理参数接口.
 * 
 * @author suchen
 * @time 2008-9-2 下午12:18:00
 * @email xiaochen_su@126.com
 */
interface ManagerParameter {
	public Object manageParameter(FacilityContainer facilityContainer,
			Object[] obj) throws Exception;
	
	//	update 2009-07-04 21:48:26	从sc.facilitycontainer.scarfskin.support目录移动到
	//	sc.facilitycontainer.scarfskin目录 作为外皮的实现细节不公开接口
}

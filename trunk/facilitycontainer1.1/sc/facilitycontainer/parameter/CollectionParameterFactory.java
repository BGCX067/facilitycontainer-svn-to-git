package sc.facilitycontainer.parameter;

import java.util.List;
import java.util.Map;
import java.util.Set;

import sc.facilitycontainer.Parameter;
import sc.facilitycontainer.ConstantContext;

/**
 * 集合参数工厂.
 * 
 * @author suchen
 * @time 2008-6-7 下午02:16:45
 * @email xiaochen_su@126.com
 */
public abstract class CollectionParameterFactory {
	
	/**
	 * 返回一个集合参数.
	 * 
	 * @param expectType Class<?> 预期类型.
	 * @param parameter List<Parameter> 集合参数的缓存,不同的集合对象会以各自的方式保存这些参数.
	 * @param tag int 根据该标记选择集合参数. 参见 sc.facilitycontainer.ConstantContext.
	 *   
	 * @return CollectionParameter 集合参数.
	 */
	public CollectionParameter getCollectionParameter(Class<?> expectType, List<Parameter> parameter, int tag) {
		return createCollectionParameter(expectType, parameter, tag);
	}

	public CollectionParameter getMapParameter(List<Parameter> parameter) {
		return createCollectionParameter(Map.class, parameter, ConstantContext.MAP_PARAMETER);
	}
	
	public CollectionParameter getSetParameter(List<Parameter> parameter) {
		return createCollectionParameter(Set.class, parameter, ConstantContext.SET_PARAMETER);
	}
	
	public CollectionParameter getListParameter(List<Parameter> parameter) {
		return createCollectionParameter(List.class, parameter, ConstantContext.LIST_PARAMETER);
	}
	
	public CollectionParameter getArrayParameter(Class<?> expectType, List<Parameter> parameter) {
		return createCollectionParameter(expectType, parameter, ConstantContext.ARRAY_PARAMETER);
	}
	
	/**
	 * 创建一个集合参数.
	 * 
	 * @param expectType Class<?> 预期类型.
	 * @param parameter List<Parameter> 集合参数的缓存,不同的集合对象会以各自的方式保存这些参数.
	 * @param tag int 根据该标记选择集合参数. 参见 sc.facilitycontainer.ConstantContext.
	 * 
	 * @return CollectionParameter 集合参数.
	 */
	abstract CollectionParameter createCollectionParameter(Class<?> expectType, List<Parameter> parameter, int tag);
}

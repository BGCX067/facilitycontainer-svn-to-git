package sc.facilitycontainer.parameter;

import java.util.List;

import sc.facilitycontainer.ConstantContext;
import sc.facilitycontainer.Parameter;

/**
 * 默认的集合参数工厂.
 * 
 * @author suchen
 * @time 2008-6-7 下午04:02:14
 * @email xiaochen_su@126.com
 */
public class DefaultCollectionParameterFactory extends CollectionParameterFactory {

	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.parameter.CollectionParameterFactory#createCollectionParameter(java.lang.Class, java.util.List, int)
	 */
	@Override
	CollectionParameter createCollectionParameter(Class<?> expectType, List<Parameter> parameter, int tag) {
		// TODO Auto-generated method stub
		switch(tag) {
		case ConstantContext.LIST_PARAMETER : {
			CollectionParameter temp = new ListParameter();
			temp.cache = parameter;
			return temp;
		} 
		case ConstantContext.SET_PARAMETER : {
			CollectionParameter temp = new SetParameter();
			temp.cache = parameter;
			return temp;
		}
		case ConstantContext.MAP_PARAMETER : {
			CollectionParameter temp = new MapParameter();
			temp.cache = parameter;
			return temp;
		} 
		case ConstantContext.ARRAY_PARAMETER : {
			CollectionParameter temp = new ArrayParameter(expectType);
			temp.cache = parameter;
			return temp;
		}
		default : { throw new IllegalArgumentException( " [current tag] " + tag + " 参数对象标记不符合规格参见常量上下文中的定义 "  ); }
		}
		
	}

}

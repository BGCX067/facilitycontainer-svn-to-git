/*
 * facilitycontainer1.1
 * 	包中提供了各种抽象,如组件外皮,容器,明明以及参数等等.
 */
package sc.facilitycontainer;

/**
 * 常量上下文.
 * 		1.数值逻辑状态常量.
 * 		2.常用的empty常量.
 * 
 * @author sc
 * @email xiaochen_su@126.com
 * @time 2008-3-31 下午05:27:35
 */
public final class ConstantContext {
	private ConstantContext() {
		
	}
	
	//	////////////////////////////////////////////////////////
	//	数值逻辑状态
	//
	//	a > b　大于　		dayu
	//	a < b　小于		xiaoyu
	//	a == b　等于		dengyu
	//	a >= b　大于等于	dayudengyu
	//	a <= b　小于等于	xiaoyudengyu
	//	////////////////////////////////////////////////////////
	
	/** 大于 */
	public static final int DAYU = 1;
	/** 小于 */
	public static final int XIAOYU = 2;
	/** 等于 */
	public static final int DENGYU = 3;
	/** 大于等于 */
	public static final int DAYUDENGYU = 4;
	/** 小于等于 */
	public static final int XIAOYUDENGYU = 5;
	
	//	////////////////////////////////////////////////////////
	//	创建集合参数的常量 - 常量工厂使用
	//	////////////////////////////////////////////////////////
	
	/** 集合参数 - List */
	public static final int LIST_PARAMETER = 1;
	/** 集合参数 - Set */
	public static final int SET_PARAMETER = 2;
	/** 集合参数 - Map */
	public static final int MAP_PARAMETER = 3;
	/** 集合参数 - Array */
	public static final int ARRAY_PARAMETER = 4;
	
	//	////////////////////////////////////////////////////////
	//	创建组件外皮的常量 - 外皮工厂使用
	//	////////////////////////////////////////////////////////
	
	/** 外皮 - Basic */
	public static final int BASIC_SCARFSKIN = 1;
	/** 外皮 - Constructor */
	public static final int CONSTRUCTOR_SCARFSKIN = 2;
	
	
	//	////////////////////////////////////////////////////////
	//	经常使用的Empty数值,以及一些连接时使用的字符串
	//	////////////////////////////////////////////////////////
	
	/** Empty的Object数组 */
	public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];	
	/** Empty的Parameter数组 */
	public static final Parameter[] EMPTY_PARAMETER_ARRAY = new Parameter[0];
	/** Empty的String对象 */
	public static final String EMPTY_STRING = "";
	
}

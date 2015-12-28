package demo.testing;

import java.io.IOException;

import demo.component.RefUserModel;

import sc.facilitycontainer.context.ClassPathFacilityContainerContext;
import sc.facilitycontainer.context.FacilityContainerContext;

/**
 * 测试单体工厂组件和SAX对特殊字符的处理.
 * 
 * @author suchen
 * @time 2009-5-17 上午09:00:45
 * @email xiaochen_su@126.com
 */
public class TestingSingleAndFactoryComponent {

	public static void main(String[] args) {
//		testingSingle();
//		testingZyChar();
		testingRefSingle();
	}
	
	/**
	 * 测试引用上下文时组件是否能够根据single属性 控制单体.
	 * 改变被引用的组件的single属性 观察结果
	 */
	public static void testingRefSingle() {
		try {
			FacilityContainerContext containerContext = new ClassPathFacilityContainerContext("demo/testing/file/single-testing.xml","demo/testing/file/usermodel.properties");
			
			RefUserModel refA = (RefUserModel)containerContext.getComponentByKey("reftsC");
			RefUserModel refB = (RefUserModel)containerContext.getComponentByKey("reftsC");
			
			System.out.println(refA.equals(refB));
			System.out.println(refA.getUserModel() == refB.getUserModel());
			
			refA = (RefUserModel)containerContext.getComponentByKey("reftsS");
			refB = (RefUserModel)containerContext.getComponentByKey("reftsS");
			
			System.out.println(refA.equals(refB));
			System.out.println(refA.getUserModel() == refB.getUserModel());
			
			refA = (RefUserModel)containerContext.getComponentByKey("reftsM");
			refB = (RefUserModel)containerContext.getComponentByKey("reftsM");
			
			System.out.println(refA.equals(refB));
			System.out.println(refA.getUserModel() == refB.getUserModel());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 取得2个相同id的组件看是否精确等于
	 */
	public static void testingSingle() {
		try {
			FacilityContainerContext containerContext = new ClassPathFacilityContainerContext("demo/testing/file/single-testing.xml", "demo/testing/file/usermodel.properties");
			
			Object a = containerContext.getComponentByKey("ts");	//	testing single ts 
			Object b = containerContext.getComponentByKey("ts");
			
			System.out.println(a == b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 测试转义字符
	 */
	public static void testingZyChar() {
		try {
			FacilityContainerContext containerContext = new ClassPathFacilityContainerContext("demo/testing/file/zychr.xml", "demo/testing/file/usermodel.properties");
			Object a = containerContext.getComponentByKey("http");
			Object b = containerContext.getComponentByKey("userModel");
			System.out.println(a);
			System.out.println("=======================");
			System.out.println(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//	update 2009-07-04 22:06:42	添加了引用非单体组件时 返回的都是同一对象的错误更改后的测试.
	
}

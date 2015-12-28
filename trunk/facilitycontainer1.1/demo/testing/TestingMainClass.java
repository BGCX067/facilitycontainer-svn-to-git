package demo.testing;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;


import demo.component.CTBModel;
import demo.component.CollectionModel;
import demo.component.SetterMethodCollectionModel;
import demo.component.UserModel;
import demo.component.UserModelCache;

import sc.facilitycontainer.context.ClassPathFacilityContainerContext;
import sc.facilitycontainer.context.FacilityContainerContext;

/**
 * 测试主类.
 * 
 * @author suchen
 * @time 2008-7-30 上午10:58:31
 * @email xiaochen_su@126.com
 */
public class TestingMainClass {

	public static void main(String[] args) {
		
		System.out.println("-----------------testingUserModel-----------------------------");
		testingUserModel();
		System.out.println("-----------------testingUserModelCache-----------------------------");
		testingUserModelCache();
		
		System.out.println("-----------------testingSingleCollectionMethod-----------------------------");
		testingSingleCollectionMethod();
		System.out.println("-----------------testingSingleCollectionConstructor-----------------------------");
		testingSingleCollectionConstructor();
		System.out.println("-----------------testingSingleCollectionSetter-----------------------------");
		testingSingleCollectionSetter();
		
		System.out.println("-----------------testingDoubleCollectionMethod-----------------------------");
		testingDoubleCollectionMethod();
		System.out.println("-----------------testingDoubleCollectionConstructor-----------------------------");
		testingDoubleCollectionConstructor();
		System.out.println("-----------------testingDoubleCollectionSetter-----------------------------");
		testingDoubleCollectionSetter();
		
		System.out.println("-----------------testingSingleCollectionBasicConstructor-----------------------------");
		testingSingleCollectionBasicConstructor();
		System.out.println("-----------------testingSingleCollectionBasicSetter-----------------------------");
		testingSingleCollectionBasicSetter();
		System.out.println("-----------------testingSingleCollectionBasicMethod-----------------------------");
		testingSingleCollectionBasicMethod();
		
		System.out.println("-----------------testingDoubleCollectionBasicConstructor-----------------------------");
		testingDoubleCollectionBasicConstructor();
		System.out.println("-----------------testingDoubleCollectionBasicMethod-----------------------------");
		testingDoubleCollectionBasicMethod();
		System.out.println("-----------------testingDoubleCollectionBasicSetter-----------------------------");
		testingDoubleCollectionBasicSetter();
		
	}
	

	/**
	 * 测试用户模型的方法.
	 */
	public static void testingUserModel() {
		try {
			FacilityContainerContext containerContext = 
				new ClassPathFacilityContainerContext("demo/testing/file/user-model.xml");
				//new ClassPathFacilityContainerContext("demo/testing/file/user-model.xml", "demo/testing/file/usermodel.properties");
			containerContext.getComponentByKey("um_0/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 测试用户模型缓存的方法.
	 */
	public static void testingUserModelCache() {
		try {
			FacilityContainerContext containerContext = new ClassPathFacilityContainerContext("demo/testing/file/user-model-cache.xml", "demo/testing/file/usermodel.properties");
			
			UserModelCache userModelCache = (UserModelCache)containerContext.getComponentByKey("umc");
			
			System.out.println(userModelCache.getName());
			System.out.println(userModelCache.getCreateDate());
			
			Map<String, UserModel> cache = userModelCache.getUserModelCache();
			
			for(Iterator<String> iterator = cache.keySet().iterator();iterator.hasNext();) {
				String key = iterator.next();
				
				System.out.println(key + "  " + cache.get(key));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//	////////////////////////
	//	测试未添加基本元素(short, int, long, float, double, boolean)集合参数方法.
	//	
	//	均匀出现 就是每个元素都出现且出现一次.
	//	重复出现 每个元素都出现但次数没有限制.
	//	////////////////////////
	
	//	均匀出现
	/**
	 * 均匀出现集合参数的函数测试 --- method
	 */
	public static void testingSingleCollectionMethod() {
		try {
			FacilityContainerContext containerContext = new ClassPathFacilityContainerContext("demo/testing/file/collection-testing.xml", "demo/testing/file/usermodel.properties");
			CollectionModel collectionModel1 = (CollectionModel)containerContext.getComponentByKey("ct_1");
			System.out.println(collectionModel1);
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 均匀出现集合参数的函数测试 --- constructor
	 */
	public static void testingSingleCollectionConstructor() {
		try {
			FacilityContainerContext containerContext = new ClassPathFacilityContainerContext("demo/testing/file/collection-testing.xml", "demo/testing/file/usermodel.properties");
			
			CollectionModel collectionModel2 = (CollectionModel)containerContext.getComponentByKey("ct_2");
			System.out.println(collectionModel2);
			
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 *  均匀出现集合参数的函数测试 --- setter
	 */
	public static void testingSingleCollectionSetter() {
		try {
			FacilityContainerContext containerContext = new ClassPathFacilityContainerContext("demo/testing/file/collection-testing.xml", "demo/testing/file/usermodel.properties");
			CollectionModel collectionModel = (CollectionModel)containerContext.getComponentByKey("ct_0");
			System.out.println(collectionModel);
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//	重复出现
	
	/**
	 *  重复出现集合参数的函数测试 --- method
	 */
	public static void testingDoubleCollectionMethod() {
		try {
			FacilityContainerContext containerContext = new ClassPathFacilityContainerContext("demo/testing/file/collection-testing.xml", "demo/testing/file/usermodel.properties");
			
			SetterMethodCollectionModel smcmm = (SetterMethodCollectionModel)containerContext.getComponentByKey("smcmm");
			System.out.println(smcmm);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 *  重复出现集合参数的函数测试 --- constructor
	 */
	public static void testingDoubleCollectionConstructor() {
		try {
			FacilityContainerContext containerContext = new ClassPathFacilityContainerContext("demo/testing/file/collection-testing.xml", "demo/testing/file/usermodel.properties");
			
			containerContext.getComponentByKey("ct_3");
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 重复出现集合参数的函数测试 --- setter
	 */
	public static void testingDoubleCollectionSetter() {
		try {
			FacilityContainerContext containerContext = new ClassPathFacilityContainerContext("demo/testing/file/collection-testing.xml", "demo/testing/file/usermodel.properties");
			
			SetterMethodCollectionModel smcms = (SetterMethodCollectionModel)containerContext.getComponentByKey("smcms");
			System.out.println(smcms);
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//	不包含基本类型的集合参数测试完毕.
	
	//	/////////////////////////////
	//	包含基本类型的集合参数测试
	//	/////////////////////////////
	
	//	均匀出现 带有基本类型的集合参数测试 - constructor
	public static void testingSingleCollectionBasicConstructor() {
		try {
			FacilityContainerContext containerContext = new ClassPathFacilityContainerContext("demo/testing/file/collection-testing-basic.xml", "demo/testing/file/usermodel.properties");
			
			CTBModel ctb = (CTBModel)containerContext.getComponentByKey("ctb_0");
			
			System.out.println(ctb);
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//	均匀出现 带有基本类型的集合参数测试 - setter
	public static void testingSingleCollectionBasicSetter() {
		try {
			FacilityContainerContext containerContext = new ClassPathFacilityContainerContext("demo/testing/file/collection-testing-basic.xml", "demo/testing/file/usermodel.properties");
			
			CTBModel ctb = (CTBModel)containerContext.getComponentByKey("ctb_1");
			
			System.out.println(ctb);
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//	均匀出现 带有基本类型的集合参数测试 - method
	public static void testingSingleCollectionBasicMethod() {
		try {
			FacilityContainerContext containerContext = new ClassPathFacilityContainerContext("demo/testing/file/collection-testing-basic.xml", "demo/testing/file/usermodel.properties");
			
			CTBModel ctb = (CTBModel)containerContext.getComponentByKey("ctb_2");
			
			System.out.println(ctb);
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//	重复出现 带有基本类型的集合参数测试 - constructor
	public static void testingDoubleCollectionBasicConstructor() {
		try {
			FacilityContainerContext containerContext = new ClassPathFacilityContainerContext("demo/testing/file/collection-testing-basic.xml", "demo/testing/file/usermodel.properties");
			
			containerContext.getComponentByKey("ctb_3");
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//	重复出现 带有基本类型的集合参数测试 - method
	public static void testingDoubleCollectionBasicMethod() {
		try {
			FacilityContainerContext containerContext = new ClassPathFacilityContainerContext("demo/testing/file/collection-testing-basic.xml", "demo/testing/file/usermodel.properties");
			
			containerContext.getComponentByKey("ctb_4");
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//	重复出现 带有基本类型的集合参数测试 - setter
	public static void testingDoubleCollectionBasicSetter() {
		try {
			FacilityContainerContext containerContext = new ClassPathFacilityContainerContext("demo/testing/file/collection-testing-basic.xml", "demo/testing/file/usermodel.properties");
			
			System.out.println(containerContext.getComponentByKey("ctb_5"));
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//	测试上下文引用
	
	public static void testingRef() {
		
	}
	//	update 2009-07-04 10:34:48
	
	
}

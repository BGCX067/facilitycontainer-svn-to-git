package demo.component;

import java.util.List;
import java.util.Map;
import java.util.Set;

import sc.utils.StringUtils;

/**
 * 集合类测试.
 * 
 * @author suchen
 * @time 2008-7-30 下午05:05:58
 * @email xiaochen_su@126.com
 */
public class CollectionModel {
	
	private Object[] array;
	
	private Set set;
	
	private List list;
	
	private Map map;

	private Object[] array1;
	
	private Set set1;
	
	private List list1;
	
	private Map map1;
	
	public CollectionModel() {
		
	}
	
	public CollectionModel(Object[] array, Set set, Map map, List list) {
		this.array = array;
		this.set = set;
		this.map = map;
		this.list = list;
	}
	
	public CollectionModel(String str, Object[] array, Set set, Map map, List list) {
		this.array = array;
		this.set = set;
		this.map = map;
		this.list = list;
		
		System.out.println(str);
	} 
	
	public CollectionModel(Object[] array, Object[] array1, Set set, Set set1, Map map, Map map1, List list, List list1) {
		
		System.out.println("array # " + StringUtils.toString(array));
		System.out.println("array1 # " + StringUtils.toString(array1));
		System.out.println("set # " + StringUtils.toString(set));
		System.out.println("set1 # " + StringUtils.toString(set1));
		System.out.println("map # " + StringUtils.toString(map));
		System.out.println("map1 # " + StringUtils.toString(map1));
		System.out.println("list # " + StringUtils.toString(list));
		System.out.println("list1 # " + StringUtils.toString(list1));
		
	}
	

	public Object[] getArray() {
		return array;
	}

	public void setArray(Object[] array) {
		this.array = array;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Set getSet() {
		return set;
	}

	public void setSet(Set set) {
		this.set = set;
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("set\n").append(StringUtils.toString(set));
		buffer.append("list\n").append(StringUtils.toString(list));
		buffer.append("map\n").append(StringUtils.toString(map));
		buffer.append("array\n").append(StringUtils.toString(array));
		
//		buffer.append("set1\n").append(StringUtils.toString(set1));
//		buffer.append("list1\n").append(StringUtils.toString(list1));
//		buffer.append("map1\n").append(StringUtils.toString(map1));
//		buffer.append("array1\n").append(StringUtils.toString(array1));
		return buffer.toString();
	}

	public Object[] getArray1() {
		return array1;
	}

	public void setArray1(Object[] array1) {
		
		this.array1 = array1;
	}

	public List getList1() {
		return list1;
	}

	public void setList1(List list1) {
		this.list1 = list1;
	}

	public Map getMap1() {
		return map1;
	}

	public void setMap1(Map map1) {
		this.map1 = map1;
	}

	public Set getSet1() {
		return set1;
	}

	public void setSet1(Set set1) {
		this.set1 = set1;
	}
}

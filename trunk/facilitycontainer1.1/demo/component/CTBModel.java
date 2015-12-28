package demo.component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import sc.utils.StringUtils;

/**
 * 集合参数的新添加的基本类型元素测试.
 * 
 * @author suchen
 * @time 2008-8-1 下午12:05:07
 * @email xiaochen_su@126.com
 */
public class CTBModel {
	
	private Map map = null;
	private Set set = null;
	private List list = null;
	private Object[] array = null;
	
	private Map map1 = null;
	private Set set1 = null;
	private List list1 = null;
	private Object[] array1 = null;
	
	public CTBModel() {
		
	}
	
	public CTBModel(Map map, Set set, List list, Object[] array) {
		this.array = array;
		this.map = map;
		this.set = set;
		this.list = list;
	}
	
	public CTBModel(Map map, Map map1, Set set, Set set1, List list, List list1, Object[] array, Object[] array1) {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("map\n").append(StringUtils.toString(map));
		buffer.append("list\n").append(StringUtils.toString(list));
		buffer.append("set\n").append(StringUtils.toString(set));
		buffer.append("array\n").append(StringUtils.toString(array));
		buffer.append("map1\n").append(StringUtils.toString(map1));
		buffer.append("list1\n").append(StringUtils.toString(list1));
		buffer.append("set1\n").append(StringUtils.toString(set1));
		buffer.append("array1\n").append(StringUtils.toString(array1));
		System.out.println(buffer.toString());
		
	}
	
	public void print(Map map, Map map1, Set set, Set set1, List list, List list1, Object[] array, Object[] array1) {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("map\n").append(StringUtils.toString(map));
		buffer.append("list\n").append(StringUtils.toString(list));
		buffer.append("set\n").append(StringUtils.toString(set));
		buffer.append("array\n").append(StringUtils.toString(array));
		buffer.append("map1\n").append(StringUtils.toString(map1));
		buffer.append("list1\n").append(StringUtils.toString(list1));
		buffer.append("set1\n").append(StringUtils.toString(set1));
		buffer.append("array1\n").append(StringUtils.toString(array1));
		System.out.println(buffer.toString());
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("map\n").append(StringUtils.toString(map));
		buffer.append("list\n").append(StringUtils.toString(list));
		buffer.append("set\n").append(StringUtils.toString(set));
		buffer.append("array\n").append(StringUtils.toString(array));
		buffer.append("set1\n").append(StringUtils.toString(set1));
		buffer.append("list1\n").append(StringUtils.toString(list1));
		buffer.append("map1\n").append(StringUtils.toString(map1));
		buffer.append("array1\n").append(StringUtils.toString(array1));
		return buffer.toString();
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

package demo.component;

import java.util.List;
import java.util.Set;
import java.util.Map;

import sc.utils.StringUtils;

/**
 * 
 * @author suchen
 * @time 2008-7-31 上午11:10:11
 * @email xiaochen_su@126.com
 */
public class SetterMethodCollectionModel {
	private List list = null;

	private Set set = null;

	private Map map = null;

	private List list1 = null;

	private Set set1 = null;

	private Map map1 = null;

	private Object[] array = null;

	private Object[] array1 = null;

	public SetterMethodCollectionModel() {
		
	}
	
	public Object[] getArray() {
		return array;
	}

	public void setArray(Object[] array) {
		this.array = array;
	}

	public Object[] getArray1() {
		return array1;
	}

	public void setArray1(Object[] array1) {
		this.array1 = array1;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public List getList1() {
		return list1;
	}

	public void setList1(List list1) {
		this.list1 = list1;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Map getMap1() {
		return map1;
	}

	public void setMap1(Map map1) {
		this.map1 = map1;
	}

	public Set getSet() {
		return set;
	}

	public void setSet(Set set) {
		this.set = set;
	}

	public Set getSet1() {
		return set1;
	}

	public void setSet1(Set set1) {
		this.set1 = set1;
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("list\n").append(StringUtils.toString(list));
		buffer.append("list1\n").append(StringUtils.toString(list1));
		buffer.append("array\n").append(StringUtils.toString(array));
		buffer.append("array1\n").append(StringUtils.toString(array1));
		buffer.append("set\n").append(StringUtils.toString(set));
		buffer.append("set1\n").append(StringUtils.toString(set1));
		buffer.append("map\n").append(StringUtils.toString(map));
		buffer.append("map1\n").append(StringUtils.toString(map1));
		
		return buffer.toString();
	}
}

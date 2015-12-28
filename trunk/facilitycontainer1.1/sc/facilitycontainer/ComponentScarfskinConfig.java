package sc.facilitycontainer;

/**
 * 
 * @author suchen
 * @time 2008-9-2 下午03:25:06
 * @email xiaochen_su@126.com
 */
public class ComponentScarfskinConfig {
	
	private Naming naming = null;
	private boolean single = false;
	private Class<?> componentType = null;
	private boolean parent = false;
	private Naming parentName = null;
	
	public ComponentScarfskinConfig(Naming naming, Class<?> componentType, Naming parentName, boolean single) {
		if(parentName != null) {
			this.parent = true;
		}
		
		this.parentName = parentName;
		this.componentType = componentType;
		this.single = single;
		this.naming = naming;
	}
	
	public Naming getNaming() {
		return naming;
	}
	public boolean isSingle() {
		return single;
	}
	public Class<?> getComponentType() {
		return componentType;
	}
	public boolean isParent() {
		return parent;
	}
	public Naming getParentName() {
		return parentName;
	}
	
	
}

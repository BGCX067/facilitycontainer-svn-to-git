package demo.component;

/**
 * 为了测试引用是否单体或重复创建的类型.
 * 
 * @author suchen
 * @time 2009-7-4 上午10:44:04
 * @email xiaochen_su@126.com
 */
public class RefUserModel {

	public RefUserModel(UserModel userModel) {
		this.userModel = userModel;
	}
	
	public RefUserModel() {
		
	}
	
	private UserModel userModel = null;

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}
	
}

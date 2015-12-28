package demo.testing;

/**
 * 打印组件字符串信息方便测试.
 * 
 * @author suchen
 * @time 2008-7-30 下午02:58:29
 * @email xiaochen_su@126.com
 */
public class PrintComponentStr {
	
	public static void main(String[] args) {
		System.out.println(toUserModel(10));
	}
	
	/*
	 * <component id = "um_0" class = "demo.component.UserModel">
		<setter name = "id">
			<property>
				<int>1</int>
			</property>
		</setter>
		
		<setter name = "userName">
			<property>
				<string>xiaochen_su</string>
			</property>
		</setter>
				
		<setter name = "password">
			<property>
				<string>suchen_23137162</string>
			</property>
		</setter>
		
		<setter name = "email">
			<property>
				<string>xiaochen_su@126.com</string>
			</property>
		</setter>
	</component>
	 */
	
	
	public static String toUserModel(int count) {
		StringBuffer buffer = new StringBuffer();
		
		for( int i = 0; i < count; i++) {
			buffer.append("<component id = \"um_").append(i).append("\" class = \"demo.component.UserModel\">\n");
			buffer.append("\t<setter name = \"id\"><property><int>").append(i).append("</int></property></setter>\n");
			buffer.append("\t<setter name = \"userName\"><property><string>xiaochen_su#").append(i).append("</string></property></setter>\n");
			buffer.append("\t<setter name = \"password\"><property><string>suchen_23137162#").append(i).append("</string></property></setter>\n");
			buffer.append("\t<setter name = \"email\"><property><string>xiaochen_su@126.com#").append(i).append("</string></property></setter>\n");
			buffer.append("</component>\n");
		}
		
		return buffer.toString();
	}
}

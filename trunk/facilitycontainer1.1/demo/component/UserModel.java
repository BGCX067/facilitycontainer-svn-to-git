package demo.component;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * 用户模型.
 * 
 * @author suchen
 * @time 2008-7-30 上午10:41:30
 * @email xiaochen_su@126.com
 */
public class UserModel {
	private int id;
	private String password;
	private String userName;
	private String email;
	
	public UserModel() {
		
	}
	
	public UserModel(int id, String userName, String password, String email) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void print() {
		
		System.out.println(toString());
	}
	
	public void print(PrintStream out, String title) {
		out.print(title + " # ");
		out.println(toString());
	}
	
	public void print(OutputStream out, String title) {
		try {
			out.write(("print(OutputStream, String) # " + title + " # " + toString()).getBytes());
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public String toString() {
		return "[id] " + id + " [userName] " + userName + " [password] " + password + " [email] " + email;
	}
	
}

package demo.component;

import java.util.Date;
import java.util.Map;
import java.util.Iterator;

/**
 * 用户模型缓存.
 * 
 * @author suchen
 * @time 2008-7-30 上午10:44:14
 * @email xiaochen_su@126.com
 */
public class UserModelCache {
	
	private String name;
	private Date createDate;
	private Map<String, UserModel> userModelCache = null;
	
	public UserModelCache() {
		
	}
	
	public UserModelCache(String name, Date createDate, Map<String, UserModel> userModelCache) {
		this.createDate = createDate;
		this.userModelCache = userModelCache;
		this.name = name;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
	
	public Map<String, UserModel> getUserModelCache() {
		return userModelCache;
	}

	public void setUserModelCache(Map<String, UserModel> userModelCache) {
		this.userModelCache = userModelCache;
	}
	
	public String toString() {
		return " name " + name + " create date " + createDate;
	}
	
	public String cacheToStr() {
		StringBuffer buffer = new StringBuffer();
		
		int i = 0;
		
		for(Iterator<String> iterator = userModelCache.keySet().iterator(); iterator.hasNext(); ) {
			String key = iterator.next();
			buffer.append(i++).append(". ").append( key ).append(" = ").append(userModelCache.get(key));
		}
		
		return buffer.toString();
	}

}

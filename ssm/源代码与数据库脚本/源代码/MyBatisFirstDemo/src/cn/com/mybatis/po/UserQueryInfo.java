package cn.com.mybatis.po;

public class UserQueryInfo {
	//在这里包装需要的查询条件  
    
    //用户查询条件  
    private UserInstance userInstance;

	public UserInstance getUserInstance() {
		return userInstance;
	}
	public void setUserInstance(UserInstance userInstance) {
		this.userInstance = userInstance;
	}  
    
    //包装其他的查询条件，如购物车、商品信息等  
    //......  
}

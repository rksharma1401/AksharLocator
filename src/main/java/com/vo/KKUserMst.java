package main.java.com.vo;

public class KKUserMst {
	
	
	private Long userId;
	private Long mobileNO;
	private String userName;
	private String email;
	private String password;
	private Long parentUserId;
	private Integer roleId;
	private  Integer activeFlag;
	
	private  Boolean loogedIn;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getMobileNO() {
		return mobileNO;
	}
	public void setMobileNO(Long mobileNO) {
		this.mobileNO = mobileNO;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getParentUserId() {
		return parentUserId;
	}
	public void setParentUserId(Long parentUserId) {
		this.parentUserId = parentUserId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}
	public Boolean getLoogedIn() {
		return loogedIn;
	}
	public void setLoogedIn(Boolean loogedIn) {
		this.loogedIn = loogedIn;
	}
	
	
	
	


	
	


	
	
}

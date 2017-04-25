package main.java.com.vo;

public class KkRoleMst {
	private Integer roleId;
	private String roleName;
	private  Integer activeFlag;
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}
	
	/*create table kk_role_mst
	(
	role_id number(20) primary key,
	role_name varchar(200),
	active_flag number(5)

	);*/
}

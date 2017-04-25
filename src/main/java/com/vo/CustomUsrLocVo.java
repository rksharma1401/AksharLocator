package main.java.com.vo;

import java.util.Date;

public class CustomUsrLocVo {
	private Long userId;
	private Long mobileNO;
	private String userName;
	
	private Integer roleId;
	
	private String latitude;
	private String longitude;
	private String lastAddress;
	
	private  Integer latestRec;
	private Integer hours;
	private Integer timein_min;
	private Long parentUserId;
	
	public CustomUsrLocVo(Long userId, Long mobileNO, String userName,
			Integer roleId, String latitude, String longitude,
			String lastAddress, Integer hours,
			Integer timein_min,Long parentId) {
		super();
		this.userId = userId;
		this.mobileNO = mobileNO;
		this.userName = userName;
		this.roleId = roleId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.lastAddress = lastAddress;
		this.parentUserId=parentId;
		this.hours = hours;
		this.timein_min = timein_min;
	}

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

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLastAddress() {
		return lastAddress;
	}

	public void setLastAddress(String lastAddress) {
		this.lastAddress = lastAddress;
	}

	public Integer getLatestRec() {
		return latestRec;
	}

	public void setLatestRec(Integer latestRec) {
		this.latestRec = latestRec;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	public Integer getTimein_min() {
		return timein_min;
	}

	public void setTimein_min(Integer timein_min) {
		this.timein_min = timein_min;
	}
	
}

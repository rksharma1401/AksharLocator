package main.java.com.vo;


/*create table kk_user_locaion_txn
(
locatin_txn_id number(20) primary key,
user_id number(20),
lngitude number(20),
latitude number(20),
latest_rec number(5),
creation_date Date,
hours number(20),
timein_min number(20),
active_flag number(5),
last_address varchar2(250)

);*/
public class KkUserLocaionTxn {
	private  Long locationTxnId;
	private  Long userId;
	private String latitude;
	private String longitude;
	private String lastAddress;
	private  Integer activeFlag;
	private  Integer latestRec;
	private Integer hours;
	private Integer timein_min;
	private String createdDate;
	public Long getLocationTxnId() {
		return locationTxnId;
	}
	public void setLocationTxnId(Long locationTxnId) {
		this.locationTxnId = locationTxnId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public Integer getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
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
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
}

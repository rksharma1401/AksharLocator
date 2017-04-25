package main.java.com.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import main.java.com.Dao.KKDaoImpl;
import main.java.com.vo.KKUserMst;
import main.java.com.vo.KkUserLocaionTxn;

public class KKServiceImpl {
	private static final Logger logger=Logger.getLogger(KKServiceImpl.class);
	
	public Boolean checkRegistration(String pwd,Long phoneNO)
	{
		logger.info("inside KKServiceImpl: checkRegistration");
		boolean flag=false;
		try {
			KKDaoImpl dao=new KKDaoImpl();
			if(phoneNO!=null)
			{
				flag=dao.checkRegistration(pwd, phoneNO);	
			}
			
		} catch (Exception e) {e.printStackTrace();
			//e.printStackTrace();
			logger.error("Error KKServiceImpl: checkRegistration",e);
		}
		logger.info("end KKServiceImpl: checkRegistration");
		return flag;
	}
	/*public Boolean validateLogin(String pwd,Long phoneNO)
	{
		try {
			
			KKDaoImpl dao=new KKDaoImpl();
			
		} catch (Exception e) {e.printStackTrace();
			e.printStackTrace();
		}
		return true;
	}*/

	public Boolean registerUser(Long mobileNO, String userName, String email,
			String password, Long parentUserId, Integer roleId,
			Integer activeFlag)
	{
		boolean valueSaved=false;
		try {
			logger.info("start KKServiceImpl: registerUser");
			KKDaoImpl dao=new KKDaoImpl();
			boolean flag=false;
			flag=dao.checkRegistration(password, mobileNO);
			if(!flag)
			{
			KKUserMst userMst= new KKUserMst();
			userMst.setMobileNO(mobileNO);
			userMst.setEmail(email);
			userMst.setUserName(userName);
			userMst.setPassword(password);
			userMst.setRoleId(roleId);
			userMst.setActiveFlag(activeFlag);
		
			dao.save(userMst);
			valueSaved=true;
			}
			
			
		} catch (Exception e) {e.printStackTrace();
			logger.error("Error KKServiceImpl: registerUser",e);
		}
		logger.info("end KKServiceImpl: registerUser");
		return valueSaved;
	}
	/*public Boolean updateDeleteUser(String pwd,Long phoneNO)
	{
		try {
			
		} catch (Exception e) {e.printStackTrace();
			e.printStackTrace();
		}
		return true;
	}*/
	
	/*public List<Object> getLocationData(Long userId,String userName)
	{
		try {
			
		} catch (Exception e) {e.printStackTrace();
			e.printStackTrace();
		}
		return null;
	}*/
	
	/*public Boolean serachUser(String userName,Long phoneNO,String email)
	{
		try {
			
		} catch (Exception e) {e.printStackTrace();
			e.printStackTrace();
		}
		return true;
	}*/

	public Boolean insertUpdateUserLocation(Long locationTxnId, Long userId,
			String latitude, String longitude, String lastAddress,
			Integer activeFlag, Integer latestRec, Integer hours,
			Integer timein_min, String createdDate,boolean insertFlag)
	{
		try {
			logger.info("start KKServiceImpl: insertUpdateUserLocation");
			KkUserLocaionTxn trnObj=null;
			 boolean flag=false;
			KKDaoImpl dao=new KKDaoImpl();
			if(insertFlag)
			{
				if(userId!=null)
				{
					dao.updatePrevLocation(0, userId, null, null, null, null, null);	
				}
				
				
			trnObj=new KkUserLocaionTxn();
			
			trnObj.setUserId(userId);
			trnObj.setLatitude(latitude);
			trnObj.setLongitude(longitude);
			trnObj.setLastAddress(lastAddress);
			trnObj.setLatestRec(latestRec);
			trnObj.setActiveFlag(activeFlag);
			trnObj.setTimein_min(timein_min);
			trnObj.setHours(hours);
			trnObj.setCreatedDate(createdDate);
			
			dao.save(trnObj);
			}
			else
			{
				//load obj and update
				
				if(userId!=null)
				{
					
					dao.updatePrevLocation(1, userId, latitude, longitude, lastAddress, hours, timein_min);
				}
				
			}
			
			
		} catch (Exception e) {e.printStackTrace();
			logger.error("Error KKServiceImpl: insertUpdateUserLocation",e);
		}
		logger.info("end KKServiceImpl: insertUpdateUserLocation");
		return true;
	}
	
	
	/*public List showHistory(Long userId,String userName)
	{
		try {
			
		} catch (Exception e) {e.printStackTrace();
			e.printStackTrace();
		}
		return null;
	}*/
}

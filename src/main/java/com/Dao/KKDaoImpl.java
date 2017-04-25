package main.java.com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;












import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

//main.java.com.common
import  main.java.com.common.MySessison;
import main.java.com.vo.CustomUsrLocVo;
import main.java.com.vo.KKUserMst;



public class KKDaoImpl {
	
	/*PreparedStatement ps=null;
	Connection con=null;
	ResultSet rs=null;*/
	private static final Logger logger=Logger.getLogger(KKDaoImpl.class);
	public Boolean checkRegistration(String pwd,Long mobileNo)
	{
		boolean flag=false;
		logger.info("inside KKDaoImpl: checkRegistration");
		try {
			Session session =MySessison.getMySession().getFactory().openSession();
			Criteria crit=session.createCriteria(KKUserMst.class);
			if(mobileNo!=null)
			{
				crit.add(Restrictions.eq("mobileNO", mobileNo));	
			}
			
			List<KKUserMst> KKUserMstList=crit.list();
			if(KKUserMstList!=null && !KKUserMstList.isEmpty())
			{


				flag=true;	
			}
			
		} catch (Exception e) {e.printStackTrace();
			e.printStackTrace();
		}
		logger.info("end KKDaoImpl: checkRegistration");
		return flag;
	}
	public List<KKUserMst> getUserList(Long userId,Long parentUserID,Integer roleId,Integer activeFlag,String userName ,Long mobileNo)
	{
		boolean flag=false;
		List<KKUserMst> KKUserMstList=null;
		logger.info("start KKDaoImpl: getUserList");
		try {
			Session session =MySessison.getMySession().getFactory().openSession();
			Criteria crit=session.createCriteria(KKUserMst.class);
			if(mobileNo!=null)
			{
				crit.add(Restrictions.eq("mobileNO", mobileNo));	
			}
			if(userId!=null)
			{
				crit.add(Restrictions.eq("userId", userId));	
			}
			if(parentUserID!=null)
			{
				crit.add(Restrictions.eq("parentUserId", parentUserID));	
			}
			if(roleId!=null)
			{
				crit.add(Restrictions.eq("roleId", roleId));	
			}
			if(activeFlag!=null)
			{
				crit.add(Restrictions.eq("activeFlag", activeFlag));	
			}
			if(userName!=null)
			{
				crit.add(Restrictions.like("userName", userName));	
			}
			
			KKUserMstList=crit.list();
			
			
		} catch (Exception e) {e.printStackTrace();
			logger.error("error KKDaoImpl: getUserList",e);
		}
		logger.info("end KKDaoImpl: getUserList");
		return KKUserMstList;
	}
	public KKUserMst validateLogin(String pwd,Long mobileNo)
	{
		KKUserMst objKkUserMst=null;
		logger.info("start KKDaoImpl: validateLogin");
		try {
			
			Session session =MySessison.getMySession().getFactory().openSession();
			Criteria crit=session.createCriteria(KKUserMst.class);
			if(mobileNo!=null)
			{
				crit.add(Restrictions.eq("mobileNO", mobileNo));	
			}
			if(pwd!=null && !("").equals(pwd))
			{
				crit.add(Restrictions.eq("password", pwd));	
			}
			//crit.add(Restrictions.eq("activeFlag", 1));	
			List<KKUserMst> KKUserMstList=crit.list();
			if(KKUserMstList!=null && !KKUserMstList.isEmpty())
			{
				objKkUserMst=KKUserMstList.get(0);
				
			}
			
			
			
		} catch (Exception e) {e.printStackTrace();
			logger.error("error KKDaoImpl: validateLogin",e);
		}
		logger.info("end KKDaoImpl: validateLogin");
		return objKkUserMst;
	}
	
	public Boolean save(Object obj)
	{
		try {
			
			logger.info("start KKDaoImpl: save");
			Session session= MySessison.getMySession().getFactory().openSession();
			Transaction tx=session.beginTransaction();
					session.save(obj);	
			
			tx.commit();
			
			
		} catch (Exception e) {e.printStackTrace();
			logger.error("Error KKDaoImpl: save",e);
		}
		logger.info("end KKDaoImpl: save");
		return true;
	}
	public Boolean updateUserData(Integer activeFlag,Integer roleId,Long userIdAry[],Long userId,Long parentId)
	{
		int result=0;
		boolean flag=false;
		try {
			logger.info("start KKDaoImpl: updateUserData");
			StringBuilder builder=new StringBuilder();
			builder.append("update KKUserMst set ");
			if(activeFlag!=null)
			{
				builder.append(" activeFlag=:activeFlag  ");	
			}
			if(roleId!=null)
			{
				if(activeFlag!=null)
			{
					builder.append(" ,");
			}
				builder.append(" roleId=:roleId ");	
			}
			if(parentId!=null)
			{
				builder.append(" ,parentUserId=:parentId ");	
			}
			if(userIdAry!=null)
			{
				builder.append("where userId in(:userIdAry)");	
			}
			if(userId!=null)
			{
				builder.append("where userId in(:userId)");	
			}
			
			
			Session session=MySessison.getMySession().getFactory().openSession();
			Transaction tx=session.beginTransaction();
			Query query=session.createQuery(builder.toString());
			if(activeFlag!=null)
			{
				query.setInteger("activeFlag", activeFlag);	
			}
			if(roleId!=null)
			{
				query.setInteger("roleId", roleId)	;
			}
			if(userIdAry!=null)
			{
				query.setParameterList("userIdAry", userIdAry);
			}
			if(userId!=null)
			{
				query.setLong("userId", userId)	;
			}
			if(parentId!=null)
			{
				query.setLong("parentId", parentId)	;
			}
			result=query.executeUpdate();
			tx.commit();
			if(result>0)
			{
				flag=true;
			}
	System.out.println(result);
	
		} catch (Exception e) {e.printStackTrace();
			logger.error("error KKDaoImpl: updateUserData",e);
		}
		logger.info("end KKDaoImpl: updateUserData");
		return flag;
	}
	
	
	public Boolean updatePrevLocation(Integer latestRec,Long userId,String latitude, String longitude, String lastAddress,Integer hours,Integer timein_min)
	{
		int result=0;
		boolean flag=false;
		Transaction tx=null;
		logger.info("start KKDaoImpl: updatePrevLocation");
		try {
			StringBuilder builder=new StringBuilder();
			builder.append("update KkUserLocaionTxn set ");
			if(latestRec!=null)
			{
				builder.append(" latestRec=:latestRec  ");	
			}
			if(latitude!=null)
			{
				if (latestRec != null) {
					builder.append(" ,");
				}
				builder.append(" latitude=:latitude ");
			}
			if(longitude!=null)
			{
				if (latitude != null) {
					builder.append(" ,");
				}
				builder.append(" longitude=:longitude ");	
			}
			
			if(lastAddress!=null)
			{
				if (longitude != null) {
					builder.append(" ,");
				}
				builder.append(" lastAddress=:lastAddress ");	
			}
			
			if(hours!=null)
			{
				if (lastAddress != null) {
				builder.append(" ,");
			}
				builder.append(" hours=:hours ");	
			}
			if(timein_min!=null)
			{
				if (hours != null) {
					builder.append(" ,");
				}
				builder.append(" hours=:timein_min ");	
			}
			
			
			if(userId!=null)
			{
				builder.append(" where userId in(:userId) and latestRec=1 ");	
			}
			
			
			Session session=MySessison.getMySession().getFactory().openSession();
			 tx=session.beginTransaction();
			Query query=session.createQuery(builder.toString());
			
			if(latestRec!=null)
			{
				query.setInteger("latestRec", latestRec);
			}
			if(latitude!=null)
			{
				query.setParameter("latitude", latitude);
			}
			if(longitude!=null)
			{
				query.setParameter("longitude", longitude);
			}
			if(hours!=null)
			{
				query.setParameter("hours", hours);
			}
			if(timein_min!=null)
			{
				query.setParameter("timein_min", timein_min);
			}
			if(lastAddress!=null)
			{
				query.setParameter("lastAddress", lastAddress);
			}
		
			if(userId!=null)
			{
				query.setLong("userId", userId)	;
			}
			
			result=query.executeUpdate();
			tx.commit();
			if(result>0)
			{
				flag=true;
			}
	System.out.println(result);
	
		} catch (Exception e) {e.printStackTrace();
			tx.rollback();
			logger.error("error KKDaoImpl: updatePrevLocation",e);
		}
		logger.info("end KKDaoImpl: updatePrevLocation");
		return flag;
	}
	
	public 	List<CustomUsrLocVo> getUserListWithLocation(Long userId,Integer activeFlag,Integer latestRec,boolean showHistory,Long parentId,String date)
	{
		List<CustomUsrLocVo> customUsrLocVoList=null;
		try {
			logger.info("start KKDaoImpl: getUserListWithLocation");
			StringBuilder builder=new StringBuilder();
		/*	if(showHistory)
			{
				builder.append("select mst1.user_name,txn.last_address, mst1.user_id,mst1.mobile_no");
				builder.append("from KkUserLocaionTxn txn, KKUserMst mst1,KKUserMst mst2");
				builder.append("where mst1.userId= txn.userId");
				builder.append("and mst1.activeFlag=:activeFlag");
				builder.append("and txn.latestRec=:latestRec");
				builder.append("and mst1.parentUserId= mst2.userId");
				builder.append("and mst2.userId=:userId");
				builder.append("and mst2.activeFlag=:activeFlag");
			}
			else
			{
				
			}*/
		/*	private Long userId;
			private Long mobileNO;
			private String userName;
			
			private Integer roleId;
			
			private String latitude;
			private String longitude;
			private String lastAddress;
			
			private  Integer latestRec;
			private Integer hours;
			private Integer timein_min;
			*/
			// new CustomUsrLocVo(userId, mobileNO, userName, roleId, latitude, longitude, lastAddress, latestRec, hours, timein_min)
			//new CustomUsrLocVo(userId, mobileNO, userName, roleId, latitude, longitude, lastAddress, hours, timein_min)
			
			builder.append(" select  new com.vo.CustomUsrLocVo(mst1.userId,mst1.mobileNO,mst1.userName,mst1.roleId , txn.latitude, txn.longitude,  txn.lastAddress,txn.hours,  txn.timein_min,mst1.parentUserId) ");
			builder.append(" from KKUserMst as mst1  ,  KkUserLocaionTxn as txn  ");
			
			
			builder.append("where  mst1.userId= txn.userId");
			
			if(parentId!=null)
			{
		//	builder.append(" and mst1.parentUserId= mst2.userId");
			builder.append(" and mst1.parentUserId= :parentId");
			
			}

			if(userId!=null)
			{
				builder.append(" and mst1.userId=:userId");		
			}
			
			if(activeFlag!=null)
			{
			builder.append("  and mst1.activeFlag=:activeFlag");
			//builder.append(" and mst2.activeFlag=:activeFlag");
			}
			if(latestRec!=null)
			{
				builder.append(" and txn.latestRec=:latestRec");	
			}
			
			if(date!=null)
			{
				builder.append(" and txn.createdDate=:date");	
			}
			
		
			builder.append(" order by mst1.userName,txn.hours,  txn.timein_min ");	
			
			// need to add date filter also
			Session session=MySessison.getMySession().getFactory().openSession();
			Query query=session.createQuery(builder.toString());
			if(activeFlag!=null)
			{
				query.setInteger("activeFlag", activeFlag);
			}
			if(latestRec!=null)
			{
				query.setInteger("latestRec", latestRec);
			}
			if(userId!=null)
			{
				query.setLong("userId", userId);
			}
			if(parentId!=null)
			{
				query.setLong("parentId", parentId);
			}
			if(date!=null)
			{
				query.setParameter("date", date);
			}
			
			customUsrLocVoList=query.list();
			
		} catch (Exception e) {e.printStackTrace();
			logger.error("error KKDaoImpl: getUserListWithLocation",e);
		}
		logger.info("end KKDaoImpl: getUserListWithLocation");
		return customUsrLocVoList;
	}

	public Boolean updateUserRec(Object obj,boolean showHistory)
	{
		try {
			logger.info("start KKDaoImpl: updateUserRec");
			
			StringBuilder builder=new StringBuilder();
			if(showHistory)
			{
				builder.append("update KKUserMst");
				builder.append("from KkUserLocaionTxn txn,  mst1,KKUserMst mst2");
				builder.append("where mst1.userId= txn.userId");
				builder.append("and mst1.activeFlag=1");
				builder.append("and txn.latestRec=1");
				builder.append("and mst1.parentUserId= mst2.userId");
				builder.append("and mst2.userId=1");
				builder.append("and mst2.activeFlag=1");
			}
			else
			{
				
			}
			
		} catch (Exception e) {e.printStackTrace();
			//e.printStackTrace();
			logger.error("error KKDaoImpl: updateUserRec",e);
		}
		logger.info("end KKDaoImpl: updateUserRec");
		return true;
	}

}

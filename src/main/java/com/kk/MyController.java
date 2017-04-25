package main.java.com.kk;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path; 
import javax.ws.rs.Produces; 
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;  

import org.apache.log4j.Logger;

import main.java.com.Dao.KKDaoImpl;
import com.google.gson.Gson;
import main.java.com.service.KKServiceImpl;
import main.java.com.vo.CustomUsrLocVo;
import main.java.com.vo.KKUserMst;

@Path("/MainController")
public class MyController {
	public static final String Success="<result>success</result>";
	public static final String failure="<result>fail</result>";
	public static final String mobileNOAvailble="<result>mobileAvail</result>";
	public static final String notRegistred="<result>user not registered</result>";
	public static final String login_sucess="<result>Login Sucess</result>";
	public static final String login_fail="<result>Userid or Password incorrect</result>";
	
	private static final Logger logger=Logger.getLogger(MyController.class);
	
	 SimpleDateFormat ft = 
		      new SimpleDateFormat ("dd-MM-YYY");
	 
	 	Date dd=new Date();


	Gson gson=new Gson();
		@POST 
		@Path("/validateLogin") 
	    @Produces(MediaType.APPLICATION_JSON) 
	  
	   public String validateLogin(@FormParam("mobileNo") String mobileNo,@FormParam("password") String password,@Context HttpServletResponse servletResponse){ 
		  
		   String finalString="";
		   logger.info("inside MyController: validateLogin");
		   try {
			
			   KKDaoImpl dao=new KKDaoImpl();
			   KKUserMst user=null;
			 
			   if(mobileNo!=null && !("").equals(mobileNo) && password!=null && !("").equals(password))
			   {
				   user=dao.validateLogin(password,Long.parseLong(mobileNo)) ;
				   if(user!=null)
				   {
					   user.setLoogedIn(true);
				   }
				   else
				   {
					   user=new KKUserMst();
					   user.setLoogedIn(false);
					   
				   }
				   
			   }
			 
			 
			   finalString=gson.toJson(user);
		   System.out.println(finalString);
		 
		   }
		  catch (Exception e) {e.printStackTrace();
			   e.printStackTrace();
			   logger.error("Error in  MyController: validateLogin",e);
			}
		   logger.info("end MyController: validateLogin");
	      return finalString; 
	   
	}
	   
			@POST 
		   @Path("/insertLocationData") 
		   @Produces(MediaType.APPLICATION_XML) 
		   public String insertLocationData(@FormParam("userId") String userId,
				   @FormParam("latitude") String latitude,
				   @FormParam("longitude") String longitude,
				   @FormParam("lastAddress") String lastAddress,
				   @FormParam("hours") String hours,
				   @FormParam("action") String action,@Context HttpServletRequest servletRequest,
				   @Context HttpServletResponse servletResponse){ 
			   try {
				   logger.info("start in  MyController: insertLocationData");
				 // System.out.println("insertLocationData is calling");
			   
			   KKServiceImpl kkServiceImpl=new KKServiceImpl();
			   Date dd=new Date();
			   dd.getHours();
			   dd.getMinutes();
			   boolean insertFlag=true;
			   if(action!=null && !("").equals(action))
			   {
				   if("update".equals(action))
				   {
					   insertFlag=false;
				   }
			   }
			   Long usrId=null;
			   
			   if(userId!=null && !("").equals(userId))
				  {
					  usrId=Long.parseLong(userId);
				  }
			   kkServiceImpl.insertUpdateUserLocation(null, usrId, latitude, longitude, lastAddress, 1, 1, dd.getHours(), dd.getMinutes(), ft.format(dd), insertFlag);
			  
			   System.out.println("insertLocationData");
			   }
			  catch (Exception e) {e.printStackTrace();
					// TODO: handle exception
				   logger.error("Error in  MyController: insertLocationData",e);
				}
			   logger.info("end in  MyController: insertLocationData");
		      return Success; 
		   
		}
	   
	   @POST 
	   @Path("/resisterUser") 
	  
	   @Produces(MediaType.APPLICATION_XML) 
	   public String resisterUser(@FormParam("mobileNo") String mobileNo,
			   @FormParam("name") String name,
			   @FormParam("email") String email,
			   @FormParam("password") String password,
			   @Context HttpServletRequest servletRequest,
			   @Context HttpServletResponse servletResponse){ 
		   Gson gs=new Gson();
		   String finalStr=failure;
		   logger.info("start in  MyController: resisterUser");
		   try {
		   KKServiceImpl kkServiceImpl=new KKServiceImpl();
		   Long mobNo=null;
		   if(mobileNo!=null && !("").equals(mobileNo))
		   {
			   mobNo=Long.parseLong(mobileNo) ;
		   }
		   if( kkServiceImpl.checkRegistration(null, mobNo))
		   {
			   finalStr=mobileNOAvailble;
		   }
		   else
		   {
			   kkServiceImpl.registerUser(mobNo, name, email, password,
					   null, 5, 1);   
			   finalStr=Success;
		   }
		   
		   
		   
		   }
		  catch (Exception e) {e.printStackTrace();
				// TODO: handle exception
			   logger.error("Error in  MyController: resisterUser",e);
			}
		   logger.info("end in  MyController: resisterUser"); 
	      return finalStr; 
	   
	}
	   
	   @POST 
	   @Path("/getUserList") 
	   @Produces(MediaType.APPLICATION_JSON) 
	   public String getUserList(@FormParam("mobileNo") String mobileNo,
			   @FormParam("roleId") String roleId,
			   @FormParam("userId") String userId,
			   @FormParam("parentUserId") String parentUserId,
			   @FormParam("userName") String userName,
			   @FormParam("action") String action,@Context HttpServletRequest servletRequest,
			   @Context HttpServletResponse servletResponse){ 
		   String finalString="";
		   try {
			   logger.info("start in  MyController: getUserList"); 
			   Long mobNum=null;
			   Integer rolId=null;
			   Long parentUsrId=null;
			   Long usrId=null;
			   Long mobileN=null;
			  if(roleId!=null && !("").equals(roleId))
			  {
				  rolId=Integer.parseInt(roleId);
			  }
			   
			  if(parentUserId!=null && !("").equals(parentUserId))
			  {
				  parentUsrId=Long.parseLong(parentUserId);
			  }
			  if(userId!=null && !("").equals(userId))
			  {
				  usrId=Long.parseLong(userId);
			  }
			  if(mobileNo!=null && !("").equals(mobileNo))
			  {
				  mobileN=Long.parseLong(mobileNo);
			  }
			  if(action!=null && !("").equals(action))
			  {
				  if(action.equalsIgnoreCase("deleteUsers"))
				  {
					 
					  parentUsrId=usrId;
					  usrId=null;
					  mobileN=null;
					  rolId=null;
					
				  }
				  else 
				  {
					  rolId=5; 
					  usrId=null;
					  mobileN=null;
					  
				  }
				  
				 
			  }
			  KKDaoImpl dao=new KKDaoImpl();
			 List<KKUserMst> userList= dao.getUserList(usrId, parentUsrId, rolId, 1, userName, mobileN);
			 if(userList!=null && !userList.isEmpty())
			 {
				 finalString=gson.toJson(userList);
			 }
		  
		   }
		  catch (Exception e) {e.printStackTrace();
				// TODO: handle exception
			   logger.error("Error in  MyController: getUserList",e);
			}
		   logger.info("end in  MyController: getUserList");
	      return finalString; 
	   
	}
	   @POST 
	   @Path("/updateUsersMpg") 
	   @Produces(MediaType.APPLICATION_XML) 
	   public String updateUsermpg(@FormParam("mobileNo") String mobileNo,
			   @FormParam("roleId") String roleId,
			   @FormParam("userId") String userId,
			   @FormParam("userIdArray") String userIdArray,
			   @FormParam("mpgLevel") String mpgLevel
			   
			   ,
			   @FormParam("parentUserId") String parentUserId,
			   
			   @FormParam("userName") String userName,
			   @FormParam("action") String action,@Context HttpServletRequest servletRequest,
			   @Context HttpServletResponse servletResponse){ 
		   String finalStr=failure;
		   try {
			   logger.info("start in  MyController: updateUsermpg");
			   Long[] userIdArry=null;
			   String[] userIdArryStr=null;
			   int size=0;
			   Integer rolId=null;
			   Integer activateFlag=null;
			   Long usrId=null;
			   Long parentId=null;
			   if(userId!=null && !("").equals(userId))
			   {
				   usrId=Long.parseLong(userId);
			   }
			   
			   KKDaoImpl dao=new KKDaoImpl();
		 if(userIdArray!=null && !("").equals(userIdArray))
		 {
			 userIdArryStr=userIdArray.split(",");
			 
			 size=userIdArryStr.length;
			 userIdArry=new Long[size];
			 for(int i=0;i<size;i++)
			 {
				 userIdArry[i]=Long.parseLong(userIdArryStr[i]);
			 }
			 
			 
		 }
		 if(mpgLevel!=null && !("").equals(mpgLevel))
		 {
			 activateFlag=1;
			 if(mpgLevel.equals("MapAdmin"))
			 {
				 rolId=2;
				 parentId=usrId;
			 }
			 else if(mpgLevel.equals("MapBranchAdmin"))
			 {
				 rolId=3;
				 parentId=usrId;
			 }
			 else if(mpgLevel.equals("NormalUser"))
			 {
				 rolId=4;
				 parentId=usrId;
			 }
			 else 
			 {
				 rolId=4;
			 }
		 }
		 
		 
		 if(action!=null && !("").equals(action))
		 {
			 if(("deleteUsers").equals(action))
			 {
				 activateFlag=0;
				 rolId=null;
			 }
		 }
		 boolean flag=false;
		 
		 flag= dao.updateUserData(activateFlag, rolId, userIdArry, null,parentId);
		   if(flag)
		   {
			   finalStr=Success;
		   }
		 
		   }
		  catch (Exception e) {e.printStackTrace();
				// TODO: handle exception
			   logger.error("Error in  MyController: updateUsermpg",e);
			}
		   logger.info("end in  MyController: updateUsermpg");
	      return finalStr; 
	   
	}
	   
	   @POST 
	   @Path("/getUserListWithLoc") 
	   @Produces(MediaType.APPLICATION_JSON) 
	   public String getUserListWithLoc(@FormParam("mobileNo") String mobileNo,
			   @FormParam("roleId") String roleId,
			   @FormParam("userId") String userId
			   //@FormParam("") String userId,
			  ,@Context HttpServletRequest servletRequest,
			   @Context HttpServletResponse servletResponse){ 
		   String finalString="";
		   try {
			   logger.info("start in  MyController: getUserListWithLoc");
			   Long mobNum=null;
			   Integer rolId=null;
			   Long parentUsrId=null;
			   Long usrId=null;
			   Long mobileN=null;
			  if(roleId!=null && !("").equals(roleId))
			  {
				  rolId=Integer.parseInt(roleId);
			  }
			   
			 
			  if(userId!=null && !("").equals(userId))
			  {
				  usrId=Long.parseLong(userId);
			  }
			  if(mobileNo!=null && !("").equals(mobileNo))
			  {
				  mobileN=Long.parseLong(mobileNo);
			  }
			 
			  KKDaoImpl dao=new KKDaoImpl();
			  List<CustomUsrLocVo> customUsrLocVos=null;
			  if(rolId!=null &&  rolId.equals(4))
			  {
				  customUsrLocVos= dao.getUserListWithLocation(usrId, 1, 1, false, null,ft.format(dd));
			  }
			  else
			  {
				  customUsrLocVos= dao.getUserListWithLocation(null, 1, 1, false, usrId,null
						  
						  );  
			  }
			  
			  finalString=  gson.toJson(customUsrLocVos);
		   }
		  catch (Exception e) {e.printStackTrace();
			   logger.error("Error in  MyController: getUserListWithLoc",e);
			}
		   logger.info("end in  MyController: getUserListWithLoc");
	      return finalString; 
	   
	}
	   @POST 
	   @Path("/viewHistory") 
	   @Produces(MediaType.APPLICATION_JSON) 
	   public String showHistory(@FormParam("mobileNo") String mobileNo,
			   @FormParam("roleId") String roleId,
			   @FormParam("userId") String userId
			  ,@Context HttpServletRequest servletRequest,
			   @Context HttpServletResponse servletResponse){ 
		   String finalString="";
		   try {
			   logger.info("start in  MyController: showHistory");
			   Long mobNum=null;
			   Integer rolId=null;
			   Long parentUsrId=null;
			   Long usrId=null;
			   Long mobileN=null;
			  if(roleId!=null && !("").equals(roleId))
			  {
				  rolId=Integer.parseInt(roleId);
			  }
			   
			 
			  if(userId!=null && !("").equals(userId))
			  {
				  usrId=Long.parseLong(userId);
			  }
			  if(mobileNo!=null && !("").equals(mobileNo))
			  {
				  mobileN=Long.parseLong(mobileNo);
			  }
			 
			  KKDaoImpl dao=new KKDaoImpl();
			  
			  List<CustomUsrLocVo> customUsrLocVos= dao.getUserListWithLocation(usrId, 1, null, false, null,ft.format(dd));
			  finalString=  gson.toJson(customUsrLocVos);
		   }
		  catch (Exception e) {e.printStackTrace();
			   logger.error("Error in  MyController: showHistory",e);
			}
		   logger.info("end in  MyController: showHistory");
	      return finalString; 
	   
	}
	   
}


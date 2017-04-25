package main.java.com.common;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MySessison 
{
	
	 public static MySessison mySession=null;
	 private SessionFactory factory;
	private  MySessison() {
		// TODO Auto-generated constructor stub
	}
	public static MySessison getMySession()
	{
		System.out.println("getMySession calling");
		if(mySession==null)
		{
			System.out.println("getMySession calling null");
			mySession=new MySessison();
			Configuration cfg=new Configuration();
			//src\main\resources
			cfg.configure("/main/resources/hibernate.cfg.xml");
			 SessionFactory sessionFactory = cfg.buildSessionFactory();
			mySession.setFactory(sessionFactory);
		}
		else
		{
			
		}
		return mySession;
	}

	public SessionFactory getFactory() {
		return factory;
	}
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

}

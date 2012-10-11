package knt.exceedvote.ui;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

import knt.exceedvote.controller.PasswordHash;
import knt.exceedvote.controller.SSLMail;
import knt.exceedvote.model.Login;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.cfg.Configuration;



import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class LoginDAO {

public static boolean checkUser(String uid){
	

	  Session session = null;

	  try{
		  // This step will read hibernate.cfg.xml and prepare hibernate for use

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session =sessionFactory.openSession();
		  //Create new instance of Contact and set values in it by reading them from form object

		 List userlist = session.createCriteria(Login.class)
				 .add(Restrictions.like("uid", uid))
				 .setProjection( Projections.projectionList()
				 .add(Projections.count("uid"))
				 )
				 .list();

		 long useramount = (long) userlist.get(0);
		 if (useramount == 1){
		 return true;
		 }
		 else{
			 return false;
		 }
		  }catch(Exception e){
		  System.out.println(e.getMessage());
		  return false;
		  
		  }finally{
		  // Actual contact insertion will happen at this step
		  session.flush();
		  session.close();

		  }

  }
  
	  public static boolean checkpassword(String uid, String password){
  
		  Session session = null;

		  try{
			  // This step will read hibernate.cfg.xml and prepare hibernate for use

			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session =sessionFactory.openSession();
			
			
			  //Create new instance of Contact and set values in it by reading them from form object

			 List<Login> user = session.createCriteria(Login.class)
					 .add(Restrictions.like("uid", uid))
					 .list();

			 
			 if(PasswordHash.validatePassword(password, user.get(0).getPassword())){
				 return true;
			 } else {
				 return false;
			 }

			  }catch(Exception e){
			  System.out.println(e.getMessage());
			  return false;
			  
			  }finally{
			  // Actual contact insertion will happen at this step
			  session.flush();
			  session.close();
			  
			  }
	  }
	  
	  public static boolean checkFirstlogin(String uid){
		  
		  Session session = null;

		  try{
			  // This step will read hibernate.cfg.xml and prepare hibernate for use

			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session =sessionFactory.openSession();
			
			
			  //Create new instance of Contact and set values in it by reading them from form object

			 List<Login> user = session.createCriteria(Login.class)
					 .add(Restrictions.like("uid", uid))
					 .list();

			 if(user.get(0).isFirstlogin() == 1){
				 return true;
			 } else {
				 return false;
			 }
			 
			  }catch(Exception e){
			  System.out.println(e.getMessage());
			  return false;
			  
			  }finally{
			  // Actual contact insertion will happen at this step
			  session.flush();
			  session.close();
			  
			  }
	  }
	

	  
		public static void insertUser(String uid){
			
			

			  Session session = null;

			  try{
				  // This step will read hibernate.cfg.xml and prepare hibernate for use

				SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
				session =sessionFactory.openSession();
				
				  SecureRandom random = new SecureRandom();  
				  String password = new BigInteger(130, random).toString(32);  
				

				Login newUser = new Login();
				newUser.setUid(uid);
				newUser.setPassword(PasswordHash.createHash(password));
				newUser.setTyid(1);
				newUser.setFirstlogin(1);
				
				Transaction transaction = null;
				transaction = session.beginTransaction();
				session.save(newUser);
				transaction.commit();

				SSLMail.sendMail(uid, password, "register");

				  }catch(Exception e){
				  System.out.println(e.getMessage());
				  
				  }finally{
				  // Actual contact insertion will happen at this step
				  session.flush();
				  session.close();
				  
				  }
			
			
		}
		
}
package test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
	

}

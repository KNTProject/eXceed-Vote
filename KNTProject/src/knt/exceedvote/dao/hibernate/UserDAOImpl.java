package knt.exceedvote.dao.hibernate;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Collection;
import java.util.List;

import knt.exceedvote.dao.DaoFactory;
import knt.exceedvote.dao.UserDAO;
import knt.exceedvote.model.Login;
import knt.exceedvote.system.PasswordHash;
import knt.exceedvote.system.SSLMail;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.cfg.Configuration;



import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 * DAO for Login Object
 * @author Thomas Raudenbusch
 *
 */
public class UserDAOImpl implements UserDAO {

/* (non-Javadoc)
 * @see knt.exceedvote.dao.hibernate.UserDAO#checkUser(java.lang.String)
 */
@Override
public boolean checkUser(String uid){
	

	  Session session = null;

	  try{
		  // This step will read hibernate.cfg.xml and prepare hibernate for use

		session = getSession();
		
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
  
	  /* (non-Javadoc)
	 * @see knt.exceedvote.dao.hibernate.UserDAO#checkpassword(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean checkpassword(String uid, String password){
  
		  Session session = null;

		  try{
			  // This step will read hibernate.cfg.xml and prepare hibernate for use

			session = getSession();
			
			
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
	
	  /* (non-Javadoc)
	 * @see knt.exceedvote.dao.hibernate.UserDAO#updatetUser(knt.exceedvote.model.Login)
	 */
	@Override
	public boolean updatetUser(Login newUser){

		  Session session = null;

		  try{
			  // This step will read hibernate.cfg.xml and prepare hibernate for use

			session = getSession();
			
			
			Transaction transaction = null;
			transaction = session.beginTransaction();
			session.update(newUser);
			transaction.commit();
			return true;
			
			


			  }catch(Exception e){
	
			  System.out.println(e.getMessage());
			  return false;
			  
			  }finally{
			  // Actual contact insertion will happen at this step
			  session.flush();
			  session.close();
			  
			  }
		
		
	}
	  
		/* (non-Javadoc)
		 * @see knt.exceedvote.dao.hibernate.UserDAO#insertUser(java.lang.String)
		 */
		@Override
		public boolean insertUser(String uid){
			
			

			  Session session = null;

			  try{
				  // This step will read hibernate.cfg.xml and prepare hibernate for use

				session = getSession();
				
				  SecureRandom random = new SecureRandom();  
				  String password = new BigInteger(130, random).toString(32);  
				

				Login newUser = new Login(uid, PasswordHash.createHash(password), 1, 1);
				Transaction transaction = null;
				transaction = session.beginTransaction();

				session.save(newUser);
				transaction.commit();

				if (! SSLMail.sendMail(newUser.getUid(), password))
				{

					session.flush();
					session.clear();
					//session.close();

					transaction = session.beginTransaction();

					session.delete(newUser);
					transaction.commit();

					return false;
				}
				return true;

				  }catch(Exception e){
				  System.out.println(e.getMessage());
				  return false;
				  
				  }finally{
				  // Actual contact insertion will happen at this step
				  session.flush();
				  session.close();
				  
				  }
	
		}


		@Override
		public Login getUser(String uid) {

			Session session = null;
			try{
			session = getSession();
			
			
			  //Create new instance of Contact and set values in it by reading them from form object

			 List<Login> user = session.createCriteria(Login.class)
					 .add(Restrictions.like("uid", uid))
					 .list();

			 return user.get(0);
			 

			  }catch(Exception e){
	
			  System.out.println(e.getMessage());
			  return null;
			  
			  }finally{
			  // Actual contact insertion will happen at this step
			  session.flush();
			  session.close();
			  
			  }
			
		}
		
		
		@Override
		public Collection<Login> getAllUsers() {

			Session session = null;
			try{
			session = getSession();
			
			
			  //Create new instance of Contact and set values in it by reading them from form object

			 List<Login> users = session.createCriteria(Login.class)
					 .list();

			 Collection<Login> collection = users;
			 
			 return collection;
			 

			  }catch(Exception e){
	
			  System.out.println(e.getMessage());
			  return null;
			  
			  }finally{
			  // Actual contact insertion will happen at this step
			  session.flush();
			  session.close();
			  
			  }
			
		}
		
		
		
		/**
		 * @return
		 */
		private Session getSession() {
			Session session = DaoFactory.getInstance("hibernate").getSessionFactory().openSession();
			return session;
		}

	
}

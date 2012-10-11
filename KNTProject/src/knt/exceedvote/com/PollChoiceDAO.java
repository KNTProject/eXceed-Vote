package knt.exceedvote.com;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 * Class to handle the details for one Poll from the Database
 * Its the DAO for PollChoice.java
 * @author Thomas Raudenbusch
 *
 */
public class PollChoiceDAO {

	/**
	 * The method gets one Poll from the database
	 * @param pid
	 * the Poll ID which is stored at the db
	 * @return
	 * All data from this Poll ID which is stored in the db
	 */
	public static List<PollChoice> getChoices(int pid){

		  Session session = null;

		  try{
			  // This step will read hibernate.cfg.xml and prepare hibernate for use

			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session =sessionFactory.openSession();
			
			
			  //Create new instance of Contact and set values in it by reading them from form object

				 List<PollChoice> teams = session.createCriteria(PollChoice.class)
						 .add(Restrictions.like("pid", pid))
						 .list();
				
			
			return teams;

			  }catch(Exception e){
			  System.out.println(e.getMessage());
			  return null;
			  
			  }finally{
			  // Actual contact insertion will happen at this step
			  session.flush();
			  session.close();
			  
			  }
	}
	

}

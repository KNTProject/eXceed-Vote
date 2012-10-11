package knt.exceedvote.ui;

import java.util.List;

import knt.exceedvote.model.Poll;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 * Model for Poll Object
 * @author Thomas Raudenbusch
 *
 */
public class PollDAO {

	
	
	public static List<Poll> getPolls(Integer pid){

		  Session session = null;

		  try{
			  // This step will read hibernate.cfg.xml and prepare hibernate for use

			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session =sessionFactory.openSession();
			
			
			  //Create new instance of Contact and set values in it by reading them from form object
			List<Poll> polls;
			if (pid == null) {
			 polls = session.createCriteria(Poll.class)
					 .list();
			}
			else {
				 polls = session.createCriteria(Poll.class)
						 .add(Restrictions.like("pid", pid))
						 .list();
				}	
			
			return polls;

			  }catch(Exception e){
				  Logger log = Logger.getLogger( PollDAO.class );
				  log.error(e);
			  return null;
			  
			  }finally{
			  // Actual contact insertion will happen at this step
			  session.flush();
			  session.close();
			  
			  }

		
	}


}

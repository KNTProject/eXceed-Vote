package knt.exceedvote.dao.hibernate;

import java.util.List;

import knt.exceedvote.dao.PollChoiceDAO;
import knt.exceedvote.model.PollChoice;

import org.apache.log4j.Logger;
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
public class PollChoiceDAOImpl implements PollChoiceDAO {

	/**
	 * The method gets one Poll from the database
	 * @param pid
	 * the Poll ID which is stored at the db
	 * @return
	 * All data from this Poll ID which is stored in the db
	 */
	public List<PollChoice> getChoices(int pid){

		  Session session = null;

		  try{
			  // This step will read hibernate.cfg.xml and prepare hibernate for use
			  
			session = getSession();
			
			
			  //Create new instance of Contact and set values in it by reading them from form object

				 List<PollChoice> teams = session.createCriteria(PollChoice.class)
						 .add(Restrictions.like("pid", pid))
						 .list();
				
			
			return teams;

			  }catch(Exception e){
				  Logger log = Logger.getLogger( PollChoiceDAOImpl.class );
				  log.error(e);
			  return null;
			  
			  }finally{
			  // Actual contact insertion will happen at this step
			  session.flush();
			  session.close();
			  
			  }
	}

	/**
	 * @param sessionFactory
	 * @return
	 */
	private Session getSession() {
		Session session =DaoFactoryImpl.getInstance().getSessionFactory().openSession();
		return session;
	}
	

}

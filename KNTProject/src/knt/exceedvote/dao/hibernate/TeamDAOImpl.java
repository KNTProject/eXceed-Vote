package knt.exceedvote.dao.hibernate;

import java.util.List;

import knt.exceedvote.dao.DaoFactory;
import knt.exceedvote.dao.TeamDAO;
import knt.exceedvote.model.Team;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 * Model for Team Object
 * @author Thomas Raudenbusch
 *
 */
public class TeamDAOImpl implements TeamDAO {

	

	/* (non-Javadoc)
	 * @see knt.exceedvote.dao.hibernate.TeamDAO#getTeam(java.lang.Integer)
	 */
	@Override
	public List<Team> getTeams(){

		  Session session = null;

		  try{
			  // This step will read hibernate.cfg.xml and prepare hibernate for use
			session = getSession();
			
			
			  //Create new instance of Contact and set values in it by reading them from form object

				 List<Team> team = session.createCriteria(Team.class)
						 .list();
				
			
			return team;

			  }catch(Exception e){
				  Logger log = Logger.getLogger( TeamDAOImpl.class );
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
	 * @throws HibernateException
	 */
	private static Session getSession()
			throws HibernateException {
		Session session =DaoFactory.getInstance("hibernate").getSessionFactory().openSession();
		return session;
	}

	

}

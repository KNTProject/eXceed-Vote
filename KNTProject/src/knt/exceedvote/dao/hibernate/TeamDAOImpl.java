package knt.exceedvote.dao.hibernate;

import java.util.List;

import knt.exceedvote.dao.DaoFactory;
import knt.exceedvote.dao.TeamDAO;
import knt.exceedvote.model.Login;
import knt.exceedvote.model.Team;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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

	@Override
	public boolean addTeam(Team team) {
		Session session = null;

		  try{
		session = getSession();
		
		
		Transaction transaction = null;
		transaction = session.beginTransaction();
		session.save(team);
		transaction.commit();
		return true;
		
		
		  }catch(Exception e){
			  Logger log = Logger.getLogger( PollDAOImpl.class );
			  log.error(e);
		  return false;
		  
		  }finally{
		  // Actual contact insertion will happen at this step
		  session.flush();
		  session.close();
		  
		  }
		
	}
	
	
	public void deleteTeam(int tid){
		
		  Session session = getSession();
		  try{
			  Transaction transaction = session.beginTransaction();
			  String hql = "delete from Team where tid= :tid";
			  Query query = session.createQuery(hql);
			  query.setInteger("tid", tid);
			  query.executeUpdate();
			  transaction.commit();
		
	  }catch(Exception e){
		  
	  Logger log = Logger.getLogger( VoteDAOImpl.class );
	  log.error(e);	  
	  
	  }finally{
	  // Clean connection
	  session.flush();
	  // Close connection
	  session.close();
	  
	  }
	}
}

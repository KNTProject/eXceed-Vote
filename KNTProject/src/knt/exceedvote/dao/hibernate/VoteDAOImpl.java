package knt.exceedvote.dao.hibernate;


import java.util.ArrayList;
import java.util.List;

import knt.exceedvote.dao.DaoFactory;
import knt.exceedvote.dao.VoteDAO;
import knt.exceedvote.model.Login;
import knt.exceedvote.model.Poll;
import knt.exceedvote.model.Vote;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;



/**
 * Class for put a vote into the database
 * It is the DAO for Vote.java
 * @author Thomas Raudenbusch
 *
 */

public class VoteDAOImpl implements VoteDAO {

	/* (non-Javadoc)
	 * @see knt.exceedvote.dao.hibernate.VoteDAO#insertVote(knt.exceedvote.model.Vote)
	 */
	@Override
	public boolean insertVote(Vote newVote){
		
		  // Always set null to avoid problems
		  Session session = getSession();

		  try{
			  
				// This step will read hibernate.cfg.xml and prepare hibernate for use
				@SuppressWarnings("deprecation")
				//session = getSession();
				
				
				//Begin the db input
				Transaction transaction = null;
				transaction = session.beginTransaction();
				session.save(newVote);
				transaction.commit();
				return true;

		  }catch(Exception e){
	
				  Logger log = Logger.getLogger( VoteDAOImpl.class );
				  log.error(e);
				  
				  return false;
				  
				  }finally{
				  // Clean connection
				  session.flush();
				  // Close connection
				  session.close();
			  
				  }

	}


	/* (non-Javadoc)
	 * @see knt.exceedvote.dao.hibernate.VoteDAO#getVotes(java.lang.String)
	 */
	@Override
	public List<Vote> getVotes(String uid){
		
		  // Always set null to avoid problems
		  Session session = getSession();

		  try{
			  
			// This step will read hibernate.cfg.xml and prepare hibernate for use
			@SuppressWarnings("deprecation")
			
			List voteList = new ArrayList<Vote>();
		
				
			//Create a new Vote object and put into the attributs
			voteList = session.createCriteria(Vote.class)
					 .add(Restrictions.like("uid", uid)
					 )
					 .list();
			
			// voteList = session.createCriteria(Vote.class)
			//		.add(arg0)
			
		  
			 return voteList;

			  }catch(Exception e){

			  Logger log = Logger.getLogger( VoteDAOImpl.class );
			  log.error(e);
			  
			  return null;
			  
			  }finally{
			  // Clean connection
			  session.flush();
			  // Close connection
			  session.close();
			  
			  }

	}
	
	
	
	public Vote getVote(int vid){
		
		  // Always set null to avoid problems
		  Session session = getSession();

		  try{
			  
			// This step will read hibernate.cfg.xml and prepare hibernate for use
			@SuppressWarnings("deprecation")
			
			List<Vote> voteList = new ArrayList<Vote>();
		
				
			//Create a new Vote object and put into the attributs
			voteList = session.createCriteria(Vote.class)
					 .add(Restrictions.like("vid", vid)
					 )
					 .list();
			
			// voteList = session.createCriteria(Vote.class)
			//		.add(arg0)
			
			 return voteList.get(0);

			  }catch(Exception e){

			  Logger log = Logger.getLogger( VoteDAOImpl.class );
			  log.error(e);
			  
			  return null;
			  
			  }finally{
			  // Clean connection
			  session.flush();
			  // Close connection
			  session.close();
			  
			  }

	}
		
	

	/**
	 * @return
	 */
	private Session getSession() {
		Session session =DaoFactory.getInstance("hibernate").getSessionFactory().openSession();
		return session;
	}
	

}

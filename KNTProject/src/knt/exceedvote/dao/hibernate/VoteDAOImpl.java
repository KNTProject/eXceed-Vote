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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
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
		
		  
		  Session session = getSession();

		  try{
			  

				
				
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
	
	@Override
	public Vote checkVote(String user, Poll poll) {
		// TODO Auto-generated method stub
		  Session session = getSession();

		  try{
			  
		  Criteria criteria = session.createCriteria(Vote.class);

		  Criterion uid = Restrictions.like("uid", user);
		  Criterion pid = Restrictions.like("pid", poll.getPid());
			  
	        LogicalExpression expression = Restrictions.and(uid, pid);
	        
			criteria.add(expression);
	        List<Vote> vote = criteria.list();
			if (vote == null) return null;
	        if (vote.size() > 0) return vote.get(0);
	        else return null;
		
		
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
	
	

	@Override
	public boolean updateVote(Vote vote) {

		  Session session = getSession();

		  try{
			  Transaction tr = session.beginTransaction();

			  session.update(vote);
			  tr.commit();

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
	
	public boolean deleteVote(Login user, int pid){
		
		  Session session = getSession();
		  try{
			  Transaction transaction = session.beginTransaction();
			  String hql = "delete from Vote where uid= :uid AND pid= :pid";
			  Query query = session.createQuery(hql);
			  query.setString("uid", user.getUid());
			  query.setInteger("pid", pid);
			  query.executeUpdate();
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
	
	
	
	public static void main (String args[]){
		

		VoteDAO v = DaoFactory.getInstance("hibernate").getVoteDao();
		System.out.println(v.deleteVote(new Login("123", "", 1, 1), 1));
		
	}
	

	/**
	 * @return
	 */
	private Session getSession() {
		Session session =DaoFactory.getInstance("hibernate").getSessionFactory().openSession();
		return session;
	}





	

}

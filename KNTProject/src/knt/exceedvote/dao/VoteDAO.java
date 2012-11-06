package knt.exceedvote.dao;


import java.util.ArrayList;
import java.util.List;

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



/**
 * Class for put a vote into the database
 * It is the DAO for Vote.java
 * @author Thomas Raudenbusch
 *
 */

public class VoteDAO {

	/**
	 * Method that put a new vote into the db
	 * @param uid
	 * Is the ID of the UserSession
	 * @param pid
	 * Is the ID of the Poll
	 * @param tid
	 * Is the ID of the Team
	 * @param votes
	 * Is the amount of Votes smbdy give a team
	 * @return
	 * return true when input was okay, return false if smth goes wrong
	 */
	public static boolean insertVote(Vote newVote){
		
		  // Always set null to avoid problems
		  Session session = null;

		  try{
			  
			// This step will read hibernate.cfg.xml and prepare hibernate for use
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session =sessionFactory.openSession();
			
			
			//Begin the db input
			Transaction transaction = null;
			transaction = session.beginTransaction();
			session.save(newVote);
			transaction.commit();
			return true;

			  }catch(Exception e){

			  Logger log = Logger.getLogger( VoteDAO.class );
			  log.error(e);
			  
			  return false;
			  
			  }finally{
			  // Clean connection
			  session.flush();
			  // Close connection
			  session.close();
			  
			  }

	}

	public static List<Vote> getVotes(String uid){
		
		  // Always set null to avoid problems
		  Session session = null;

		  try{
			  
			// This step will read hibernate.cfg.xml and prepare hibernate for use
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session =sessionFactory.openSession();
			
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

			  Logger log = Logger.getLogger( VoteDAO.class );
			  log.error(e);
			  
			  return null;
			  
			  }finally{
			  // Clean connection
			  session.flush();
			  // Close connection
			  session.close();
			  
			  }

	}
	
	public static void main(String args[]){
		
		List<Vote> v = getVotes("123");
		
		for (Vote b : v){
			System.out.println(b.getPid());
		}
	}

}

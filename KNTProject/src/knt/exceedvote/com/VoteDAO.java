package knt.exceedvote.com;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Class for put a vote into the database
 * It is the DAO for Vote.java
 * @author Thomas Raudenbusch
 *
 */

public class VoteDAO {

	/**
	 * Methode that put a new vote into the db
	 * @param uid
	 * Is the ID of the User
	 * @param pid
	 * Is the ID of the Poll
	 * @param tid
	 * Is the ID of the Team
	 * @param votes
	 * Is the amount of Votes smbdy give a team
	 * @return
	 * return true when input was okay, return false if smth goes wrong
	 */
	public static boolean insertVote(String uid, int pid, int tid, int votes){
		
		  // Always set null to avoid problems
		  Session session = null;

		  try{
			  
			// This step will read hibernate.cfg.xml and prepare hibernate for use
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session =sessionFactory.openSession();
			
			//Create a new Vote object and put into the attributs
			Vote newVote = new Vote();
			newVote.setPid(pid);
			newVote.setUid(uid);
			newVote.setTid(tid);
			newVote.setVotes(votes);
			
			//Begin the db input
			Transaction transaction = null;
			transaction = session.beginTransaction();
			session.save(newVote);
			transaction.commit();
			return true;

			  }catch(Exception e){
			  //Must be smth better
			  System.out.println(e.getMessage());
			  return false;
			  
			  }finally{
			  // Clean connection
			  session.flush();
			  // Close connection
			  session.close();
			  
			  }

	}

}

package test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class VoteDAO {

	
	public static void insertVote(String uid, int pid, int tid, int votes){
		
		

		  Session session = null;

		  try{
			  // This step will read hibernate.cfg.xml and prepare hibernate for use

			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session =sessionFactory.openSession();
			
			Vote newVote = new Vote();
			newVote.setPid(pid);
			newVote.setUid(uid);
			newVote.setTid(tid);
			newVote.setVotes(votes);
			Transaction transaction = null;
			transaction = session.beginTransaction();
			session.save(newVote);
			transaction.commit();



			  }catch(Exception e){
			  System.out.println(e.getMessage());
			  
			  }finally{
			  // Actual contact insertion will happen at this step
			  session.flush();
			  session.close();
			  
			  }
		
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		VoteDAO.insertVote("123", 1, 1, 1);
		
	}

}

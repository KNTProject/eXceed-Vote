package knt.exceedvote.dao.hibernate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import knt.exceedvote.dao.DaoFactory;
import knt.exceedvote.dao.PollDAO;
import knt.exceedvote.model.Login;
import knt.exceedvote.model.Poll;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * Model for Poll Object
 * @author Thomas Raudenbusch
 *
 */
public class PollDAOImpl implements PollDAO {

	
	
	public Poll getPoll(int pid){

		  Session session = null;

		  try{
			  // This step will read hibernate.cfg.xml and prepare hibernate for use
			session = getSession();
			
			
			  //Create new instance of Contact and set values in it by reading them from form object
			Poll poll;
			

			
			poll = (Poll) session.createCriteria(Poll.class)
				 .add(Restrictions.like("pid", pid))
				 .uniqueResult();	
				
				
			return poll;

			  }catch(Exception e){
				  Logger log = Logger.getLogger( PollDAOImpl.class );
				  log.error(e);
			  return null;
			  
			  }finally{
			  // Actual contact insertion will happen at this step
			  session.flush();
			  session.close();
			  
			  }
		  
	}
	
	public List<Poll> getAll(){

		  Session session = null;

		  try{
			  // This step will read hibernate.cfg.xml and prepare hibernate for use
			  
			session = getSession();
			
			
			  //Create new instance of Contact and set values in it by reading them from form object

				 List<Poll> ballots = session.createCriteria(Poll.class)
						 .list();
				
			
			return ballots;

			  }catch(Exception e){
				  Logger log = Logger.getLogger( PollDAOImpl.class );
				  log.error(e);
			  return null;
			  
			  }finally{
			  // Actual contact insertion will happen at this step
			  session.flush();
			  session.close();
			  
			  }
	}
	
	public List<Poll> getNotVotedYet(Login user){
		  
		Session session = null;

		  try{
			  
		  
		session = getSession();

		String hql = "SELECT p FROM Poll p WHERE p.pid NOT IN (SELECT v.pid FROM Vote v WHERE v.uid =  :uid)";
		Query query = session.createQuery(hql);
		query.setParameter("uid", user.getUid());
		List<Poll> polls = query.list();
		return polls;
		
		  }catch(Exception e){
			  Logger log = Logger.getLogger( PollDAOImpl.class );
			  log.error(e);
		  return null;
		  
		  }finally{
		  // Actual contact insertion will happen at this step
		  session.flush();
		  session.close();
		  
		  }
	}
	
	public List<Poll> getVoted(Login user){
		  
		Session session = null;

		  try{
			  
		  
		session = getSession();

		String hql = "SELECT p FROM Poll p WHERE p.pid IN (SELECT v.pid FROM Vote v WHERE v.uid =  :uid)";
		Query query = session.createQuery(hql);
		query.setParameter("uid", user.getUid());
		List<Poll> polls = query.list();
		return polls;
		
		  }catch(Exception e){
			  Logger log = Logger.getLogger( PollDAOImpl.class );
			  log.error(e);
		  return null;
		  
		  }finally{
		  // Actual contact insertion will happen at this step
		  session.flush();
		  session.close();
		  
		  }
	}
	
	public boolean addPoll(Poll poll){
		Session session = null;

		  try{
		session = getSession();
		
		
		Transaction transaction = null;
		transaction = session.beginTransaction();
		session.save(poll);
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

	/**
	 * @param sessionFactory
	 * @return
	 * @throws HibernateException
	 */
	private Session getSession()
			throws HibernateException {
		Session session =DaoFactory.getInstance("hibernate").getSessionFactory().openSession();
		return session;
	}
	
	public static void main (String args[]){
		
		PollDAO p = DaoFactory.getInstance("hibernate").getPollDao();
		
		Login l = new Login("harry", "1", 1, 1);
		
		List<Poll> polls = p.getNotVotedYet(l);
		
		for(Poll a : polls){
			System.out.println(a.getPid() + " " + a.getName());
		}
		
		System.out.println("-- break --");
		List<Poll> polls2 = p.getVoted(l);
		
		for(Poll a : polls2){
			System.out.println(a.getPid() + " " + a.getName());
		}
		
	}

}

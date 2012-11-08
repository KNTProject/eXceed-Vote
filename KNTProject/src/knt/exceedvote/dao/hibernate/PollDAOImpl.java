package knt.exceedvote.dao.hibernate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import knt.exceedvote.dao.PollDAO;
import knt.exceedvote.model.Poll;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * Model for Poll Object
 * @author Thomas Raudenbusch
 *
 */
public class PollDAOImpl implements PollDAO {

	
	
	public List<Poll> getPolls(Integer pid){

		  Session session = null;

		  try{
			  // This step will read hibernate.cfg.xml and prepare hibernate for use
			session = getSession();
			
			
			  //Create new instance of Contact and set values in it by reading them from form object
			List<Poll> polls = new ArrayList();
			
			if (pid == null) {
			//java.util.Date date = new Date();
			//Timestamp timestamp = new Timestamp(date.getTime());
			 polls = session.createCriteria(Poll.class)
			//		 .add(Restrictions.gt("deadline", timestamp))
					 .list();
			} else
			{
			polls = session.createCriteria(Poll.class)
				 .add(Restrictions.like("pid", pid))
				 .list();	
				
				
			}
			/*
			else if(type.equals("with")){
								
				for(int i : pid){
					Criteria criteria = session.createCriteria(Poll.class);
						 criteria.add(Restrictions.like("pid", i));
						 polls.add((Poll) criteria.list().get(0));
				}
				
				
				
				}
			else if(type.equals("notwith")){
				
					Criteria criteria = session.createCriteria(Poll.class);

					for (int i : pid){
					criteria.add(Restrictions.not(Restrictions.like("pid", i)));
					}

				polls = criteria.list();
				
				}
			*/
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

	/**
	 * @param sessionFactory
	 * @return
	 * @throws HibernateException
	 */
	private Session getSession()
			throws HibernateException {
		Session session =DaoFactoryImpl.getInstance().getSessionFactory().openSession();
		return session;
	}
	

}

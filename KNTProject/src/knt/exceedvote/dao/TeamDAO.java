package knt.exceedvote.dao;

import java.util.List;

import knt.exceedvote.model.Team;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 * Model for Team Object
 * @author Thomas Raudenbusch
 *
 */
public class TeamDAO {

	

	public static List<Team> getTeam(int tid){

		  Session session = null;

		  try{
			  // This step will read hibernate.cfg.xml and prepare hibernate for use

			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session =sessionFactory.openSession();
			
			
			  //Create new instance of Contact and set values in it by reading them from form object

				 List<Team> team = session.createCriteria(Team.class)
						 .add(Restrictions.like("tid", tid))
						 .list();
				
			
			return team;

			  }catch(Exception e){
				  Logger log = Logger.getLogger( TeamDAO.class );
				  log.error(e);
			  return null;
			  
			  }finally{
			  // Actual contact insertion will happen at this step
			  session.flush();
			  session.close();
			  
			  }
	}

	

}

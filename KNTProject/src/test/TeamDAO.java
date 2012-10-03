package test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

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
			  System.out.println(e.getMessage());
			  return null;
			  
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

		List<Team> t = TeamDAO.getTeam(1);
		for (Team tt : t){
			
			System.out.println(tt.name);
			
		}
		
	}

}

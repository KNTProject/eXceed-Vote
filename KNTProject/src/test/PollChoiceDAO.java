package test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class PollChoiceDAO {

	
	public static List<PollChoice> getChoices(int pid){

		  Session session = null;

		  try{
			  // This step will read hibernate.cfg.xml and prepare hibernate for use

			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session =sessionFactory.openSession();
			
			
			  //Create new instance of Contact and set values in it by reading them from form object

				 List<PollChoice> teams = session.createCriteria(PollChoice.class)
						 .add(Restrictions.like("pid", pid))
						 .list();
				
			
			return teams;

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

		List<PollChoice> teams = PollChoiceDAO.getChoices(1);
		for (PollChoice p : teams){
			System.out.println(p.pcid);
			System.out.println(p.pid);
			System.out.println(p.tid);

		}
		
	}

}

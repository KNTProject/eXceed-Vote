package knt.exceedvote.dao;

import knt.exceedvote.dao.UserDAO;
import knt.exceedvote.dao.hibernate.UserDAOImpl;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Factory for creating DAOs for specific entity types.
 * @author NUTJA
 *
 */
public abstract class DaoFactory {
	private static DaoFactory factory; // the real instance
	
	
	public static DaoFactory getInstance() {
		if (factory == null) {
			init(); // initialize and create factory object
		}
		return factory;
	}
	
	private static void init() {
		// no initialization to do yet, just create the instance
		
	}
	

	public abstract UserDAO getUserDao();
	public abstract PollDAO getPollDao();
	public abstract PollChoiceDAO getPollChoiceDao();
	public abstract TeamDAO getTeamDao();
	public abstract VoteDAO getVoteDao();
	
	public SessionFactory getSessionFactory(){
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		return sessionFactory;
	}
}


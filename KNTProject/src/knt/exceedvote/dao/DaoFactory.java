package knt.exceedvote.dao;

import knt.exceedvote.dao.UserDAO;
import knt.exceedvote.dao.hibernate.DaoFactoryImpl;
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
	
	
	public static DaoFactory getInstance(String type) {
		if (type.equals("hibernate")){
			if (factory == null) {
				factory = new DaoFactoryImpl();
			}
			
			return factory;
		}
		return null;}

	
	public abstract void init();

	

	public abstract UserDAO getUserDao();
	public abstract PollDAO getPollDao();
	public abstract PollChoiceDAO getPollChoiceDao();
	public abstract TeamDAO getTeamDao();
	public abstract VoteDAO getVoteDao();
	
	public abstract SessionFactory getSessionFactory();
}


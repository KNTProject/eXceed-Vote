package knt.exceedvote.dao.hibernate;

import knt.exceedvote.dao.*;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Factory for creating DAOs for specific entity types.
 * @author NUTJA
 *
 */
public class DaoFactoryImpl extends DaoFactory{
	private static DaoFactoryImpl factory; // the real instance

	private UserDAO userDao = null;
	private VoteDAO voteDao = null;
	private TeamDAO teamDao = null;
	private PollDAO pollDao = null;
	private PollChoiceDAO pollChoiceDao = null;
	
	
	public static DaoFactoryImpl getInstance() {
		if (factory == null) {
			init(); // initialize and create factory object
		}
		return factory;
	}
	
	private static void init() {
		// no initialization to do yet, just create the instance
		factory = new DaoFactoryImpl();
	}
	
	/**
	 * Get an instance of the User DAO object
	 * @return a UserDao
	 */
	public UserDAO getUserDao() {
		if (userDao == null) userDao = new UserDAOImpl();
		return userDao;
	}
	
	public PollDAO getPollDao(){
		if (pollDao == null) pollDao = new PollDAOImpl();
		return pollDao;
	}
	public PollChoiceDAO getPollChoiceDao(){
		if (pollChoiceDao == null) pollChoiceDao = new PollChoiceDAOImpl();
		return pollChoiceDao;
	}
	public TeamDAO getTeamDao(){
		if (teamDao == null) teamDao = new TeamDAOImpl();
		return teamDao;
	}
	public VoteDAO getVoteDao(){
		if (voteDao == null) voteDao = new VoteDAOImpl();
		return voteDao;
	}
	
	public SessionFactory getSessionFactory(){
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		return sessionFactory;
	}
}


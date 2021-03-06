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

	private UserDAO userDao = null;
	private VoteDAO voteDao = null;
	private TeamDAO teamDao = null;
	private PollDAO pollDao = null;
	private RankingDAO rankingDao = null;

	
	
	
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

	public TeamDAO getTeamDao(){
		if (teamDao == null) teamDao = new TeamDAOImpl();
		return teamDao;
	}
	public VoteDAO getVoteDao(){
		if (voteDao == null) voteDao = new VoteDAOImpl();
		return voteDao;
	}
	
	public SessionFactory getSessionFactory(){
		SessionFactory sessionFactory = new Configuration()
		.configure("knt/exceedvote/dao/hibernate/cfg/hibernate.cfg.xml")
		.buildSessionFactory();

		return sessionFactory;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RankingDAO getRankingDao() {
		if (rankingDao == null) rankingDao = new RankingDAOImpl();
		return rankingDao;
	}
}


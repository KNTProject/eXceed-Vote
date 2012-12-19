package knt.exceedvote.dao;

import knt.exceedvote.model.Ranking;

public interface RankingDAO {
	public void findWinner();
	public Ranking[] getTeamWinner();
}

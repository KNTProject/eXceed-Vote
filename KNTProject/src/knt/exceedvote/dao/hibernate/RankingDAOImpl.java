package knt.exceedvote.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import knt.exceedvote.dao.DaoFactory;
import knt.exceedvote.dao.PollDAO;
import knt.exceedvote.dao.RankingDAO;
import knt.exceedvote.dao.UserDAO;
import knt.exceedvote.dao.VoteDAO;
import knt.exceedvote.model.Poll;
import knt.exceedvote.model.Rank;
import knt.exceedvote.model.Ranking;
import knt.exceedvote.model.Team;
import knt.exceedvote.model.Vote;

public class RankingDAOImpl implements RankingDAO {

	private Map<Integer, Rank> ranks;
	private List<Integer> pollList;
	private List<Poll> polls;
	private List<Team> teams;
	private List<Vote> votes;
	private int size;
	
	public RankingDAOImpl(List<Poll> polls, List<Team> teams, List<Vote> votes){
		this.polls = polls;
		this.teams = teams;
		this.votes = votes;
		size = polls.size();
		pollList = new ArrayList<Integer>();
		findWinner();
		
	}
	
	public RankingDAOImpl(){
		UserDAO userDao = DaoFactory.getInstance("hibernate").getUserDao();
		VoteDAO voteDao = DaoFactory.getInstance("hibernate").getVoteDao();
		PollDAO pollDao = DaoFactory.getInstance("hibernate").getPollDao();
		this.polls = ;
		this.teams = teams;
		this.votes = votes;
		size = polls.size();
		pollList = new ArrayList<Integer>();
		findWinner();
	}
	
	@Override
	public void findWinner() {
		ranks = new HashMap<Integer, Rank>();
		//int[][] rank = new int[size][3];
		for(int i = 0; i < size; i++){
			Integer poll = polls.get(i).getPid();
			Integer team = teams.get(i).getTid();
			Integer vote = votes.get(i).getVotes();
			if(ranks.containsKey(poll))
				ranks.get(poll).setTeamScore(team, vote);
			else{
				ranks.put(poll, new Rank(team, vote));
				pollList.add(poll);
			}
		}
		/*
		for(int i = 0; i < size; i++){
			rank[i][0] = polls.get(i).getPid();
			rank[i][1] = teams.get(i).getTid();
			rank[i][2] = votes.get(i).getVotes();
		}
		Map<Integer, Integer[][]> ranking = new HashMap<Integer, Integer[][]>();
		for(int i = 0; i < size; i++){
			int poll = polls.get(i).getPid();
			int team = teams.get(i).getTid();
			int vote = votes.get(i).getVotes();
			if(ranking.containsKey(poll))
				ranking.put(poll,)
		}
		
		
		return null;
		*/
	}
	
	public Ranking[] getTeamWinner(){
		Ranking[] ranking = new Ranking[size];
		Iterator it = ranks.values().iterator();
		int index = 0;
		
		while (it.hasNext()) {
			Rank rank = (Rank) it.next();
			ranking[index].setPid(pollList.get(index));
			ranking[index].setTid(rank.findTeamWinner());
			ranking[index].setVotes(rank.findScoreWinner());
			index++;
		}
		
		return ranking;
	}

}

package knt.exceedvote.dao;

import java.util.List;

import knt.exceedvote.model.Vote;

public interface VoteDAO {

	/**
	 * Method that put a new vote into the db
	 * @param uid
	 * Is the ID of the UserSession
	 * @param pid
	 * Is the ID of the Poll
	 * @param tid
	 * Is the ID of the Team
	 * @param votes
	 * Is the amount of Votes smbdy give a team
	 * @return
	 * return true when input was okay, return false if smth goes wrong
	 */
	public boolean insertVote(Vote newVote);

	public List<Vote> getVotes(String uid);

	public Vote getVote(int vid);
}
package knt.exceedvote.dao;

import java.util.List;

import knt.exceedvote.model.Login;
import knt.exceedvote.model.Poll;

import org.hibernate.Session;

public interface PollDAO {
	public Poll getPoll(int pid);
	public List<Poll> getAll();
	public List<Poll> getVoted(Login user);
	public List<Poll> getNotVotedYet(Login user);
}
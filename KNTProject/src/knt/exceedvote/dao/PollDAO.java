package knt.exceedvote.dao;

import java.util.List;

import knt.exceedvote.model.Poll;

import org.hibernate.Session;

public interface PollDAO {
	public List<Poll> getPolls(Integer pid);
}
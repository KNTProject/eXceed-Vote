package knt.exceedvote.dao;

import java.util.List;

import org.hibernate.Session;

import knt.exceedvote.model.PollChoice;

public interface PollChoiceDAO {
	public List<PollChoice> getChoices(int pid);
}
package knt.exceedvote.dao;

import java.util.List;

import knt.exceedvote.model.Team;

public interface TeamDAO {

	public List<Team> getTeam(Integer tid);

}
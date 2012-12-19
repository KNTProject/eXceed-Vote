package knt.exceedvote.dao;

import java.util.List;

import knt.exceedvote.model.Team;

public interface TeamDAO {

	public List<Team> getTeams();
	public boolean addTeam(Team team);
	public void deleteTeam(int tid);
}
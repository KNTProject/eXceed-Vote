package knt.exceedvote.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;




/**
 * Model for Poll Object
 * @author Thomas Raudenbusch
 *
 */
public class Poll {

	private int pid;
	private String name;
	private String description;
	private Date deadline;
	private Set<Team> teams = new HashSet<Team>(0);

	public Set<Team> getTeams() {
		return teams;
	}
	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


}

package knt.exceedvote.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Model for Team Object
 * @author Thomas Raudenbusch
 *
 */
public class Team {

	int tid;
	String name;
	private Set<Poll> polls = new HashSet<Poll>(0);

	
	public Set<Poll> getPolls() {
		return polls;
	}
	public void setPolls(Set<Poll> polls) {
		this.polls = polls;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

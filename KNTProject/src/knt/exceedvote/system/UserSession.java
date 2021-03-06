package knt.exceedvote.system;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import knt.exceedvote.model.*;
/**
 * UserSession session for a website
 * @author Thomas Raudenbusch
 *
 */
public class UserSession {

	Login user;
	List<Poll> notVotedYet;
	List<Poll> voted;
	List<Poll> allPolls;
	List<Team> allTeams;
	DateTime countdown;
	String language;
	
	public UserSession() {
		this.setLanguage("english");
	}
	
	public List<Poll> getAllPolls() {
		return allPolls;
	}
	public void setAllPolls(List<Poll> allPolls) {
		this.allPolls = allPolls;
	}

	
	public List<Team> getAllTeams() {
		return allTeams;
	}

	public void setAllTeams(List<Team> allTeams) {
		this.allTeams = allTeams;
	}

	public DateTime getCountdown() {
		return countdown;
	}
	public void setCountdown(DateTime countdown) {
		this.countdown = countdown;
	}

	public List<Poll> getNotVotedYet() {
		return notVotedYet;
	}
	public void setNotVotedYet(List<Poll> notVotedYet) {
		this.notVotedYet = notVotedYet;
	}
	public List<Poll> getVoted() {
		return voted;
	}
	public void setVoted(List<Poll> voted) {
		this.voted = voted;
	}
	
	public Login getUser() {
		return user;
	}
	public void setUser(Login user) {
		this.user = user;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

	
}

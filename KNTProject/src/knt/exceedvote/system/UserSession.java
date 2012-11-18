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

	String uid;
	int typ;
	List<Poll> notVotedYet;
	List<Poll> voted;
	List<Poll> allPolls;
	DateTime countdown;
	
	public List<Poll> getAllPolls() {
		return allPolls;
	}
	public void setAllPolls(List<Poll> allPolls) {
		this.allPolls = allPolls;
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
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getTyp() {
		return typ;
	}
	public void setTyp(int typ) {
		this.typ = typ;
	}
	
}

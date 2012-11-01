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
	String typ;
	List<Poll> novote;
	List<Poll> voted;
	DateTime countdown;
	
	public DateTime getCountdown() {
		return countdown;
	}
	public void setCountdown(DateTime countdown) {
		this.countdown = countdown;
	}
	public List<Poll> getNovote() {
		return novote;
	}
	public void setNovote(List<Poll> novote) {
		this.novote = novote;
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
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}
	
}

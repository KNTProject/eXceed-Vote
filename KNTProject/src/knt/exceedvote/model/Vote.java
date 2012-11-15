package knt.exceedvote.model;


/**
 * Model for Vote Object
 * @author Thomas Raudenbusch
 *
 */
public class Vote {

	private int vid;
	private String uid;
	private int pid;
	private int tid;
	private int votes;
	
	public Vote(String uid, int pid, int tid, int votes){
		this.uid = uid;
		this.pid = pid;
		this.tid = tid;
		this.votes = votes;
	}
	
	public Vote(int vid, String uid, int pid, int tid, int votes){
		this.vid = vid;
		this.uid = uid;
		this.pid = pid;
		this.tid = tid;
		this.votes = votes;
	}
	
	public Vote() { }
	
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	
	
	public int getVid() {
		return vid;
	}
	public void setVid(int vid) {
		this.vid = vid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
	
}

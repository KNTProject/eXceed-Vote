package knt.exceedvote.model;

import java.util.Date;




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

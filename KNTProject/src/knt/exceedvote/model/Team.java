package knt.exceedvote.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Model for Team Object
 * @author Thomas Raudenbusch
 *
 */
public class Team {

	private int tid;
	private String name;
	private String image1;
	private String image2;
	private String image3;
	private Set<Poll> polls = new HashSet<Poll>(0);

	public Team() { }
	public Team(String name, String image1, String image2, String image3){
		this.name = name;
		this.image1 = image1;
		this.image2 = image2;
		this.image3 = image3;
	}
	
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
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public String getImage3() {
		return image3;
	}
	public void setImage3(String image3) {
		this.image3 = image3;
	}
	
}

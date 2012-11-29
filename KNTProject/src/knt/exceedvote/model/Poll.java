package knt.exceedvote.model;


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;




/**
 * Model for Poll Object
 * @author Thomas Raudenbusch
 *
 */
public class Poll {

	private int pid;
	private String name;
	private String description;
	private String image;
	private Set<Team> teams = new HashSet<Team>(0);

	public Poll() { }
	public Poll(String name, String description){
		this.name = name;
		this.description = description;
	}
	
	
	public Set<Team> getTeams() {
		return teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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

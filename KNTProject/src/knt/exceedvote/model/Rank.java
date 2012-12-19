package knt.exceedvote.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Rank {
	private int pid;
	private Map<Integer,Integer> team;
	private List<Integer> teams;
	private int maxIndex;
	
	public Rank(Integer tid, Integer vote){
		teams = new ArrayList<Integer>();
		team = new HashMap<Integer, Integer>();
		team.put(tid, vote);
	}
	
	public int getPID(){
		return pid;
	}
	
	public void setTeamScore(Integer tid, Integer vote){
		if(team.containsKey(tid)){
			team.put(tid, team.get(tid)+vote);
			teams.add(tid);
		}
		else
			team.put(tid, vote);
	}
	
	public int findTeamWinner(){
		int index = 0;
		maxIndex = 0;
		int maxValue = 0;
		Iterator it = team.values().iterator();

		while (it.hasNext()) {
			int temp = ((Integer)it.next()).intValue();
			if(temp > maxValue) {
				maxValue = temp;
				maxIndex = index;
			}
			index++;
		}
		return teams.get(maxIndex).intValue();
	}
	
	public int findScoreWinner(){
		return team.get(maxIndex);
	}
}

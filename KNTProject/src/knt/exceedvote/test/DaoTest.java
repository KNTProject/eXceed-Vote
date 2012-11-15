package knt.exceedvote.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import knt.exceedvote.*;
import knt.exceedvote.dao.*;
import knt.exceedvote.dao.hibernate.DaoFactoryImpl;
import knt.exceedvote.model.*;

import org.junit.Test;

public class DaoTest {


	public static Login testUserDao() // console test, not a JUnit test
		{
		  Login harry = new Login();
		  harry.setUid("harry");
		  UserDAO dao;
		  dao = DaoFactory.getInstance("hibernate").getUserDao();

		  dao.insertUser(harry.getUid());
		  System.out.println("Saved Harry Potter with id " + harry.getUid() );
		  
		  Login voter =  dao.getUser(harry.getUid());
		  System.out.println( "Found " + voter.getUid() );
		  
		  Collection<Login> all = dao.getAllUsers();
		  
		  System.out.println("Users in database: " + all.size() ); 
		  
		  for(Login x : all) System.out.println(x.getUid());
		     // this output better include Harry Potter!
		  
		  return harry;
		}
		  
		  
	public static void testBallotDao(Login harry) {


		  VoteDAO vote = DaoFactory.getInstance("hibernate").getVoteDao();
		  PollDAO poll = DaoFactory.getInstance("hibernate").getPollDao();

		  List<Poll> pollid = poll.getPolls(null);
		  
		  Vote ballot = new Vote(17, harry.getUid(), pollid.get(0).getPid(), 1, 1);
		  vote.insertVote(ballot);
		  
		  Vote ballot2 = vote.getVote(ballot.getVid());
		  

		  
		// Is it same ballot?
		  if (ballot.getVid() == ballot2.getVid()) System.out.println("ballots are same");
		  else  System.out.println("ballots are NOT same");

		// print the ballot2
		//TODO - print items in the ballot
		  
		  for(Poll p : pollid)
		     System.out.println( p.getDescription() );
		  
		  
}
	
	public static void main(String args[]){
		
		Login harry = testUserDao();

		 testBallotDao(harry);
		
	}
}
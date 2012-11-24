package knt.exceedvote.system;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;

import knt.exceedvote.dao.hibernate.VoteDAOImpl;
import knt.exceedvote.model.Login;

public class Logging {

	public static void login(String ip, Login user){
	
		
			  // Create file 
			 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			 Calendar cal = Calendar.getInstance();

			    File log = new File("log/log_login.log");
			    try{
			    if(log.exists()==false){
			    	log.getParentFile().mkdirs();
			           //Create a new file
			            log.createNewFile();
			    }
			    PrintWriter out = new PrintWriter(new FileWriter(log, true));
			    out.append("******* New Login ******* " + "\r\n");
			    out.append("Time: " + dateFormat.format(cal.getTime()) + " -- User: " + user.getUid() + " -- IP: " + ip + "\r\n");
			    out.close();

			  }catch (Exception e){//Catch exception if any
				  Logger log4j = Logger.getLogger( Logging.class );
				  log4j.error(e);
			  }
	}
	
	public static void logout(String ip, Login user){

		  // Create file 
		 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		 Calendar cal = Calendar.getInstance();

		    File log = new File("log/log_logout.log");
		    try{
		    if(log.exists()==false){
		    	log.getParentFile().mkdirs();
		           //Create a new file
		            log.createNewFile();
		    }
		    PrintWriter out = new PrintWriter(new FileWriter(log, true));
		    out.append("******* New Logout ******* " + "\r\n");
		    out.append("Time: " + dateFormat.format(cal.getTime()) + " -- User: " + user.getUid() + " -- IP: " + ip + "\r\n");
		    out.close();
		   

		  }catch (Exception e){//Catch exception if any
			  Logger log4j = Logger.getLogger( Logging.class );
			  log4j.error(e);
		  }
}
		
	

}

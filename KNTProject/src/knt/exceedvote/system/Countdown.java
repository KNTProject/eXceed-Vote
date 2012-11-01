package knt.exceedvote.system;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Properties;

import org.joda.time.DateTime;


public class Countdown {

	
	
	public static DateTime getDate(){
		
		InputStream stream = Countdown.class.getResourceAsStream( "exceed.properties" );
		Properties properties = new Properties();
	



		
		 try {
			             properties.load(stream);          
			             int year = Integer.parseInt(properties.getProperty("cdyear"));
			             int month = Integer.parseInt(properties.getProperty("cdmonth"));
			             int day = Integer.parseInt(properties.getProperty("cdday"));
			             int hour = Integer.parseInt(properties.getProperty("cdhour"));
			             int min = Integer.parseInt(properties.getProperty("cdmin"));
			             int sec = Integer.parseInt(properties.getProperty("cdsec"));
			             
			             
			             
			             DateTime countdown = new DateTime(year, month, day, hour, min, sec);

			             return countdown;
			             
			         } catch (IOException e) {
			        	return null;
			         }
		 
	}

}

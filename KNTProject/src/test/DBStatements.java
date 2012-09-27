package test;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import test.Test;



public class DBStatements {
	
	
public static void setText(String text){
	
	  Session session = null;

	  try{
	  // This step will read hibernate.cfg.xml and prepare hibernate for use

	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	session =sessionFactory.openSession();
	Transaction transaction = session.beginTransaction();
	  //Create new instance of Contact and set values in it by reading them from form object
	 System.out.println("Inserting Record NO SYNC");
	  Test test = new Test();
	  test.setText(text);
	  
	  session.save(test);
	  transaction.commit();
	  System.out.println("Done");
	  }catch(Exception e){
	  System.out.println(e.getMessage());
	  }finally{
	  // Actual contact insertion will happen at this step
	  session.flush();
	  session.close();
	  }
}

public static List<Test> getText(){
	

	  Session session = null;
	 
	  
	  try{
		  // This step will read hibernate.cfg.xml and prepare hibernate for use

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session =sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		  //Create new instance of Contact and set values in it by reading them from form object
		 System.out.println("Inserting Record!!!");

		 List<Test> l = session.createCriteria(Test.class).list();

	        if (l.size() == 0) {
	            System.out.println("Empty result");
	            return null;
	        } else {
	        	
	        }
	        
	        return l;

		  }catch(Exception e){
		  System.out.println(e.getMessage());
		  return null;
		  }finally{
		  // Actual contact insertion will happen at this step
		  session.flush();
		  session.close();
		  }

	  
}

public static void main(String[] args){
	
	DBStatements.setText("wasd");
}

}
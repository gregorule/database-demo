package com.qa.jdbcDemo;

import java.sql.Connection;
import java.sql.Statement;

//serve a similar purpose to garage
public class Cafe {
	
	//no need for arrayList<Vehicle>...
	//our database can sort it
	
	Statement stmt = null;//import java.sql
	Connection conn = null;//import java.sql
	
	JDBCDemo db = new JDBCDemo();//making my object
	
	// making a method to add our drink to our database
		public void addDrink(Drink drink) {
			try {
				conn = db.connect();
				stmt = conn.createStatement(); // Making a line of communication to our db
				// What SQL query we are putting into our database
				String queryOld = "INSERT INTO drinks(type, size, dairyFree, cost) VALUES('latte', 'grande', false, 3.32);";
				String query = "INSERT INTO drinks(type, size, dairyFree, cost) VALUES('"+ drink.getType() + "', '"+ drink.getSize() +"', " + drink.isDairyFree()+ ", "+ drink.getCost()+");";
				System.out.println(query);
				stmt.executeUpdate(query); // pass in our SQL query into stmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}

}

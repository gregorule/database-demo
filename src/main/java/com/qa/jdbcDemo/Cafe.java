package com.qa.jdbcDemo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
				String query = "INSERT INTO drinks(type, size, dairyFree, cost) VALUES('"+ drink.getType() + "', '"+ drink.getSize() +"', " + drink.isDairyFree()+ ", "+ drink.getCost()+");";
				System.out.println(query);
				stmt.executeUpdate(query); // pass in our SQL query into stmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
		// Viewing the data in our database, but we want to view it in java 
		// SELECT * FROM drinks -> Give us all of our data.. convert it to Java language
		// Method to get one drink by id
		public Drink getDrinkById(int id) {
			try {
				
				conn = db.connect();
				stmt = conn.createStatement();
				String query = "SELECT * FROM drinks WHERE id = " + id;
				// return an object of type ResultSet - A set of data from the database
				ResultSet results = stmt.executeQuery(query); 
				results.next(); // moves the selection cursor down to the next row
//				System.out.println(results); // We need to convert to a useful object
				Drink drinkResult = modelDrink(results);
				return drinkResult;
				
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		public ArrayList<Drink> getAllDrinks() {
			ArrayList<Drink> resultList = new ArrayList<>();
			try {
				conn = db.connect();
				stmt = conn.createStatement();
				String query = "SELECT * FROM drinks";
				ResultSet results = stmt.executeQuery(query);
				
				//while there is another row below, keep going
				while(results.next()) {
					//return the current row as a Drink object, add it to arrayList
					resultList.add(modelDrink(results));
				}
				return resultList;
				
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}
			
		}
		//will return a Drink from a drink resultSet
		public Drink modelDrink(ResultSet result) {
			try {
				int id = result.getInt(1);//pass in the column number
				String type = result.getString("type");//or pass in the column name
				String size = result.getNString("size");
				boolean bool = result.getBoolean("dairyFree");
				float cost = result.getFloat("cost");
				
				//make a new Drink object
				Drink drink = new Drink(id, type, size, bool, cost);
				return drink;
				
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}
			
		}
		
		

}

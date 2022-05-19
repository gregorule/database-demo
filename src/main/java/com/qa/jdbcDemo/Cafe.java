package com.qa.jdbcDemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
		
		//get by query
		public ArrayList<Drink> getByQuery(String condition, String con) {
			
			ArrayList<Drink> resultList = new ArrayList<>();
			try {
				conn = db.connect();
				stmt = conn.createStatement();
				String query = "SELECT * FROM drinks WHERE " + condition + " = " + con;
				ResultSet results = stmt.executeQuery(query);
				
				//while there is another row below this keep going
				while(results.next()) {
					resultList.add(modelDrink(results));
				}
				return resultList;
				
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}
			
		}
		
		public ArrayList<Drink> updateByQuery(String first, String sec){
			
			ArrayList<Drink> resultList = new ArrayList<>();
			
			try {
				conn = db.connect();
				stmt = conn.createStatement();
				String query = "UPDATE drinks SET type = ?, size = ?, dairyFree = ?, cost = ? WHERE ? = ?";
				PreparedStatement preStmt = conn.prepareStatement(query);
				preStmt.setString(5, first);
				ResultSet results = stmt.executeQuery(query);
				
				while(results.next()) {
					resultList.add(modelDrink(results));
				}
				return resultList;
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		
		//Prepared statements:
		//- We create a statement Object which contains a query, then we run the statement
		//- Pass in ? in place of data in our queries 
		//WHERE size = ? (change ? to be any value)
		
		public int deleteDrinkById(int id) {
			
			try {
				conn = db.connect();
				String query = "DELETE FROM drinks WHERE id = ?";
				PreparedStatement preStmt = conn.prepareStatement(query);
				//set the value of ?, you can have as many ? as you want, they index from 1
				preStmt.setInt(1, id);//the first ? = id
				return preStmt.executeUpdate();
				
			}catch(Exception e) {
				e.printStackTrace();
				return 0;
			}
		}
		
		//drink - what we're updating with
		//id 	- what drink we're updating
		public Drink updateDrinkById(Drink drink, int id) {
			
			try {
				conn = db.connect();
				String query = "UPDATE drinks SET type = ?, size = ?, dairyFree = ?, cost = ? WHERE id = ?";
				PreparedStatement preStmt = conn.prepareStatement(query);
				preStmt.setString(1, drink.getType());//return the type of our drink
				preStmt.setString(2, drink.getSize());
				preStmt.setBoolean(3, drink.isDairyFree());
				preStmt.setFloat(4, drink.getCost());
				preStmt.setInt(5, id);	//if you're getting data it's a query
				preStmt.executeUpdate();//otherwise its an update(create, update, delete)
				return getDrinkById(id);//getting the drink we have just updated
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

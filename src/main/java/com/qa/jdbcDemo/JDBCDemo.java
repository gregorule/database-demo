package com.qa.jdbcDemo;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCDemo {
	
	//Create some variables that our JDBC will use
	//final - this variable can never change
	//final variable name should be all caps
	
	final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://localhost:3306/cafe?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	final String USER = "root";
	final String PASSWORD = "*******";
	
	//change password when pushing to github!!!!!!!
	
	//import - connection from java.sql
	Connection conn;
	
	//method to connect to our database
	public Connection connect() {
		//try { contains the code we are attempting to do
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			System.out.println("Connected");
		}catch(Exception e) { //catch { contains what to do if there is an error in the try{}}
			e.printStackTrace();
			//catch method takes in an exception, these are objects that is an error
			//print the details of this error
		}
		return conn;//return the connection we have created
	}
}

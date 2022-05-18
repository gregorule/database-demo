package com.qa.jdbcDemo;

public class JDBCRunner {

	public static void main(String[] args) {
		
		// Create my JDBC Demo Object
				JDBCDemo jdbc = new JDBCDemo();
				
				// Create our Cafe object
				Cafe newCafe = new Cafe();
				
				Drink latte = new Drink("latte", "grande", true, 1.50f);
				Drink drink2 = new Drink("caramel frappe", "venti", false, 4.15f);
				Drink drink3 = new Drink("Flat White", "small", false, 2.30f);
				Drink drink4 = new Drink("Americano", "grande", true, 3.45f);
				Drink drink5 = new Drink("Earl Grey Tea", "Medium", true, 1.24f);
				
				
				System.out.println(newCafe.getDrinkById(1));
				System.out.println(newCafe.getDrinkById(2));
				System.out.println(newCafe.getAllDrinks());
				
	}

}

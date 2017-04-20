package restClasses;

import java.sql.*;
import java.util.*;

//Class to hold information about a recommendation
public class Recommendation {

	private long restaurant_id;
	private String restaurant_name;
	private double rating;

	public Recommendation(long restaurant_id, double rating){
		this.restaurant_id = restaurant_id;
		this.rating = rating;
		this.restaurant_name = "Restaurant " + restaurant_id;
		/*
		//connect to DB to get restaurant name
		//Parameters to log into database
        String url = "jdbc:mysql://localhost:3306/preferate";
        String username = "root";
        String password = "CrackerWindow654";

        System.out.println("Connecting database...");

        //Try to connect to the database
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");

            Statement stmt = connection.createStatement();
            String query = "SELECT restaurant_name FROM restaurants WHERE restaurant_id =" + this.restaurant_id;
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();

            if(rs.next()){

            	this.restaurant_name = rs.getString("restaurant_name");

            }

            connection.close();

        } catch (SQLException e) {
            System.out.println(e);
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        */

	}


	public long getRestaurant_id(){
		return restaurant_id;
	}

	public String getRestaurant_name(){
		return restaurant_name;
	}

	public double getRating(){
		return rating;
	}


}
package restClasses;

import java.sql.*;
import java.util.*;

//Class to hold information about a recommendation
public class Recommendation {

	private long restaurant_id;
	private String restaurant_name;
	private double rating;
	private String img_link;
	
	private final String[][] restaurantStrs = 
		{
				{"Peck's Arcade", "https://s3-media1.fl.yelpcdn.com/bphoto/zO1vTen-xZ7EqiorFPRkQg/90s.jpg"},	
				{"Lucas Confectionary", "https://s3-media1.fl.yelpcdn.com/bphoto/dX1RvETrT1YovcTyGjx8Uw/90s.jpg"},
				{"Sweet Sues", "https://s3-media3.fl.yelpcdn.com/bphoto/YqD2_oaSMtQmG4FWqUrUKA/90s.jpg"},
				{"Sunhee's Farm and kitchen", "https://s3-media1.fl.yelpcdn.com/bphoto/muKvke9dbCna6TFTQQpvEg/90s.jpg"},
				{"DefFazio's Pizzeria", "https://s3-media1.fl.yelpcdn.com/bphoto/g_2ffvSQPnCOPdu4h-QyKw/90s.jpg"},
				{"Ali Baba", "https://s3-media4.fl.yelpcdn.com/bphoto/h4aTroGN-BensIRaD4cfAA/90s.jpg"},
				{"Superior Mechandise Company", "https://s3-media1.fl.yelpcdn.com/bphoto/4gs9g9KKiqRYim6dVnN12Q/90s.jpg"},
				{"Little Pecks", "https://s3-media3.fl.yelpcdn.com/bphoto/4qKC19JFJYQEdNOmAeOJNA/90s.jpg"},
				{"Troy Kitchen", "https://s3-media4.fl.yelpcdn.com/bphoto/T8csM1HFLmY2QmZszYPPJQ/90s.jpg"},
				{"Muza", "https://s3-media2.fl.yelpcdn.com/bphoto/uzUzy5JmFDQH6tZ5LBu9-A/90s.jpg"}
				
				
				
		};

	public Recommendation(long restaurant_id, double rating){
		this.restaurant_id = restaurant_id;
		this.rating = rating;
		this.restaurant_name = restaurantStrs[(int) restaurant_id%10][0];
		this.img_link = restaurantStrs[(int) restaurant_id%10][1];
		
		//commented out but may be used later to integrate mahout code with database attriutes 
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
	public String getImg_Link(){
		return img_link;
	}

	public double getRating(){
		return rating;
	}


}
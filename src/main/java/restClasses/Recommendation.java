package restClasses;

import java.sql.*;
import java.util.*;

//Class to hold information about a recommendation
public class Recommendation {

	private long restaurant_id;
	private String restaurant_name;
	private double rating;

	public Recommendation(long restaurant_id, String, restaurant_name, double rating){
		this.restaurant_id = restaurant_id;
		this.restaurant_name = restaurant_name;
		this.rating = rating;
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
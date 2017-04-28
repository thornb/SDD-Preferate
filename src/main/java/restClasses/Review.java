package restClasses;

import java.sql.*;
import java.util.*;

//Class to hold information about a review
public class Review {

    //member variables to store attributes from DB
    private final long user_id;
    private final int restaurant_id;
    private final int restaurant_review;

    private final float food_rating;
    private final float menu_rating;
    private final float service_rating;

    private final String comments;
    private final String restaurant_name;

    //constructor that sets all member values
    public Review(long u_id, int r_id, int r_review, float f_rating, float m_rating, float s_rating,String c,String restaurant_name){
        this.user_id = u_id;
        this.restaurant_id = r_id;
        this.restaurant_review = r_review;
        this.food_rating = f_rating;
        this.menu_rating = m_rating;
        this.service_rating = s_rating;
        this.comments=c;
        this.restaurant_name=restaurant_name;
    }

    //getter functions for each attribute needed for Jackson to convert to json for front-end
    public long getUser_id(){
        return user_id;
    }
    public int getRestaurant_id(){
        return restaurant_id;
    }
    public int getRestaurant_review(){
        return restaurant_review;
    }
    public float getFood_rating(){
        return food_rating;
    }
    public float getMenu_rating(){
        return menu_rating;
    }
    public float getService_rating(){
        return service_rating;
    }
    public String getComments(){
        return comments;
    }
    public String getName(){
        return restaurant_name;
    }



}
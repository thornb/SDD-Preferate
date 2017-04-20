package restClasses;

import java.sql.*;
import java.util.*;

//Class to hold information about a review
public class User {

    //member variables to store attributes from DB
    private int user_id;
    private String user_name;
    private String diet_type;
    private String user_allergy;
    private String gluten;
    private String kosher;
    private String lactose;
    private String meats;
    private String eating_environment;

    //constructor that sets all member values
    public User (int user_id, String user_name, String diet_type, String user_allergy, String gluten, String kosher, String lactose, String meats, String eating_environment){
        this.user_id = user_id;
        this.user_name = user_name;
        this.diet_type = diet_type;
        this.user_allergy = user_allergy;
        this.gluten = gluten;
        this.kosher = kosher;
        this.lactose = lactose;
        this.meats = meats;
        this.eating_environment = eating_environment;

    }

    //Connect to Database and insert Account
    public void insertUser(){


        //Parameters to log into database
        String url = "jdbc:mysql://localhost:3306/preferate";
        String username = "root";
        String password = "CrackerWindow654";

        System.out.println("Connecting database...");

        //Try to connect to the database
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");

            //create SQL statment to query to insert the user
            Statement stmt = connection.createStatement();
            String query = "INSERT INTO user (user_id, user_name, diet_type, user_allergy, gluten, kosher, lactose, meats, eating_environment)" + 
                            " VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            
            //set all values for prepared statment
            preparedStmt.setInt(1, this.user_id); 
            preparedStmt.setString(2, this.user_name);
            preparedStmt.setString(3, this.diet_type);
            preparedStmt.setString(4, this.user_allergy);
            preparedStmt.setString(5, this.gluten);
            preparedStmt.setString(6, this.kosher);
            preparedStmt.setString(7, this.lactose);
            preparedStmt.setString(8, this.meats);
            preparedStmt.setString(9, this.eating_environment);

            //execute query to insert user
            preparedStmt.execute();

            //close connection
            connection.close();



            //Error case. Check if database 
        } catch (SQLException e) {
            System.out.println(e);
            throw new IllegalStateException("Cannot connect the database!", e);
        }


    }

    //Function to edit the given user's preferences. Edit the values of the object with the setters functions before calling this
    public void editPreferences(){


        //Parameters to log into database
        String url = "jdbc:mysql://localhost:3306/preferate";
        String username = "root";
        String password = "CrackerWindow654";

        System.out.println("Connecting database...");

        //Try to connect to the database
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");

            //create SQL statement to query to edit preference information
            Statement stmt = connection.createStatement();
            String query = "UPDATE user SET diet_type = ?, user_allergy = ?, gluten = ?, kosher = ?, lactose = ?, meats = ?, eating_environment = ? WHERE user_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            
            //set all values for prepared statement
            preparedStmt.setString(1, this.diet_type);
            preparedStmt.setString(2, this.user_allergy);
            preparedStmt.setString(3, this.gluten);
            preparedStmt.setString(4, this.kosher);
            preparedStmt.setString(5, this.lactose);
            preparedStmt.setString(6, this.meats);
            preparedStmt.setString(7, this.eating_environment);
            preparedStmt.setInt(8, this.user_id);

            //execute query to update user
            preparedStmt.execute();

            //close connection
            connection.close();



            //Error case. Check if database 
        } catch (SQLException e) {
            System.out.println(e);
            throw new IllegalStateException("Cannot connect the database!", e);
        }




    }



    //getter functions for each attribute needed for Jackson to convert to json for front-end
    public int getUser_id(){
        return user_id;
    }
    public void setUser_id(int user_id){
        this.user_id = user_id;
    }


    public String getUser_name(){
        return user_name;
    }
    public void setUser_name(String user_name){
        this.user_name = user_name;
    }


    public String getDiet_type(){
        return diet_type;
    }
    public void setDiet_type(String diet_type){
        this.diet_type = diet_type;
    }


    public String getUser_allergy(){
        return user_allergy;
    }
    public String getGluten(){
        return gluten;
    }
    public String getKosher(){
        return kosher;
    }
    public String getLactose(){
        return lactose;
    }
    public String getMeats(){
        return meats;
    }
    public String getEating_enviroment(){
        return eating_environment;
    }


}
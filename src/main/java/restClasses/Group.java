package restClasses;

import java.sql.*;
import java.util.*;

//Class to hold group information and to insert groups and add members
public class Group {

    // int owner; 
    // private final int id;
    // String name; 
    // String members;
    // // ArrayList<String> preferences; 
    // // ArrayList<Integer> history; // restaurant ids


    // //We need create group 
    // // join group
    // // return users in group
    // // remove from group
    // // create group object from database (contructor with just groupID)

    // // public Group(long id, String content) {
    // public Group(int owner, String g_name, ArrayList<Integer> members) {
        

    //     //this.id = group_id;
    //     this.owner = owner;
    //     this.name = g_name; 
    //     this.members = new ArrayList<Integer>(); 
    //     this.preferences = new ArrayList<String>(); 
    //     this.history = new ArrayList<Integer>();

    //     members.add(owner); 

    //     //add this group to the DB
    //     String url = "jdbc:mysql://localhost:3306/preferate";
    //     String username = Globals.dbuser;
    //     //String password = "CrackerWindow654";
    //     String password = Globals.pass;

    //     System.out.println("Connecting database...");


    //     try (Connection connection = DriverManager.getConnection(url, username, password)) {
    //         System.out.println("Database connected!");

    //         // to add all members to the database
            

    //         Statement stmt = connection.createStatement();
    //         String query = "INSERT INTO `groups` (`group_name`, `group_member, 'owner') VALUES (?, ?, ?);";
            
    //         PreparedStatement preparedStmt = connection.prepareStatement(query);
    //         preparedStmt.setString(1, this.name);
    //         preparedStmt.setInt(2, this.owner);
    //         preparedStmt.setInt(3, this.owner); 
    //         preparedStmt.execute();
            
    //         connection.close();


    //     } catch (SQLException e) {
    //         System.out.println(e);
    //         throw new IllegalStateException("Cannot connect to the database!", e);
    //     }



    // }

    // //  should be done after the group is created and all members are in 
    // public void addMembers() {

    //     String url = "jdbc:mysql://localhost:3306/preferate";
    //     String username = Globals.dbuser;
    //     //String password = "CrackerWindow654";
    //     String password = Globals.pass;

    //     System.out.println("Connecting database...");


    //     try (Connection connection = DriverManager.getConnection(url, username, password)) {
    //         System.out.println("Database connected!");

    //         // to add all members to the database
    //         for ( int i = 0; i < members.size(); ++i ) {

    //             Statement stmt = connection.createStatement();

    //             String query = "INSERT INTO `groups` (`group_name`, `group_member, 'restaurant_name') VALUES (?, ?, ?, 'test');";
                
    //             PreparedStatement preparedStmt = connection.prepareStatement(query);
    //             preparedStmt.setInt(1, this.id);
    //             preparedStmt.setString(2, this.name);
    //             preparedStmt.setInt(3, members.get(i) ); 


    //             preparedStmt.execute();
    //         }
            

    //         connection.close();


    //     } catch (SQLException e) {
    //         System.out.println(e);
    //         throw new IllegalStateException("Cannot connect to the database!", e);
    //     }
    // }



    // public int getId() {
    //     return id;
    // }

    // public String getName() {
    //     return name; 
    // }

    // public ArrayList<Integer> getMembers() {
    //     return members; 
    // }

    // public ArrayList<String> getPreferences() {
    //     return preferences; 
    // }

    // public ArrayList<Integer> getRestaurantHistory() {
    //     return history; 
    // }

    // public 
    // boolean addFriendToGroup(int userID, int friendID, int groupID) {
    //     members.add(friendID); 

    //     return true; 
    // }

    // boolean removeFriendFromGroup(int userID, int friendID, int groupID) {
    //     members.remove(friendID); 

    //     return true; 
    // } 

    // void changeName( int groupID, String name ) {
    //     this.name = name; 
    // }

    // void addPreferences( String pref ) {
    //     preferences.add(pref); 
    // }

    // void addRestaurant( int restID ) {
    //     history.add( restID ); 
    // }

}
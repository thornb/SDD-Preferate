package restClasses;

import java.sql.*;
import java.util.*;

//Class to hold group information and to insert groups and add members
public class Group {

    Long owner; 
    int id;
    String name; 
    ArrayList<Long> members;



    //We need create group 
    // join group
    // return users in group
    // remove from group
    // create group object from database (contructor with just groupID)

    // public Group(long id, String content) {
    public Group(Long owner, String g_name, ArrayList<Long> members) {
        

        //this.id = group_id;
        this.owner = owner;
        this.name = g_name; 
        this.members = members; 



        //add this group to the DB
        String url = "jdbc:mysql://localhost:3306/preferate";
        String username = Globals.dbuser;
        String password = Globals.pass;

        System.out.println("Connecting database...");


        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");

            //Since we are creating a new group, we need to get the max group ID, and add 1 to it.
            Statement maxStmt = connection.createStatement();
            String maxQuery = "SELECT MAX(`group_id`) FROM `groups`";
            PreparedStatement maxPerparedStmt = connection.prepareStatement(maxQuery);

            ResultSet maxRS = maxPerparedStmt.executeQuery();
            int maxGroupID = 1;
            while(maxRS.next()){

                try{
                    maxGroupID = maxRS.getInt("MAX(`group_id`)");    
                }
                catch(Exception e){
                    maxGroupID = 1;
                    System.out.println(e);
                }

            }


            //insert for each member
            for(int i = 0; i < members.size(); i++){

                Statement stmt = connection.createStatement();
                String query = "INSERT INTO `groups` ( `group_id` ,`group_name`, `group_member`, `owner`) VALUES (?, ?, ?, ?);";
                
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setInt(1, maxGroupID + 1);
                preparedStmt.setString(2, this.name);
                preparedStmt.setLong(3, members.get(i));
                preparedStmt.setLong(4, this.owner); 
                preparedStmt.execute();

            }

            
            
            connection.close();


        } catch (SQLException e) {
            System.out.println(e);
            throw new IllegalStateException("Cannot connect to the database!", e);
        }



    }

    //  should be done after the group is created and all members are in 
    // public void addMembers(ArrayList<In>) {

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



    public int getId() {
        return id;
    }

    public String getName() {
        return name; 
    }

    public ArrayList<Long> getMembers() {
        return members; 
    }

    // public ArrayList<String> getPreferences() {
    //     return preferences; 
    // }

    // public ArrayList<Integer> getRestaurantHistory() {
    //     return history; 
    // }

    public 
    boolean addFriendToGroup(Long userID, Long friendID, int groupID) {
        members.add(friendID); 

        return true; 
    }

    boolean removeFriendFromGroup(Long userID, Long friendID, int groupID) {
        members.remove(friendID); 

        return true; 
    } 

    void changeName( int groupID, String name ) {
        this.name = name; 
    }

    // void addPreferences( String pref ) {
    //     preferences.add(pref); 
    // }

    // void addRestaurant( int restID ) {
    //     history.add( restID ); 
    // }

}
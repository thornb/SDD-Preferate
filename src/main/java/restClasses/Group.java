package restClasses;

import java.sql.*;
import java.util.Properties;

public class Group {

    int owner; 
    private final int id;
    String name; 
    ArrayList<int> members;
    ArrayList<String> preferences; 
    ArrayList<int> history; // restaurant ids


    // public Group(long id, String content) {
    public Group(int owner, int group_id, String g_name) {
        
        this.id = group_id;
        this.owner = owner;
        this.name = g_name; 
        this.members = new ArrayList<int>(); 
        this.preferences = new ArrayList<String>(); 
        this.history = new ArrayList<int>();

        members.add(owner); 
    }

    //  should be done after the group is created and all members are in 
    public void insertGroup() {

        String url = "jdbc:mysql://localhost:3306/preferate";
        String username = "root";
        String password = "CrackerWindow654";

        System.out.println("Connecting database...");


        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");

            // to add all members to the database
            for ( int i = 0; i < members.size(); ++i ) {

                Statement stmt = connection.createStatement();

                String query = "INSERT INTO `groups` (`group_id`, `group_name`, `group_member, 'restaurant_name') VALUES (?, ?, ?, 'test');";
                
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setInt(1, this.id);
                preparedStmt.setString(2, this.name);
                preparedStmt.setInt(3, members.get(i) ); 


                preparedStmt.execute();
            }
            

            connection.close();


        } catch (SQLException e) {
            System.out.println(e);
            throw new IllegalStateException("Cannot connect to the database!", e);
        }
    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name; 
    }

    public ArrayList<int> getMembers() {
        return members; 
    }

    public ArrayList<String> getPreferences() {
        return preferences; 
    }

    public ArrayList<int> getRestaurantHistory() {
        return history; 
    }

    public 
    boolean addFriendToGroup(int userID, int friendID, int groupID) {
        members.add(friendID); 

        return true; 
    }

    boolean removeFriendFromGroup(int userID, int friendID, int groupID) {
        members.remove(friendID); 

        return true; 
    } 

    void changeName( int groupID, String name ) {
        this.name = name; 
    }

    void addPreferences( String pref ) {
        preferences.add(pref); 
    }

    void addRestaurant( int restID ) {
        history.add( restID ); 
    }

}
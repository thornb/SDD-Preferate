package restClasses;

import java.sql.*;
import java.util.*;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;



//Class to query the database for Groups and to hold a list of these groups 
public class GroupList {

    //member variable to store groups
    private ArrayList<Group> group_list;

    //Constructor that creates a group list of all the groups the user is in
    public GroupList(long user_id){

        this.group_list = new ArrayList<Group>();

        //Parameters to log into database
        String url = "jdbc:mysql://localhost:3306/preferate";
        String username = Globals.dbuser;
        String password = Globals.pass;

        System.out.println("Connecting database...");

        //Try to connect to the database
        try (Connection connection = DriverManager.getConnection(url, username,password)) {
            System.out.println("Database connected!");

            //create SQL statment to query for reviews
            Statement stmt = connection.createStatement();
            String query = "SELECT `group_id` FROM `groups` WHERE `group_member` = " + user_id;
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();

            //save a list of the group IDs
            ArrayList<Integer> groupIDs = new ArrayList<Integer>();
            while(rs.next()){
                groupIDs.add(rs.getInt("group_id"));
            }

            //for each groupID we must create a group object containing the members
            for(int i = 0; i < groupIDs.size(); i++){

                //get a list of all the members in the group
                Statement memStmt = connection.createStatement();
                String memQuery = "SELECT * FROM `groups` WHERE `group_id` = " + groupIDs.get(i);
                PreparedStatement mempreparedStmt = connection.prepareStatement(memQuery);
                ResultSet memRS = mempreparedStmt.executeQuery();

                ArrayList<Long> members = new ArrayList<Long>();
                long owner = 0;
                String name = "";
                while(memRS.next()){
                    members.add( memRS.getLong("group_member") );
                    owner = memRS.getLong("owner");
                    name  = memRS.getString("group_name"); 
                }

                //create group object and add to the group_list
                Group g = new Group(owner, groupIDs.get(i), name, members );

                addGroup(g);



            }


            //close connection
            connection.close();



            //Error case. Check if database 
        } catch (SQLException e) {
            System.out.println(e);
            throw new IllegalStateException("Cannot connect the database!", e);
        }


    }


    //getter function to return the list. Needed so that Jackson can convert this to json when sending to front-end
    public ArrayList<Group> getgroup_list(){
        return group_list;
    }

    //adds a review to the review_list privit variable
    public void addGroup(Group r){
        this.group_list.add(r);
    }





}
package restClasses;

import java.sql.*;
import java.util.*;


// import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
// import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

//Class to query the database for reviews and to hold a list of these reviews 
public class ReviewList {

    //member variable to store reviews
    private final ArrayList<Review> review_list;

    //Constructer that logs into database, gets all restaurant reviews, creates a Review object for each, and adds them to the review_list variable    
    public ReviewList(){


        //Parameters to log into database
        String url = "jdbc:mysql://localhost:3306/preferate";
        String username = "root";
        String password = "CrackerWindow654";

        System.out.println("Connecting database...");

        //Try to connect to the database
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");

            //create SQL statment to query for reviews
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM `restaurant_reviews`";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();

            //prepare review_list to add reviews
            review_list = new ArrayList<Review>();

            //Loop over all reviews returned from query 
            while (rs.next()) {

                //create a Review object for each
                Review r = new Review(rs.getInt("user_id"), rs.getInt("restaurant_id"), rs.getInt("restaurant_review"), 
                    rs.getFloat("food_rating"), rs.getFloat("menu_rating"), rs.getFloat("service_rating") );

                //Add to the list
                this.review_list.add(r);             

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
    public ArrayList<Review> getreview_list(){
        return review_list;
    }


    public static JDBCDataModel getReviewsRecommender(String param){

         MysqlDataSource dataSource = new MysqlDataSource();

         dataSource.setServerName("jdbc:mysql://localhost:3306/");
         dataSource.setUser("root");
         dataSource.setPassword("CrackerWindow654");
         dataSource.setDatabaseName("preferate");

         JDBCDataModel dataModel;
         //generalized, format for the "param" parameter should be a string of all lowercase letters with no spaces, it should be a parameter in the reviews database
         if(type != NULL){
            dataModel = new MySQLJDBCDataModel(
            dataSource, "restaurant_reviews", "user_id",
            "restaurant_id", type + "_rating");
         } else {
            dataModel = new MySQLJDBCDataModel(
            dataSource, "restaurant_reviews", "user_id",
            "restaurant_id", "food_rating");
         }
         return dataModel;
    }

}

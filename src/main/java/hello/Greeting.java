package hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Statement;
import java.sql.ResultSet;

public class Greeting {

    private final long id;
    //private final String content;

    // public Greeting(long id, String content) {
    public Greeting(String q1) {
        // this.id = id;
        // this.content = content;

        String url = "jdbc:mysql://localhost:3306/preferate";
        String username = "root";
        String password = "CrackerWindow654";

        System.out.println("Connecting database...");

        this.id = 3;

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");

            Statement stmt = connection.createStatement();


            String query = "INSERT INTO `user` (`user_id`, `group_name`, `user_name`, `diet_type`, `user_allergy`, `gluten`, `kosher`, `lactose`, `meats`, `eating_environment`) VALUES ('" + this.id + "', '"+ q1 +"', 'test', 'test', 'test', 'tes', 'tes', 'tes', 'test', 'test');";

            ResultSet rs = stmt.executeQuery(query);


        } catch (SQLException e) {
            System.out.println(e);
            throw new IllegalStateException("Cannot connect the database!", e);
        }


    }

    public long getId() {
        return id;
    }

    // public String getContent() {
    //     return content;
    // }
}
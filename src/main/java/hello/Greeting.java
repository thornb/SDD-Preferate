package hello;

import java.sql.*;
import java.util.Properties;

public class Greeting {

    private final int id;
    //private final String content;

    // public Greeting(long id, String content) {
    public Greeting(String q1) {
        // this.id = id;
        // this.content = content;

        String url = "jdbc:mysql://localhost:3306/preferate";
        String username = "root";
        String password = "ColonieBrownie779";

        System.out.println("Connecting database...");

        this.id = 3;

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");

            Statement stmt = connection.createStatement();

            System.out.println("q1:" + q1);

            String query = "INSERT INTO `user` (`user_id`, `group_name`, `user_name`, `diet_type`, `user_allergy`, `gluten`, `kosher`, `lactose`, `meats`, `eating_environment`) VALUES (?, ?, 'test', 'test', 'test', 'tes', 'tes', 'tes', 'test', 'test');";
            
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, this.id);
            preparedStmt.setString(2, q1);

            preparedStmt.execute();

            connection.close();




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
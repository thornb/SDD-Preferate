package hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Greeting {

    private final long id;
    //private final String content;

    // public Greeting(long id, String content) {
    public Greeting() {
        // this.id = id;
        // this.content = content;

        String url = "jdbc:mysql://localhost:3306/preferate";
        String username = "root";
        String password = "CrackerWindow654";

        System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");

            this.id = 1;


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
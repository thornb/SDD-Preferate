package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

  //       String url = "jdbc:mysql://localhost:3306/preferate";
		// String username = "root";
		// String password = "CrackerWindow654";

		// System.out.println("Connecting database...");

		// try (Connection connection = DriverManager.getConnection(url, username, password)) {
		//     System.out.println("Database connected!");
		// } catch (SQLException e) {
		// 	System.out.println(e);
		//     throw new IllegalStateException("Cannot connect the database!", e);
		// }


    }


    
    
}
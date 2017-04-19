package restClasses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//This Class is what starts the whole application with the spring-boot automation tools
@SpringBootApplication
//@EnableAutoConfiguration
public class Application {

	//Starts the spring Application
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        Recommender rec = new Recommender();
        String params[] = {"food","menu","service"};
        //get the predicted ratings for some user's ID
        int UserID = 1;
        try{
        	rec.runRecommender(params, UserID);
        }
        catch(Exception e){
        	System.out.println("runRecommender encountered an error:");
        	System.out.println(e);
        }
    }

}
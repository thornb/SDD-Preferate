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
        GroupRecommender multiRec = new GroupRecommender();
        String params[] = {"food","menu","service"};
        //get the predicted ratings for some user's ID
        long UserID = 1;
        long UserIDs[] = {1,2,3};
        try{
        	rec.runRecommender(params, UserID);
        	multiRec.runRecommender(params, UserIDs);
        }
        catch(Exception e){
        	System.out.println("runRecommender encountered an error:");
        	System.out.println(e);
        }
    }

}
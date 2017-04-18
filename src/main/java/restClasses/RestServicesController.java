package restClasses;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.ResponseBody;


import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


//This is the controller that runs the REST services on the site. It maps URLs to our other classes, that will ultimatly be converted to JSON objects by jackson and sent to the front-end
@RestController
public class RestServicesController {

	//Example REST call and database call
    @RequestMapping("/greeting")
    public Greeting greeting( @RequestParam( value="q1", defaultValue="option5") String q1 ) {
        return new Greeting(q1);
    }

    //when user queries the url "/reviews", it returns a list of review objects in json 
    @RequestMapping("/reviews")
    public ReviewList ReviewList(){
    	return new ReviewList();
    }


    //When user queries the url "/addUser", it takes in the parameters from the url and 
    @RequestMapping("/addUser")
    //@ResponseStatus(value = HttpStatus.OK)
    public void addDBUser( @RequestParam( value="user_id" ) int user_id, 
                         @RequestParam( value="user_name" ) String user_name,
                         @RequestParam( value="diet_type" ) String diet_type,
                         @RequestParam( value="user_allergy" ) String user_allergy,
                         @RequestParam( value="gluten" ) String gluten,
                         @RequestParam( value="kosher" ) String kosher,
                         @RequestParam( value="lactose" ) String lactose,
                         @RequestParam( value="meats" ) String meats,
                         @RequestParam( value="eating_enviroment" ) String eating_enviroment ){

        //create the user class object
        User u = new User(user_id, user_name, diet_type, user_allergy, gluten, kosher, lactose, meats, eating_enviroment); 

        //insert this user into the database
        u.insertUser();        
    }

    //When user queries the url "/addUser", it takes in the parameters from the url and 
    @RequestMapping("/editPref")
    //@ResponseStatus(value = HttpStatus.OK)
    public void addUser( @RequestParam( value="user_id" ) int user_id, 
                         @RequestParam( value="user_name" ) String user_name,
                         @RequestParam( value="diet_type" ) String diet_type,
                         @RequestParam( value="user_allergy" ) String user_allergy,
                         @RequestParam( value="gluten" ) String gluten,
                         @RequestParam( value="kosher" ) String kosher,
                         @RequestParam( value="lactose" ) String lactose,
                         @RequestParam( value="meats" ) String meats,
                         @RequestParam( value="eating_enviroment" ) String eating_enviroment ){

        //create the user class object
        User u = new User(user_id, user_name, diet_type, user_allergy, gluten, kosher, lactose, meats, eating_enviroment); 

        //edit this user's preferences into the database
        u.editPreferences();        
    }



}


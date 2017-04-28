package restClasses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import org.springframework.web.bind.annotation.CrossOrigin;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.sql.*;
import java.util.*;
import java.lang.Long;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


//This is the controller that runs the REST services on the site. It maps URLs to our other classes, that will ultimatly be converted to JSON objects by jackson and sent to the front-end
@RestController
public class RestServicesController {

	//Example REST call and database call
	@RequestMapping("/greeting")
	public Greeting greeting( @RequestParam( value="q1", defaultValue="option5") String q1 ) {
		return new Greeting(q1);
	}

	//when user queries the url "/reviews", it returns a list of review objects in json 
	@CrossOrigin
	@RequestMapping("/reviews")
	public ReviewList ReviewList(){
		return new ReviewList();
	}


    //when user queries the url "/userReviews", it returns a list of review objects made by that user in json 
    @CrossOrigin
    @RequestMapping("/userReviews")
    public ReviewList ReviewList(@RequestParam( value="user_id" ) String user_id ){
        long userID = Long.parseLong(user_id);
        //This constructor returns the reviews made by a particular user    
        return new ReviewList(userID);
    }   


    //when user queries the url "/createGroup", it returns a list of review objects made by that user in json 
    @CrossOrigin
    @RequestMapping("/createGroup")
    public Group createGroup(@RequestParam(value="owner_id") String owner_id, @RequestParam(value="group_name") String group_name, @RequestParam(value="members") String members_str ){
        
        //parse the members string
        String [] members_str_list = members_str.split("-");
        ArrayList<Long> members = new ArrayList<Long>();

        for(int i = 0; i < members_str_list.length; i++){
            members.add(Long.parseLong(members_str_list[i]));
        }

        return new Group(Long.parseLong(owner_id), group_name, members);

    }    


    //Adds a review to the database
	@CrossOrigin
	@RequestMapping(value = "/addreview")
    public @ResponseBody Review generateReport(@RequestParam int restaurant_review,@RequestParam String user_id, @RequestParam float food_rating, 
        @RequestParam float menu_rating, @RequestParam float service_rating, @RequestParam int restaurant_id, @RequestParam String comments,
        @RequestParam String restaurant_name){
        String url = "jdbc:mysql://localhost:3306/preferate";
        String username = Globals.dbuser;
        String password = Globals.pass;

        System.out.println("Connecting database...");

        String rr=Integer.toString(restaurant_review);
        //String ui=Integer.toString(user_id);
        String fr=Float.toString(food_rating);
        String mr=Float.toString(menu_rating);
        String sr=Float.toString(service_rating);
        String ri=Integer.toString(restaurant_id);
        String com=comments;
        String name=restaurant_name;
        long temp_id=Long.parseLong(user_id);
        //Try to connect to the database
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!"); 
            Statement stmt = connection.createStatement();
            //(restaurant_review,user_id,food_rating,menu_rating,service_rating,restaurant_id,comments)
            stmt.executeUpdate("INSERT INTO restaurant_reviews (restaurant_review,user_id,food_rating,menu_rating,service_rating,restaurant_id,comments,restaurant_name) "+"VALUES ("+rr+","+temp_id+","+fr+","+mr+","+sr+","+ri+",'"+com+"','"+name+"')");

            //close connection
            connection.close();



            //Error case. Check if database 
        } catch (SQLException e) {
            System.out.println(e);
            throw new IllegalStateException("Cannot connect the database!", e);
        }

        Review r=new Review(temp_id,restaurant_id,restaurant_review,food_rating,menu_rating,service_rating,comments,restaurant_name);
        //ReviewList temp=new ReviewList();
        //ReviewList.addReview(r);

        // String pNameParameter = pName;
        // String lNameParameter = lName;
    // ...
    // Here you can use the request and response objects like:
    // response.setContentType("application/pdf");
    // response.getOutputStream().write(...);
    return r;

    }

    //edits a review in the database
	@CrossOrigin
	@RequestMapping(value = "/changereview")
    public @ResponseBody Review generateUpdate(@RequestParam int restaurant_review,@RequestParam String user_id, @RequestParam float food_rating, 
        @RequestParam float menu_rating, @RequestParam float service_rating, @RequestParam int restaurant_id, @RequestParam String comments,
        @RequestParam String restaurant_name){
        String url = "jdbc:mysql://localhost:3306/preferate";
        String username = Globals.dbuser;
        String password = Globals.pass;

        System.out.println("Connecting database...");

        String rr=Integer.toString(restaurant_review);
        //String ui=Integer.toString(user_id);
        String fr=Float.toString(food_rating);
        String mr=Float.toString(menu_rating);
        String sr=Float.toString(service_rating);
        String ri=Integer.toString(restaurant_id);
        String com=comments;
        String name=restaurant_name;
        long foo=Long.parseLong(user_id);
        //Try to connect to the database
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!"); 
            Statement stmt = connection.createStatement();
            //(restaurant_review,user_id,food_rating,menu_rating,service_rating,restaurant_id,comments)
            //stmt.executeUpdate("INSERT INTO restaurant_reviews (restaurant_review,user_id,food_rating,menu_rating,service_rating,restaurant_id,comments) "+"VALUES ("+rr+","+ui+","+fr+","+mr+","+sr+","+ri+",'"+com+"');");
            stmt.executeUpdate("UPDATE restaurant_reviews SET food_rating="+fr+", menu_rating="+mr+", service_rating="+sr+", comments='"+comments+"', restaurant_name='"+name+"' WHERE restaurant_review="+rr);
            //close connection
            connection.close();



            //Error case. Check if database 
        } catch (SQLException e) {
            System.out.println(e);
            throw new IllegalStateException("Cannot connect the database!", e);
        }

        Review r=new Review(foo,restaurant_id,restaurant_review,food_rating,menu_rating,service_rating,comments,restaurant_name);
        return r;
        // Review r=new Review(user_id,restaurant_id,restaurant_review,food_rating,menu_rating,service_rating,comments);
        // ReviewList temp=new ReviewList();
        // ReviewList.addReview(r);

        // String pNameParameter = pName;
        // String lNameParameter = lName;
    // ...
    // Here you can use the request and response objects like:
    // response.setContentType("application/pdf");
    // response.getOutputStream().write(...);

    }




	//when user queries the url "/suggestions_page?user_id=<int>", it returns a sorted list of Recommendation objects of restaurant class
	String params[] = {"food","menu","service"};
	@CrossOrigin
	@RequestMapping("/suggestions_page")
	public ArrayList<Recommendation> recommender(@RequestParam( value="user_id" ) String s_user_id){
		long user_id = Long.parseLong(s_user_id);
		try {
			Recommender rec = new Recommender(params, user_id);
			return rec.getRecs();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	//when user queries the url "/suggestions_pageGroup", it returns a sorted list of Reccommendation objects
	//url should look like http://localhost:8080/suggestions_pageGroup?members=[1-2-3-...]
	@CrossOrigin
	@RequestMapping("/suggestions_pageGroup")
	public ArrayList<Recommendation> recommenderGroup(@RequestParam( value="members" ) String memString){
		//parse string into array
		String input = memString.substring(1, memString.length()-1);
		ArrayList<Long> members = new ArrayList<Long>();
		for(String mem : Arrays.asList(input.split("-"))){
			members.add(Long.parseLong(mem));
		}
		long memArr[] = new long[members.size()];
		for(int i = 0; i < memArr.length; ++i){
			memArr[i] = members.get(i);
		}
		try {
			Recommender rec = new GroupRecommender(params, memArr);
			return rec.getRecs();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

    }
    
    //When user queries the url "/addUser", it takes in the parameters from the url and inserts or edits preference info
    @CrossOrigin
    @RequestMapping("/addOrEditUser")
    public void addDBUser( @RequestParam( value="user_id" ) String s_user_id, 
                         @RequestParam( value="user_name" ) String user_name,
                         @RequestParam( value="diet_type" ) String diet_type,
                         @RequestParam( value="user_allergy" ) String user_allergy,
                         @RequestParam( value="gluten" ) String gluten,
                         @RequestParam( value="kosher" ) String kosher,
                         @RequestParam( value="lactose" ) String lactose,
                         @RequestParam( value="meats" ) String meats,
                         @RequestParam( value="eating_environment" ) String eating_environment ){

        //create the user class object
        Long user_id = Long.parseLong(s_user_id);

        User u = new User(user_id, user_name, diet_type, user_allergy, gluten, kosher, lactose, meats, eating_environment); 

        //insert this user into the database
        u.insertOrEditUser();        
    }

    //returns user object attributes with given user id. COnverted to json when returned from front-end automatically with jackson
    @CrossOrigin
    @RequestMapping("/getUser")
    public User getDBUser( @RequestParam( value="user_id" ) String s_user_id){

        //create the user class object
        Long user_id = Long.parseLong(s_user_id);

        User u = new User(user_id); 

        //insert this user into the database
        return u;     
    }



}


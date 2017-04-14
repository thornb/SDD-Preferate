package hello;

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
public class GreetingController {

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


}


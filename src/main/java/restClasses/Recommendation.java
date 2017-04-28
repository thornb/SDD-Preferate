package restClasses;

import java.sql.*;
import java.util.*;

//Class to hold information about a recommendation
public class Recommendation {

	private long restaurant_id;
	private String restaurant_name;
	private double rating;
	private String img_link;
	
	private final String[][] restaurantStrs = 
		{
				{"Peck's Arcade", "https://s3-media1.fl.yelpcdn.com/bphoto/zO1vTen-xZ7EqiorFPRkQg/90s.jpg"},	
				{"Lucas Confectionary", "https://s3-media1.fl.yelpcdn.com/bphoto/dX1RvETrT1YovcTyGjx8Uw/90s.jpg"},
				{"Sweet Sues", "https://s3-media3.fl.yelpcdn.com/bphoto/YqD2_oaSMtQmG4FWqUrUKA/90s.jpg"},
				{"Sunhee's Farm and kitchen", "https://s3-media1.fl.yelpcdn.com/bphoto/muKvke9dbCna6TFTQQpvEg/90s.jpg"},
				{"DefFazio's Pizzeria", "https://s3-media1.fl.yelpcdn.com/bphoto/g_2ffvSQPnCOPdu4h-QyKw/90s.jpg"},
				{"Ali Baba", "https://s3-media4.fl.yelpcdn.com/bphoto/h4aTroGN-BensIRaD4cfAA/90s.jpg"},
				{"Superior Mechandise Company", "https://s3-media1.fl.yelpcdn.com/bphoto/4gs9g9KKiqRYim6dVnN12Q/90s.jpg"},
				{"Little Pecks", "https://s3-media3.fl.yelpcdn.com/bphoto/4qKC19JFJYQEdNOmAeOJNA/90s.jpg"},
				{"Troy Kitchen", "https://s3-media4.fl.yelpcdn.com/bphoto/T8csM1HFLmY2QmZszYPPJQ/90s.jpg"},
				{"Muza", "https://s3-media2.fl.yelpcdn.com/bphoto/uzUzy5JmFDQH6tZ5LBu9-A/90s.jpg"},	//10
				{"Psychedelicatessen", "https://s3-media3.fl.yelpcdn.com/bphoto/viI7zBkY85Yc4AzYJMHVNg/90s.jpg"},
				{"Thunder Mountain Curry", "https://s3-media1.fl.yelpcdn.com/bphoto/GkG4WPwnPrYcj-WsK5BNkQ/90s.jpg"},
				{"Carluccio's Italian Delicatessen", "https://s3-media4.fl.yelpcdn.com/bphoto/KSobD9pFNJijKHUZpNkDRQ/90s.jpg"},
				{"The Ruck", "https://s3-media3.fl.yelpcdn.com/bphoto/W3iTVZ_Creq3yMBXtCwEPw/90s.jpg"},
				{"The Flying Chicken", "https://s3-media3.fl.yelpcdn.com/bphoto/Eu_U83sIbHZLKzlWvR5XcA/90s.jpg"},
				{"Ale House", "https://s3-media2.fl.yelpcdn.com/bphoto/4AYOliaZa2spzfzopqVlEA/90s.jpg"},
				{"Muddaddy Flats", "https://s3-media3.fl.yelpcdn.com/bphoto/-dux8BgGRdsChhfJfbKltA/90s.jpg"},
				{"Lucky Corner", "https://s3-media1.fl.yelpcdn.com/bphoto/VHYjj0GvvSpP-r-q-B5x5Q/90s.jpg"},
				{"Nibble", "https://s3-media3.fl.yelpcdn.com/bphoto/8yAjMtLljv5T123H3ZsNYg/90s.jpg"},
				{"FinnCar's Pub", "https://s3-media3.fl.yelpcdn.com/bphoto/bm8lwlM8ioUjBIaMQFMDpg/90s.jpg"},	//20
				{"Slidin' Dirty", "https://s3-media4.fl.yelpcdn.com/bphoto/m5yjSkjxwc4vVnK0VzGNGw/90s.jpg"},
				{"Illium Cafe", "https://s3-media1.fl.yelpcdn.com/bphoto/dmVjssQ6S24edpEahaQB-A/90s.jpg"},
				{"The Shop", "https://s3-media1.fl.yelpcdn.com/bphoto/cjVqWzSk6R5-4rLV65xnKw/90s.jpg"},
				{"Famous Lunch", "https://s3-media4.fl.yelpcdn.com/bphoto/Ft0UW-_3WBlZBhAi_fkzng/90s.jpg"},
				{"Bespoki Bowl", "https://s3-media2.fl.yelpcdn.com/bphoto/6ITK6H8buAOpnhvMWUUDsQ/90s.jpg"},
				{"O'Brien's Public House", "https://s3-media2.fl.yelpcdn.com/bphoto/Xns_XUqT7fXGn4eBLHSTDw/90s.jpg"},
				{"The Greek Househttps://s3-media2.fl.yelpcdn.com/bphoto/NADS9LVgW-7BkNFZ-v2hAQ/90s.jpg", ""},
				{"Beirut Restaurant", "https://s3-media2.fl.yelpcdn.com/bphoto/Jx1nFGJdxLP6p4uTVf2avQ/90s.jpg"},
				{"Amante Pizza", "https://s3-media1.fl.yelpcdn.com/bphoto/j5tlwoJcMn5zjwvGxMBTbQ/90s.jpg"},
				{"Spill'n the Beans Coffeehouse & Bistro", "https://s3-media4.fl.yelpcdn.com/bphoto/77JyLmFDzsOn_QXNSjClNA/90s.jpg"},	//30
				{"Notty Pine Tavern", "https://s3-media4.fl.yelpcdn.com/bphoto/HabG12KZs_tEVOnr1u2fNA/90s.jpg"},
				{"Donna's Italian Restaurant", "https://s3-media3.fl.yelpcdn.com/bphoto/vJ3WEhULnqma5gPkEzmfUg/90s.jpg"},
				{"The Hill at Muza", "https://s3-media3.fl.yelpcdn.com/bphoto/PvQNPnRC97XjAMk8KUDTLg/90s.jpg"},
				{"Lo Porto's", "https://s3-media4.fl.yelpcdn.com/bphoto/lh7jkze8EUlaZzg5xpE-0g/90s.jpg"},
				{"Koni's Broadway Kafe", "https://s3-media3.fl.yelpcdn.com/bphoto/RUG4xFlXKMrMlSzrvAGTmQ/90s.jpg"},
				{"First Choice Caribbean", "https://s3-media3.fl.yelpcdn.com/bphoto/cpafusCZuq-2zCB_FgnkWg/90s.jpg"},
				{"Carmen's Cafe", "https://s3-media4.fl.yelpcdn.com/bphoto/EODJV0NImAicmRKGGq5l_Q/90s.jpg"},
				{"Unagi Sushi", "https://s3-media3.fl.yelpcdn.com/bphoto/J07wXQ1heFhxAru0Gzd6Wg/90s.jpg"},
				{"Manory's Restaurant", "https://s3-media2.fl.yelpcdn.com/bphoto/ZeDuLTiV5pT-v5XUpo5uPw/90s.jpg"},
				{"MochaBlend Cafe", "https://s3-media2.fl.yelpcdn.com/bphoto/eSPrevurHRMyncxGPvBhXQ/90s.jpg"},	//40
				{"I Love Pizza of Troy", "https://s3-media1.fl.yelpcdn.com/bphoto/2s-5nkNQchAAW05oI_B5Gg/90s.jpg"},
				{"The Whistling Kettle Troy", "https://s3-media4.fl.yelpcdn.com/bphoto/2M17VHIQabX7DDAGi9B9LQ/90s.jpg"},
				{"Bacchus", "https://s3-media1.fl.yelpcdn.com/bphoto/lCm-Wj3srXtopL5nQTrUyw/90s.jpg"},
				{"Deli & Brew", "https://s3-media1.fl.yelpcdn.com/bphoto/dRqBJG1SvXJtyEnSU9PHhQ/90s.jpg"},
				{"Duncan's Dairy Bar", "https://s3-media2.fl.yelpcdn.com/bphoto/d7YN_HDSZijdEDHu8q7wtw/90s.jpg"},
				{"Park Pub Restaurant", "https://s3-media3.fl.yelpcdn.com/bphoto/S8qQe452zIqNvRbd6QlmRw/90s.jpg"},
				{"Collar City Cafe", "https://s3-media1.fl.yelpcdn.com/bphoto/oEvxo1tdmgfv7A0Dub71Pw/90s.jpg"},
				{"The Plum Blossom", "https://s3-media3.fl.yelpcdn.com/bphoto/6vkJi5MPJACbHFnwOFEnlw/90s.jpg"},
				{"Red and Blue Asia Grill and Bar", "https://s3-media1.fl.yelpcdn.com/bphoto/LVbP_pojk65_RWsv5kcrGQ/90s.jpg"},
				{"Dinosaur Bar-B-Que", "https://s3-media4.fl.yelpcdn.com/bphoto/rpMPYZpwdYijRNnjH1qbCw/90s.jpg"}	//50		
		};

	public Recommendation(long restaurant_id, double rating){
		this.restaurant_id = restaurant_id;
		this.rating = rating;
		this.restaurant_name = restaurantStrs[(int) (50-restaurant_id)%50][0];
		this.img_link = restaurantStrs[(int) (50-restaurant_id)%50][1];
		
		//commented out but may be used later to integrate mahout code with database attriutes 
		/*
		//connect to DB to get restaurant name
		//Parameters to log into database
        String url = "jdbc:mysql://localhost:3306/preferate";
        String username = "root";
        String password = "CrackerWindow654";

        System.out.println("Connecting database...");

        //Try to connect to the database
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");

            Statement stmt = connection.createStatement();
            String query = "SELECT restaurant_name FROM restaurants WHERE restaurant_id =" + this.restaurant_id;
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();

            if(rs.next()){

            	this.restaurant_name = rs.getString("restaurant_name");

            }

            connection.close();

        } catch (SQLException e) {
            System.out.println(e);
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        */

	}


	public long getRestaurant_id(){
		return restaurant_id;
	}

	public String getRestaurant_name(){
		return restaurant_name;
	}
	public String getImg_Link(){
		return img_link;
	}

	public double getRating(){
		return rating;
	}


}
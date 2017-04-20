package restClasses;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;

import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;


public class Recommender 
{
	//stores recommendations once a Recommender has been properly instantiated
	protected ArrayList<Recommendation> recs;
	public ArrayList<String> getRecs(){
		return recs;
	}
	Recommender(){
		recs = null;
	}

	//currently outputs estimated preferences for all restaurants to System.out
	public Recommender(String params[], long userID) throws Exception
	{
		//UserID 
		//int user = 1;
		int numRestaurants = 50;
		//parameters over which we will create models
		//String params[] = {"food","menu","service"};
		parseReviews(params);
		double[][] ratings = new double[params.length][numRestaurants];
		//create our models and estimate all the preferences
		for(int i = 0; i < params.length;++i){
			String file = new String("data/Reviews"+params[i]+".csv");
			DataModel model = new FileDataModel(new File(file));
			UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
			UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
			UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
			for (int j = 0; j < numRestaurants; ++j){
				ratings[i][j] = recommender.estimatePreference(userID, j + 1);
			}
		}

		Map<Long,Double> estimates = new HashMap<Long, Double>();
		//synthesize and output overall preference scores
		//for now, just average them all
		for(long i = 0; i < numRestaurants; ++i){
			estimates.put(i, 0.0);
			for(long j = 0; j < params.length;++j){
				estimates.put(i, estimates.get(i) + ratings[(int)j][(int)i]);
			}
		}
		//sort the map
		Map<Long, Double> sortedEstimates = sortByValue(estimates);
		/* string version
		//store in recs
		int iter = 0;
		recs = new ArrayList<String>();
		for(Entry<Long, Double> i : sortedEstimates.entrySet()){
			System.out.println("Adding Rec: Restaurant " + (i.getKey()+1) + ": " + i.getValue()/params.length);
			recs.add(iter, ("Restaurant " + (i.getKey()+1) + ": " + i.getValue()/params.length));
			iter++;
		}
		*/
		//store in recs
		int iter = 0;
		recs = new ArrayList<Recommendation>();
		for(Entry<Long, Double> i : sortedEstimates.entrySet()){
			System.out.println("Adding Rec: Restaurant " + (i.getKey()+1) + ": " + i.getValue()/params.length);
			recs.add(new Recommendation(i.getKey()+1). i.getValue());
			iter++;
		}
	}

	//integrated recommender
	public Recommender(String params[], long userID, boolean online) throws Exception
	{
		//UserID 
		//int user = 1;
		int numRestaurants = 50;
		//parameters over which we will create models
		//String params[] = {"food","menu","service"};
		//parseReviews(params);
		double[][] ratings = new double[params.length][numRestaurants];
		//create our models and estimate all the preferences
		for(int i = 0; i < params.length;++i){
			//String file = new String("data/Reviews"+params[i]+".csv");
			DataModel model = ReviewList.getReviewsRecommender(params[i]);
			UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
			UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
			UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
			for (int j = 0; j < numRestaurants; ++j){
				ratings[i][j] = recommender.estimatePreference(userID, j + 1);
			}
		}

		Map<Long,Double> estimates = new HashMap<Long, Double>();
		//synthesize and output overall preference scores
		//for now, just average them all
		for(long i = 0; i < numRestaurants; ++i){
			estimates.put(i, 0.0);
			for(long j = 0; j < params.length;++j){
				estimates.put(i, estimates.get(i) + ratings[(int)j][(int)i]);
			}
		}
		//sort the map
		Map<Long, Double> sortedEstimates = sortByValue(estimates);
		//store in recs
		/*int iter = 0;
		recs = new ArrayList<String>();
		for(Entry<Long, Double> i : sortedEstimates.entrySet()){
			System.out.println("Adding Rec: Restaurant " + (i.getKey()+1) + ": " + i.getValue()/params.length);
			recs.add(iter, ("Restaurant " + (i.getKey()+1) + ": " + i.getValue()/params.length));
			iter++;
		}*/
		int iter = 0;
		recs = new ArrayList<Recommendation>();
		for(Entry<Long, Double> i : sortedEstimates.entrySet()){
			System.out.println("Adding Rec: Restaurant " + (i.getKey()+1) + ": " + i.getValue()/params.length);
			recs.add(new Recommendation(i.getKey()+1). i.getValue());
			iter++;
		}
	}

	//java 8 hashmap sorter, descending order
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		return map.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
				.collect(Collectors.toMap(
						Map.Entry::getKey, 
						Map.Entry::getValue, 
						(e1, e2) -> e1, 
						LinkedHashMap::new
						));
	}

	//may need to refactor to integrate with database
	//constructs files for the individual review parameters
	//takes an array of names of the review params, eg ["Food","Menu","Service"]
	public static void parseReviews(String[] reviewParameters) {
		//construct the csv files
		try
		{
			//Create CSVWriters  and files for writing to Reviews<Parameter>.csv
			CSVWriter[] writers = new CSVWriter[reviewParameters.length];
			int i = 0;
			for(String param : reviewParameters){
				String file = new String("data/Reviews"+param+".csv");
				writers[i] = new CSVWriter(new FileWriter(file),CSVWriter.DEFAULT_SEPARATOR,CSVWriter.NO_QUOTE_CHARACTER);
				++i;
			}
			//Read in the Reviews
			try {
				//csv file containing data
				String strFile = "data/Reviews.csv";
				CSVReader reader = new CSVReader(new FileReader(strFile));
				String [] nextLine;
				int lineNumber = 0;
				while ((nextLine = reader.readNext()) != null) {
					//skip the first line
					if(lineNumber != 0){
						// nextLine[] is an array of values from the line
						int len = nextLine.length;
						for(i = 0; i < writers.length; ++i){
							//write the appropriate line to each file
							String[] row = new String[]
									{nextLine[len-2],nextLine[len-1],nextLine[i+1]};
							writers[i].writeNext(row);
						}
					}
					lineNumber++;
				}
				//close the reader
				reader.close();
				//close the writers
				try
				{
					for(i = 0; i < writers.length; i++)
						writers[i].close();
				}catch(Exception ee){
					ee.printStackTrace();
				}
			} catch (IOException e){
				System.err.println("ERROR: Failed to read reviews");
				return;
			}

		} catch(Exception ee){
			ee.printStackTrace();
		}
	}

}



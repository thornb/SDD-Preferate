package com.preferate.recommender;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

/**
 * Hello world!
 *
 */
public class App 
{
	//currently outputs estimated preferences for all restaurants to System.out
	public static void main( String[] args ) throws Exception
	{
		//UserID 
		int user = 1;
		int numRestaurants = 50;
		//parameters over which we will create models
		String[] params = new String[]{"food","menu","service"};
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
				ratings[i][j] = recommender.estimatePreference(user, j + 1);
			}
		}
		
		double[] estimates = new double[numRestaurants];
		//synthesize and output overall preference scores
		//for now, just average them all
		for(int i = 0; i < numRestaurants; ++i){
			estimates[i] = 0;
			for(int j = 0; j < params.length;++j){
				estimates[i] += ratings[j][i];
			}
			System.out.println((i + 1) + ": " + estimates[i]/3);
		}
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



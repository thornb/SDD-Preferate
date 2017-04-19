package restClasses;

import java.io.File;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class GroupRecommender extends Recommender{

	public void runRecommender(String params[], long userIDs[]) throws Exception
	{
		int numRestaurants = 50;
		int numUsers = userIDs.length;
		//parameters over which we will create models
		//String params[] = {"food","menu","service"};
		parseReviews(params);
		double[][][] ratings = new double[numUsers][params.length][numRestaurants];
		//create preferences for all users
		for(int u = 0; u < numUsers; ++u){
			for(int i = 0; i < params.length;++i){
				String file = new String("data/Reviews"+params[i]+".csv");
				DataModel model = new FileDataModel(new File(file));
				UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
				UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
				UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
				for (int j = 0; j < numRestaurants; ++j){
					ratings[u][i][j] = recommender.estimatePreference(userIDs[u], j + 1);
				}
			}
		}

		double[] estimates = new double[numRestaurants];
		//synthesize and output overall preference scores
		//for now, just average them all
		for(int i = 0; i < numRestaurants; ++i){
			estimates[i] = 0;
			for(int j = 0; j < params.length;++j){
				for(int u = 0; u < numUsers; u++){
					estimates[i] += ratings[u][j][i];
				}
			}
			System.out.println((i + 1) + ": " + estimates[i]/(3*numUsers));
		}
	}
}

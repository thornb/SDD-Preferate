package restClasses;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class GroupRecommender extends Recommender{

	public GroupRecommender(String[] params, long userID) throws Exception {
		super(params, userID);
	}

	public GroupRecommender(String params[], long[] userIDs) throws Exception
	{
		int numRestaurants = 50;
		int numUsers = userIDs.length;
		if(numUsers == 1){
			System.out.println("only one user, defautling to single recommender");
			Recommender rec = new Recommender(params, userIDs[0]);
			recs = rec.getRecs();
			return;
		}
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

		Map<Long,Double> estimates = new HashMap<Long, Double>();
		//synthesize and output overall preference scores
		//for now, just average them all
		for(long i = 0; i < numRestaurants; ++i){
			estimates.put(i, 0.0);
			for(long j = 0; j < params.length;++j){
				for(long u = 0; u < numUsers; u++){
					estimates.put(i, estimates.get(i) + ratings[(int)u][(int)j][(int)i]);
				}
			}
		}
		Map<Long, Double> sortedEstimates = sortByValue(estimates);
		//output and store
		/*
		int iter = 0;
		recs = new ArrayList<String>();
		for(Entry<Long, Double> i : sortedEstimates.entrySet()){
			System.out.println("adding rec: Restaurant " + (i.getKey()+1) + ": " + i.getValue()/(params.length*numUsers));
			recs.add(iter, ("Restaurant " + (i.getKey()+1) + ": " + i.getValue()/(params.length*numUsers)));
			iter++;
		}
		return;*/
		addRecs(sortedEstimates, params);
	}
	//online recommender
	public GroupRecommender(String params[], long[] userIDs, boolean online) throws Exception
	{
		int numRestaurants = 50;
		int numUsers = userIDs.length;
		if(numUsers <= 1){
			System.out.println("only one user, defautling to single recommender");
			Recommender rec = new Recommender(params, userIDs[0]);
			recs = rec.getRecs();
			return;
		}
		//parameters over which we will create models
		//String params[] = {"food","menu","service"};
		//parseReviews(params);
		double[][][] ratings = new double[numUsers][params.length][numRestaurants];
		//create preferences for all users
		for(int u = 0; u < numUsers; ++u){
			for(int i = 0; i < params.length;++i){
				DataModel model = ReviewList.getReviewsRecommender(params[i]);
				UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
				UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
				UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
				for (int j = 0; j < numRestaurants; ++j){
					ratings[u][i][j] = recommender.estimatePreference(userIDs[u], j + 1);
				}
			}
		}

		Map<Long,Double> estimates = new HashMap<Long, Double>();
		//synthesize and output overall preference scores
		//for now, just average them all
		for(long i = 0; i < numRestaurants; ++i){
			estimates.put(i, 0.0);
			for(long j = 0; j < params.length;++j){
				for(long u = 0; u < numUsers; u++){
					estimates.put(i, estimates.get(i) + ratings[(int)u][(int)j][(int)i]);
				}
			}
		}
		Map<Long, Double> sortedEstimates = sortByValue(estimates);
		//output and store
		/*
		int iter = 0;
		recs = new ArrayList<String>();
		for(Entry<Long, Double> i : sortedEstimates.entrySet()){
			System.out.println("adding rec: Restaurant " + (i.getKey()+1) + ": " + i.getValue()/(params.length*numUsers));
			recs.add(iter, ("Restaurant " + (i.getKey()+1) + ": " + i.getValue()/(params.length*numUsers)));
			iter++;
		}
		return;
		*/
		addRecs(sortedEstimates, params);
	}
}

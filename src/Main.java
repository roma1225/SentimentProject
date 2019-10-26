import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Tweet> tweets = ProcessFile.makeTweetsList("");
        ArrayList<String> connotationsOfTweets = ProcessFile.makeConnotationList("");
        ArrayList<Double> intensities = calculateIntensities();
        ArrayList<TweetInfo> tweetsInfo = ProcessFile.makeTweetInfoList(tweets, connotationsOfTweets, intensities);

    }

    private static ArrayList<Double> calculateIntensities() {

    }

    private static void calculateAndDisplayError(ArrayList<TweetInfo> tweetsInfo){

        double totalError = 0.0;
        double count;

        for (int i = 0; i < tweetsInfo.size(); i++) {
            TweetInfo tweetInfo = tweetsInfo.get(i);
            String connotation = tweetInfo.getConnotation();
            Tweet tweet = tweetInfo.getTweet();
            double intensity =
            String prediction = sentimentScore(tweet);
            String answer = connotation;
            if(prediction.equals(connotation)) count++;

            double error = (((prediction - tweet.getIntensity()/tweet.\tweet.getIntensity())*100);
            totalError += Math.abs(error);
        }

        System.out.println("Average error is of Connotation:" + count/tweetsInfo.size() *100 );


    }

//    public ArrayList<Double> intensityData() { //for graphing - % of pos and neg
//        double positiveIntensityScore =(double) (positives.size()-1) / ((positives.size()-1) + (negative.size()-1));
//        double negativeIntensityScore = (double) (negatives.size()-1) / ((positives.size()-1) + (negative.size()-1));
//
//        if(positiveIntensityScore > negativeIntensityScore){
//           score = intensityScore(positiveIntensityScore);
//        }else{
//            score = -1 * intensityScore(negativeIntensityScore);
//        }
//
//        return score;
//    }

    public static String sentimentScore(Tweet tweet) {
        double negBorder = 33.34;
        double posBorder = 66.67;
        String connotation = "";
        double intensity = tweet.calculateIntensity();

        if(intensity>posBorder && intensity <= 100) {
            connotation = "positive";
        }
        else if(intensity< negBorder && intensity >= 0){
            connotation = "negative";
        }
        else if(intensity>= negBorder && intensity<= posBorder){
            connotation = "neutral";
        }
        return connotation;
    }

//    public double IntensityPercentage(){ //evaluate the intensity - using dist from middle
//
//
//    }

}

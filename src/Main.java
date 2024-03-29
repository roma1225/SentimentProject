import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Tweet> tweets = ProcessFile.makeTweetsList("data/tweets.csv"); // what about first line???!
        ArrayList<String> connotationsOfTweets = ProcessFile.makeConnotationList("data/connotation.csv");
        ArrayList<Double> intensities = makeIntensitiesList(tweets);
        ArrayList<TweetInfo> tweetsInfo = ProcessFile.makeTweetInfoList(tweets, connotationsOfTweets, intensities);

        calculateAndDisplayError(tweetsInfo);

    }

    private static ArrayList<Double> makeIntensitiesList(ArrayList<Tweet> tweets) {
        ArrayList<Double> intensities = new ArrayList<>();
        for (int i = 0; i < tweets.size(); i++) {
            Tweet tweet = tweets.get(i);
            double intensity = tweet.getIntensity();
            intensities.add(intensity);
        }
        return intensities;
    }

    private static void calculateAndDisplayError(ArrayList<TweetInfo> tweetsInfo){
        System.out.println("starting to calculate and display");
        double count = 0;

        System.out.println(tweetsInfo.size());

        for (int i = 0; i < tweetsInfo.size(); i++) {
            TweetInfo tweetInfo = tweetsInfo.get(i);
            String connotation = tweetInfo.getConnotation();

            Tweet tweet = tweetInfo.getTweet();

            String prediction = sentimentScore(tweet);
            System.out.println(tweet);
            System.out.println("p: " + prediction);
            System.out.println("a: " +connotation);

            if(prediction.equals(connotation)) count++;
        }

        System.out.println(count);
        System.out.println(tweetsInfo.size());

        System.out.println("You Got: " + count/tweetsInfo.size() *100 + "% Correct!");
    }

    public static String sentimentScore(Tweet tweet) {
        double negBorder = 33.34;
        double posBorder = 66.67;
        double intensity = tweet.calculateIntensity();
        String connotation = "";

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



}

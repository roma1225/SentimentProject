public class TweetInfo {
    Tweet tweet;
    double intensity;
    String connotation;

    public TweetInfo(Tweet tweet, String connotation, double intensity){
        this.tweet = tweet;
        this.intensity = intensity;
        this.connotation = connotation;
    }

    public Tweet getTweet(){
        return tweet;
    }

    public double getIntensity(){
        return intensity;
    }

    public void setIntensity(double intensiti){
        intensity = intensiti;
    }

    public String getConnotation(){
        return connotation;
    }


}
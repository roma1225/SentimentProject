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

//    public static String correctFileName(){ //do when have files
//        String newFilename = "";
//        newFilename = getFileName().replaceAll("-", " ");
//        newFilename = newFilename.substring(0,newFilename.length()-8) + " " + newFilename.substring(newFilename.length()-7);
//        return newFilename;
//    }

}
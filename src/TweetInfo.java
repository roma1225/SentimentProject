public class TweetInfo {
    static Tweet tweet;
    static double intensity;
    static String connotation;

    public TweetInfo(Tweet tweet, String connotation, double intensity){
        this.tweet = tweet;
        this.intensity = intensity;
        this.connotation = connotation;
    }

    public static Tweet getTweet(){
        return tweet;
    }

    public static double getIntensity(){
        return intensity;
    }

    public static String getConnotation(){
        return connotation;
    }

//    public static String correctFileName(){ //do when have files
//        String newFilename = "";
//        newFilename = getFileName().replaceAll("-", " ");
//        newFilename = newFilename.substring(0,newFilename.length()-8) + " " + newFilename.substring(newFilename.length()-7);
//        return newFilename;
//    }

}
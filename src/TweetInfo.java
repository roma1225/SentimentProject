public class TweetInfo {
    static Tweet tweet;
    static String intensity;

    public TweetInfo(Tweet tweet, String intensity){
        this.tweet = tweet;
        this.intensity = intensity;
    }

    public static Tweet getFileName(){
        return tweet;
    } //returns filename

    public static String getIntensity(){
        return TweetInfo.intensity;
    } //returns Intensity

    public static String correctFileName(){ //do when have files
        String newFilename = "";
        newFilename = getFileName().replaceAll("-", " ");
        newFilename = newFilename.substring(0,newFilename.length()-8) + " " + newFilename.substring(newFilename.length()-7);
        return newFilename;
    }

}
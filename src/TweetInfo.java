public class TweetInfo {
    static String Filename;
    static String intensity;

    public TweetInfo(String filename, String intensity){
        this.Filename = filename;
        this.intensity = intensity;
    }

    public static String getFileName(){
        return TweetInfo.Filename;
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
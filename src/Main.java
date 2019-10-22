import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<String> positives = ProcessFile.readFileAsString("bluh");
        ArrayList<String> negatives = ProcessFile.readFileAsString("bluh");
        ArrayList<String> tweets = ProcessFile.readFileAsString("bluh");

    }



    private static void calculateAndDisplayError(){

        ArrayList<TweetInfo> tweets = ProcessFile.readDocInfo("data/Texts/allfeatures-ose-final.csv");
        double totalError = 0.0;

        for (TweetInfo tweet: tweets) {

            String filename = tweet.getFileName();

            String text = ProcessFile.readFileAsString(filename);

            double prediction = intensity("data/Texts/AllTexts/"+ filename);
            double error = (((prediction - tweet.getIntensity()/tweet.\tweet.getIntensity())*100);
            totalError += Math.abs(error);
        }

        System.out.println("Average error is:" + totalError/docs.size() );

    }

    public double intensityScore() {
        //HERE WE PUT THE EQUATION
    }

}

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

            double prediction = ("data/Texts/AllTexts/"+ filename); //I'm not sure what its called
            double error = (((prediction - tweet.getIntensity()/tweet.\tweet.getIntensity())*100);
            totalError += Math.abs(error);
        }

        System.out.println("Average error is:" + totalError/tweets.size() );

    }

    public double intensity() {
        double score;
        double positiveIntensityScore =(double) (positives.size()) / ((positives.size()) + (negative.size()));
        double negativeIntensityScore = (double) (negatives.size()) / ((positives.size()) + (negative.size()));

        if(positiveIntensityScore > negativeIntensityScore){
           score = intensityScore(positiveIntensityScore);
        }else{
            score = -1 * intensityScore(negativeIntensityScore);
        }

        return score;
    }

    public double intensityScore(double intense){
        double moderateIntensityMax;
        double moderateIntensityMin;
        double intenseIntensityMin;
        double intenseIntensityMax;
        if(intense > moderateIntensityMin && intense < moderateIntensityMax){
            score = blah blah blah;  //assign a moderate intensity score
        }
        if(intense > intenseIntensityMin && intense < intenseIntensityMax){
            score = ;//assign intense score;
        }else{
            score = ;//assign a neutral score;
        }
    }



}

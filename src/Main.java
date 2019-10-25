import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        String posText = ProcessFile.readFileAsString("idea/pos.cvs");
        String negText = ProcessFile.readFileAsString("idea/neg.cvs");
    }



    private static void calculateAndDisplayError(){

        ArrayList<TweetInfo> tweets = ProcessFile.readDocInfo("data/Texts/allfeatures-ose-final.csv");
        double totalError = 0.0;

        for (TweetInfo tweet: tweets) {

            String filename = tweet.getFileName();

            String text = ProcessFile.readFileAsString(filename);

            double prediction = intensityScore(""+ filename);
            double error = (((prediction - tweet.getIntensity()/tweet.\tweet.getIntensity())*100);
            totalError += Math.abs(error);
        }

        System.out.println("Average error is:" + totalError/docs.size() );

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

    public String intensityScore(String filename) {
        String text = ProcessFile.readFileAsString(filename);
        Tweet tweet = new Tweet(text);
        double negBorder = 33.34;
        double posBorder = 66.67;
        String connotation = "";
        int wordCount = tweet.getWordCount();
        double negPercentage = (double)(tweet.getNegative()/wordCount) *100;
        double posPercentage = (double)(tweet.getPositive()/wordCount) *100;
        double intensity = (double)(posPercentage/(negPercentage+posPercentage)*100);

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

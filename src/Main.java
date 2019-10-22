import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<String> positives = readF
    }

    private static void calculateAndDisplayError(){

        ArrayList<DocumentInfo> docs = TextLib.readDocInfo("data/Texts/allfeatures-ose-final.csv");
        double totalError = 0.0;

        for (DocumentInfo doc: docs) {

            String filename = doc.getFileName();

            String text = TextLib.readFileAsString(filename);

            double prediction = FKReadability("data/Texts/AllTexts/"+ filename);
            double error = (((prediction - doc.getReadabilityScore())/doc.getReadabilityScore())*100);
            totalError += Math.abs(error);
        }

        System.out.println("Average error is:" + totalError/docs.size() );

    }

    public double intensityScore() {
        //HERE WE PUT THE EQUATION
    }

}

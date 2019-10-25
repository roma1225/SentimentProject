import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class ProcessFile {
    public String text;
    public ArrayList<String> sentences;

    //how does the readFileAsString work
    //


    public static String readFileAsString(String filename) {
        Scanner scanner;
        StringBuilder output = new StringBuilder();

        try {
            scanner = new Scanner(new FileInputStream(filename), "UTF-8");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                output.append(line.trim()+"\n");
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }

        return output.toString();
    }

    public static ArrayList<String> splitIntoWords(ArrayList<String> sentences) {
        ArrayList<String> newWords = new ArrayList<>();
        for (int i = 0; i < sentences.size(); i++) {
            String sentence = sentences.get(i);
            String[] words = sentence.split(" ");
            for (String word: words) {
                newWords.add(word);
            }
        }
        return newWords;
    }

    public ArrayList<String> splitIntoSentences(){

        ArrayList<String> output = new ArrayList<String>();
        Locale locale = Locale.US;
        BreakIterator breakIterator = BreakIterator.getSentenceInstance(locale);
        breakIterator.setText(text);

        int prevIndex = 0;
        int boundaryIndex = breakIterator.first();
        while(boundaryIndex != BreakIterator.DONE) {
            String sentence = text.substring(prevIndex, boundaryIndex).trim();
            if (sentence.length()>0)
                output.add(sentence);
            prevIndex = boundaryIndex;
            boundaryIndex = breakIterator.next();
        }

        String sentence = text.substring(prevIndex).trim();
        if (sentence.length()>0)
            output.add(sentence);

        return output;
    }

    public static ArrayList<TweetInfo> readDocInfo(String filename){

        Scanner scanner;
        ArrayList<TweetInfo> tweetInfoList = new ArrayList<>();

        try {
            scanner = new Scanner(new FileReader(filename));
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                TweetInfo info = processLine(line);

                tweetInfoList.add( info );

            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }

        return tweetInfoList;
    }

    private static String getWordFromLine(String line) {
        return line.substring(0, line.indexOf("="));
    }

    public static TweetInfo processLine(String line) {
        String[] values = line.split(",");    // note:  may still be starting or ending spaces in values!

    /* note:  may want to check the length of values to be this line is good.  If not, display “bad
             input” and show the line */

        String filename = values[0].trim();
        String intensity = Double.parseDouble(values[91].trim() ); // fix trim... find patterns


        return new TweetInfo(filename, intensity);

    }

}


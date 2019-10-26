import java.io.*;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class ProcessFile {

    public static ArrayList<Tweet> makeTweetsList(String filename){
        String text = readFileAsString(filename);
        String[] texts = text.split("\n");
        ArrayList<Tweet> tweets = new ArrayList<>();
        int lines = 2836;

        for (int i = 0; i < lines; i++) {
            Tweet tweet;
            tweet = new Tweet(texts[i]);
            tweets.add(tweet);
        }
        return tweets;
    }

    public static ArrayList<String> makeConnotationList(String filename){
        String text = readFileAsString(filename);
        String[] texts = text.split("\n");
        ArrayList<String> words = new ArrayList<>();
        int lines = 4500; //supposed to be countlines();

        for (int i = 0; i <= lines; i++) {
            String word = texts[i];
            words.add(word);
        }
        return words;
    }

    public static ArrayList<TweetInfo> makeTweetInfoList(ArrayList<Tweet> tweets, ArrayList<String> connotation, ArrayList<Double> intensities){
        ArrayList<TweetInfo> tweetsInfo = new ArrayList<>();

        if(tweets.size() == connotation.size() && tweets.size() == intensities.size()) {

            for (int i = 0; i < tweets.size(); i++) {

                Tweet tweet = tweets.get(i);
                String cntion = connotation.get(i);
                double intensity = intensities.get(i);
                TweetInfo tweetInfo = new TweetInfo(tweet, cntion, intensity);
                tweetsInfo.add(tweetInfo);

            }
        }

        return tweetsInfo;
    }

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


//    public static int countLines() {
//        try{
//
//            File file =new File("data/tweets.csv");
//
//            if(file.exists()){
//
//                FileReader fileReader = new FileReader(filename);
//                LineNumberReader lnr = new LineNumberReader(fileReader);
//
//                int linenumber = 0;
//
//                while (lnr.readLine() != null){
//                    linenumber++;
//                }
//
//                return linenumber;
//
//
//            }else{
//                System.out.println("File does not exists!");
//                return 0;
//            }
//
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//
//        return 0;
//    }

}


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
        int lines = texts.length;

        System.out.println("Lines: " + lines);

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
        int lines = texts.length;

        for (int i = 0; i < lines; i++) {
            String word = texts[i];
            words.add(word);
        }
        return words;
    }

    public static ArrayList<TweetInfo> makeTweetInfoList(ArrayList<Tweet> tweets, ArrayList<String> connotation, ArrayList<Double> intensities){
        ArrayList<TweetInfo> tweetsInfo = new ArrayList<>();

        System.out.println(tweets.size());
        System.out.println(connotation.size());

        if(tweets.size() == connotation.size()) {

            for (int i = 0; i < tweets.size(); i++) {

                Tweet tweet = tweets.get(i);
                String cntion = connotation.get(i);
                double intensity = intensities.get(i);
                TweetInfo tweetInfo = new TweetInfo(tweet, cntion, intensity);
                tweetsInfo.add(tweetInfo);

            }
        }
        System.out.println(tweetsInfo.size());
        return tweetsInfo;
    }

    public static String readFileAsString(String filename) {
        Scanner scanner;
        StringBuilder output = new StringBuilder();

        try {
            scanner = new Scanner(new FileInputStream(filename), "UTF-8");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(filename.equals("data/tweets.csv")) {
                    line = removeExcessThings(line);
                }
                output.append(line.trim()+"\n");
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }

        return output.toString();
    }

    public static String removeExcessThings(String line){
        String newLine = line;
        for (int i = 0; i < line.length(); i++) {
            String thing = line.substring(i, i+ 1);
            if(thing.equals("@")){
                int endOfThing = line.indexOf(" ", i);
                if(endOfThing < line.length()) {
                    newLine = line.substring(0, i) + line.substring(endOfThing + 1);
                }
            }
        }
        return newLine;
    }
}


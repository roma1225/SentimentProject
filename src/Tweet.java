import java.util.ArrayList;

public class Tweet {
    String text;
    double positivity;
    ArrayList<String> words;
    ArrayList<String> sentences;
    ArrayList<String> vocab;
    public boolean isSentenceUpdates = true;
    public ArrayList<String> positives;
    public static ArrayList<String> negatives;


    public Tweet(String text, double positivity){
        this.text = text;
        this.positivity = positivity;
    }
    public

    public String getTweet(){
        return text;
    }

    public ArrayList<String> getVocabList(){
        ArrayList<String> vocab = new ArrayList<>();
        for (String word : words) {
            if(!isWordInList(word, vocab)){
                vocab.add(word);
            }
        }
        return vocab;  //non repeating words
    }

    public boolean isWordInList(String target, ArrayList<String> text){
        for (int i = 0; i < text.size(); i++) {
            if(text.contains(target)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> createVocabList() {
        ArrayList<String> vocab = new ArrayList<>(); // change so that we use words arraylist
        for (String sentence : sentences) {
            String[] words = sentence.split(" ");
            for (int i = 0; i < words.length; i++) {
                for (int j = 0; j < words.length; i++) {
                    if (words[i] != words[j]) {
                        vocab.add(words[i]);
                    }
                }

            }
        }
        return vocab;
    }

    public int getWordCount() {
        int wordCount = 0;
        for (String sentence : sentences) {
            String[] words = sentence.split(" ");
            wordCount = wordCount + words.length;

        }
        return wordCount;
    }

    public double getOccurencesOfNegativeWords(){
        int totalNegatives = 0;
        for(int i =0; i<words.size(); i++){
            String word = words.get(i);
            String privWord = words.get(i-1);
            if( isNegative(word) ){
                if( isNegative(privWord) || isNeutral(privWord) ){
                    totalNegatives++;
                    if( isInCaps(word) ){
                        //add something
                    }
                }
            }
        }
        return totalNegatives;
    }

    public int getOccurencesOfPositiveWords(){
        int totalPositives = 0;
        for(int i =0; i<words.size(); i++){
            String word = words.get(i);
            String privWord = words.get(i-1);
            if(isPositive(word)){
                if(isPositive(privWord) || isNeutral(privWord)){
                    totalPositives++;
                    if(isInCaps(word)){
                        //add something
                    }
                }
            }
        }
        return totalPositives;
    }

    private static boolean isNegative(String word) {
        if(negatives.contains(word)){
            return true;
        }
        return false;
    }

    private boolean isPositive(String word) {
        if(positives.contains(word)){
            return true;
        }
        return false;
    }

    private boolean isNeutral(String word){
        if(!positives.contains(word) && !negatives.contains(word)){
            return true;
        }
        return false;
    }

    public boolean isInCaps(String word){
        if("ABCDEFGHIJKLMNOPQRSTUVWXYZ".contains(word)){
            return true;
        }
        return false;
    }

}








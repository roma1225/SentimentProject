import java.util.ArrayList;

public class Tweet {
    String text;
    double positive;
    double negative;
    double neutral;
    String positivity;
    ArrayList<String> words;
    ArrayList<String> sentences;
    ArrayList<String> vocab = getVocabList();
    ArrayList<String> alphabet = getCharcsList();
    public boolean isSentenceUpdates = true;
    public static ArrayList<String> positives;
    public static ArrayList<String> negatives;
    public static ArrayList<String> positiveEmojis;
    public static ArrayList<String> negativeEmojis;


    public Tweet(String text, String positivity){
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

    public void getOccurencesOfNegativeWords(){
        for(int i =0; i<words.size(); i++) {
            String word = words.get(i);
            String privWord = words.get(i - 1);
            if (isNegative(word)) {
                negative++;
                if (isInCaps(word)) {
                    negative = negative + 0.5;
                }
            }
            if (privWord.equals("not")) {
                positive++;
                if (isInCaps(privWord)) {
                    negative = negative + 0.5;
                }
            }
        }
    }

    public void getOccurencesOfPositiveWords(){
        for(int i =0; i<words.size(); i++){
            String word = words.get(i);
            String privWord = words.get(i-1);
            if(isPositive(word)){
                positive++;
                if(isInCaps(word)){
                        positive = positive + 0.5;
                    }
                }
                if(privWord.equals("not")){
                    negative++;
                    if(isInCaps(privWord)){
                        positive = positive + 0.5;
                }
            }
        }
    }

    private boolean isNegative(String word) {
        if(!isAnEmoji(word)) {
            if (negatives.contains(word)) {
                return true;
            }
        }
        else {
            if (negativeEmojis.contains(word)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPositive(String word) {
        if(!isAnEmoji(word)) {
            if (positives.contains(word)) {
                return true;
            }
        }
        else {
            if (positiveEmojis.contains(word)) {
                return true;
            }
        }
        return false;
    }

//    private boolean isNeutral(String word){
//        if(!positives.contains(word) && !negatives.contains(word)){
//            return true;
//        }
//        return false;
//    }

    public boolean isInCaps(String word){
        if("ABCDEFGHIJKLMNOPQRSTUVWXYZ".contains(word)){ //maybe change - ask mr D
            return true;
        }
        return false;
    }

    public boolean isAnEmoji(String word){
        int count = 0;
        word = word.toLowerCase();
        for (int i = 0; i < word.length()-1; i++) {
            String charc = word.substring(i, i+1);
            if(alphabet.contains(charc)){
                count++;
            }
        }
        if(count == word.length()) return true;
        else return false;
    }

    private ArrayList<String> getCharcsList(){ //should be here?
        ArrayList<String> alphabet = new ArrayList<>();
        alphabet.add("a");
        alphabet.add("b");
        alphabet.add("c");
        alphabet.add("d");
        alphabet.add("e");
        alphabet.add("f");
        alphabet.add("g");
        alphabet.add("h");
        alphabet.add("i");
        alphabet.add("j");
        alphabet.add("k");
        alphabet.add("l");
        alphabet.add("m");
        alphabet.add("n");
        alphabet.add("o");
        alphabet.add("p");
        alphabet.add("q");
        alphabet.add("r");
        alphabet.add("s");
        alphabet.add("t");
        alphabet.add("u");
        alphabet.add("v");
        alphabet.add("w");
        alphabet.add("x");
        alphabet.add("y");
        alphabet.add("z");
        return alphabet;
    }

}








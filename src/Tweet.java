import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Locale;

public class Tweet {
    String text;
    double positive; //score
    double negative; //score
    ArrayList<String> words; //list of words
    ArrayList<String> sentences; //list of sentences of the text
    ArrayList<String> vocab; //vocabulary
    ArrayList<String> alphabet; //A-Z
    ArrayList<String> positives; //list of positive words of text
    ArrayList<String> negatives; //list of negative words of text
    ArrayList<String> positiveEmojis; //list of positive emojis
    ArrayList<String> negativeEmojis; //list of negative emojis
    ArrayList<String> globalPositives; // data set of pos
    ArrayList<String> globalNegatives; // data set of neg
    double intensity; //intensity calculated


    public Tweet(String text){
        this.text = text;
        this.sentences = Statics.splitIntoSentences(text);
        this.words = Statics.splitIntoWords(sentences);
        this.alphabet = getAlphabetList();
//        this.positiveEmojis = ProcessFile.makeConnotationList("");
//        this.negativeEmojis = ProcessFile.makeConnotationList("");
        this.globalPositives = ProcessFile.makeConnotationList("data/pos.csv");
        this.globalNegatives = ProcessFile.makeConnotationList("data/neg.csv");
        this.intensity = calculateIntensity();
        this.positive = getPositive(); ///????? its the score
        this.negative = getNegative();//????
    }

    public ArrayList<String> getAlphabetList(){
        return alphabet;
    }

    public ArrayList<String> getWordList(){
        return words;
    }

    public ArrayList<String> getSentenceList(){
        return sentences;
    }

    public String getText(){
        return text;
    }

    public double getPositive(){
        return positive;
    }

    public double getNegative(){
        return negative;
    }

    public double getIntensity(){
        return calculateIntensity();
    }

    public void setIntensity(double newIntensity){
        intensity = newIntensity;
    }

//    public ArrayList<String> getVocabList(){
//        for (String word : words) {
//            if(!isWordInList(word, getVocabList())){
//                vocab.add(word);
//            }
//        }
//        return vocab;  //non repeating words
//    }

    public boolean isWordInList(String target, ArrayList<String> list){
        for (int i = 0; i < list.size(); i++) {
            if(getText().contains(target)){
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

    private void pattern3(){ // 0....+0
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            if(!isNeutral(word)) {
                int count = 1;
                if (i - 1 < words.size()) {
                    String next = words.get(i + count);
                    if (!isNeutral(next)) {
                        String lastWord = next;
                        while (!isNeutral(lastWord)) {
                            count = count + 1;
                            lastWord = words.get(i + count);
                        }
                        if (isPositive(lastWord)) {
                            positive++;
                            positives.add(lastWord);
                            if (isInCaps(lastWord)) {
                                positive = positive + 0.5;
                            }
                            if (next.contains("!")) {
                                positive = positive + 0.5;
                            }
                        }
                        if (isNegative(lastWord)) {
                            negative++;
                            negatives.add(lastWord);
                            if (isInCaps(lastWord)) {
                                negative = negative + 0.5;
                            }
                            if (next.contains("!")) {
                                negative = negative + 0.5;
                            }
                            if (next.contains("?")) {
                                negative = negative + 0.25;
                            }
                        }
                    }
                }
            }
        }
    }

    private void pattern2(){
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            String next = words.get(i);
            if(word.equals("not")){
                if(isNegative(next)){
                    positive++;
                    positives.add("not " + next);
                    if(isInCaps(next)){
                        positive = positive + 0.5;
                    }
                    if(next.contains("!")){
                        positive = positive + 0.5;
                    }
                }
                else if(isPositive(next)){
                    negative++;
                    negatives.add("not " + next);
                    if(isInCaps(next)){
                        negative = negative + 0.5;
                    }
                    if(next.contains("!")){
                        negative = negative + 0.5;
                    }
                    if(next.contains("?")){
                        negative = negative + 0.25;
                    }
                }
            }
        }
    }

    private void pattern1(){
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            String next = words.get(i);
            if(!isNeutral(word) && isNeutral(next)){
                if(isPositive(word)){
                    positive++;
                    positives.add(word);
                    if(isInCaps(word)){
                        positive = positive + 0.5;
                    }
                    if(next.contains("!")){
                        positive = positive + 0.5;
                    }
                }
                else if(isNegative(word)){
                    negative++;
                    negatives.add(word);
                    if(isInCaps(next)){
                        negative = negative + 0.5;
                    }
                    if(next.contains("!")){
                        negative = negative + 0.5;
                    }
                    if(next.contains("?")){
                        negative = negative + 0.25;
                    }
                }
            }
        }
    }


    private boolean isNegative(String word) {
        //if(isAWord(word)) {
            if (globalNegatives.contains(word)) {
                return true;
            }
       // }
//        else {
//            if (negativeEmojis.contains(word)) {
//                return true;
//            }
//        }
        return false;
    }

    private boolean isPositive(String word) {
//        if(isAWord(word)) {
            if (globalPositives.contains(word)) {
                return true;
            }
        //}
//        else {
//            if (positiveEmojis.contains(word)) {
//                return true;
//            }
     //   }
        return false;
    }

    public boolean isNeutral(String word){
        if(!isPositive(word) && !isNegative(word)){
            return true;
        }
        return false;
    }

    public boolean isInCaps(String word){
        if("ABCDEFGHIJKLMNOPQRSTUVWXYZ".contains(word)){ //maybe change - ask mr D
            return true;
        }
        return false;
    }

    public boolean isAWord(String word){
        int count = 0;
        word = word.toLowerCase();
        for (int i = 0; i < word.length()-1; i++) {
            String charc = word.substring(i, i+1);
            if(isWordInList(charc, alphabet)){
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

    public double calculateIntensity(){
        pattern1();
        pattern3();
        pattern2();
        double negPercentage = (getNegative()/getWordList().size()) *100;
        double posPercentage = (getPositive()/getWordList().size()) *100;
        double intense = (posPercentage/(negPercentage+posPercentage)*100);
        return intense;
    }
}








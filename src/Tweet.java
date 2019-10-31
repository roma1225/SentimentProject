import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Locale;

public class Tweet {
    ArrayList<String> globalPositives = ProcessFile.makeConnotationList("data/pos.csv");
    ArrayList<String> globalNegatives = ProcessFile.makeConnotationList("data/neg.csv");
    String text;
    ArrayList<String> words; //list of words
    ArrayList<String> sentences; //list of sentences of the text
    ArrayList<String> alphabet; //A-Z
    ArrayList<String> positives; //list of positive words of text
    ArrayList<String> negatives; //list of negative words of text
    // data set of neg
    ArrayList<String> capitalAlphabet;
    double intensity; //intensity calculated


    public Tweet(String text){
        this.text = text;
        this.sentences = Statics.splitIntoSentences(text);
        this.words =  Statics.splitIntoWords(sentences);
        this.alphabet = getAlphabetList();
        this.intensity = calculateIntensity();
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

    private boolean isWordInList(String target, ArrayList<String> list){
        if (list.size() > 0) {
            for (String word : list) {
                if (word.equals(target)) {
                    return true;
                }
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

//    public double[] runAllPatterns(ArrayList<String> words){
//        System.out.println(words);
//        double negative = 0;
//        double positive = 0;
//        for (int i = 0; i < words.size(); i++) {
//            String word = words.get(i);
//            System.out.println(word);
//           // if (isAWord(word)) {
//
//            String next = words.get(i);
//            if(word.equals("not")){
//                if(isNegative(next)){
//                    positive++;
//                    positives.add("not " + next);
//                    if(isInCaps(next)){
//                        positive = positive + 0.5;
//                    }
//                    if(next.contains("!")){
//                        positive = positive + 0.5;
//                    }
//                }
//                else if(isPositive(next)){
//                    negative++;
//                    negatives.add("not " + next);
//                    if(isInCaps(next)){
//                        negative = negative + 0.5;
//                    }
//                    if(next.contains("!")){
//                        negative = negative + 0.5;
//                    }
//                    if(next.contains("?")){
//                        negative = negative + 0.25;
//                    }
//                }
//            }
//            if(!isNeutral(word) && isNeutral(next)){
//                if(isPositive(word)){
//                    positive++;
//                    positives.add(word);
//                    if(isInCaps(word)){
//                        positive = positive + 0.5;
//                    }
//                    if(next.contains("!")){
//                        positive = positive + 0.5;
//                    }
//                }
//                else if(isNegative(word)){
//                    negative++;
//                    negatives.add(word);
//                    if(isInCaps(next)){
//                        negative = negative + 0.5;
//                    }
//                    if(next.contains("!")){
//                        negative = negative + 0.5;
//                    }
//                    if(next.contains("?")){
//                        negative = negative + 0.25;
//                    }
//                }
//            }
//            System.out.println("pos;" + positive + "neg;" + negative);
//        }
//        double[] negAndPos = new double[]{negative, positive};
//        return negAndPos;
//        //}
//    }

//    public void pattern3(){ // 0....+0
//        for (int i = 0; i < words.size(); i++) {
//            String word = words.get(i);
//           // if (isAWord(word)) {
//                if (!isNeutral(word)) {
//                    int count = 1;
//                    if (i + 1 < words.size() && i > 0) {
//                        int index = i + count;
//                        String next;
//                        next = words.get(index);
//                        if (!isNeutral(next)) {
//                            String lastWord = next;
//                            while (!isNeutral(lastWord)) {
//                                count = count + 1;
//                                lastWord = words.get(i + count);
//                            }
//                            if (isPositive(lastWord)) {
//                                positive++;
//                                positives.add(lastWord);
//                                if (isInCaps(lastWord)) {
//                                    positive = positive + 0.5;
//                                }
//                                if (next.contains("!")) {
//                                    positive = positive + 0.5;
//                                }
//                            }
//                            if (isNegative(lastWord)) {
//                                negative++;
//                                negatives.add(lastWord);
//                                if (isInCaps(lastWord)) {
//                                    negative = negative + 0.5;
//                                }
//                                if (next.contains("!")) {
//                                    negative = negative + 0.5;
//                                }
//                                if (next.contains("?")) {
//                                    negative = negative + 0.25;
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        //}
//    }
//
//    public void pattern2(){
//        for (int i = 0; i < words.size(); i++) {
//            String word = words.get(i);
//            String next = words.get(i);
//            if(word.equals("not")){
//                if(isNegative(next)){
//                    positive++;
//                    positives.add("not " + next);
//                    if(isInCaps(next)){
//                        positive = positive + 0.5;
//                    }
//                    if(next.contains("!")){
//                        positive = positive + 0.5;
//                    }
//                }
//                else if(isPositive(next)){
//                    negative++;
//                    negatives.add("not " + next);
//                    if(isInCaps(next)){
//                        negative = negative + 0.5;
//                    }
//                    if(next.contains("!")){
//                        negative = negative + 0.5;
//                    }
//                    if(next.contains("?")){
//                        negative = negative + 0.25;
//                    }
//                }
//            }
//        }
//    }
//
//    public void pattern1(){
//        for (int i = 0; i < words.size(); i++) {
//            String word = words.get(i);
//            String next = words.get(i);
//            if(!isNeutral(word) && isNeutral(next)){
//                if(isPositive(word)){
//                    positive++;
//                    positives.add(word);
//                    if(isInCaps(word)){
//                        positive = positive + 0.5;
//                    }
//                    if(next.contains("!")){
//                        positive = positive + 0.5;
//                    }
//                }
//                else if(isNegative(word)){
//                    negative++;
//                    negatives.add(word);
//                    if(isInCaps(next)){
//                        negative = negative + 0.5;
//                    }
//                    if(next.contains("!")){
//                        negative = negative + 0.5;
//                    }
//                    if(next.contains("?")){
//                        negative = negative + 0.25;
//                    }
//                }
//            }
//        }
//    }


    public boolean isNegative(String word) {
        word = word.toLowerCase();
        for (int i = 0; i < globalNegatives.size(); i++) {
            String neg = globalNegatives.get(i);
            if(neg.equals(word)) {
                return true;
            }
        }
        return false;
    }

    public boolean isPositive(String word) {
        word = word.toLowerCase();
        for (int i = 0; i < globalPositives.size(); i++) {
            String pos = globalPositives.get(i);
            if(pos.equals(word)) {
                return true;
            }
        }
        return false;
    }

    public boolean isNeutral(String word){
        word = word.toLowerCase();
        if(!isPositive(word) && !isNegative(word)){
            return true;
        }
        System.out.println(word);
        return false;
    }

    public boolean isInCaps(String word){
        int count = 0;
        for(int j = 0; j< capitalAlphabet.size(); j++ ){
            String alphabetLetter = capitalAlphabet.get(j);
            if(word.contains(alphabetLetter)){
                count++;
            }
        }
        if(count == word.length()){
            return true;
        }
        return false;
    }

    private ArrayList<String> getCapitalCharcsList(){
        ArrayList<String> capitalAlphabet = new ArrayList<>();

        capitalAlphabet.add("A");
        capitalAlphabet.add("B");
        capitalAlphabet.add("C");
        capitalAlphabet.add("D");
        capitalAlphabet.add("E");
        capitalAlphabet.add("F");
        capitalAlphabet.add("G");
        capitalAlphabet.add("H");
        capitalAlphabet.add("I");
        capitalAlphabet.add("J");
        capitalAlphabet.add("K");
        capitalAlphabet.add("L");
        capitalAlphabet.add("M");
        capitalAlphabet.add("N");
        capitalAlphabet.add("O");
        capitalAlphabet.add("P");
        capitalAlphabet.add("Q");
        capitalAlphabet.add("R");
        capitalAlphabet.add("S");
        capitalAlphabet.add("T");
        capitalAlphabet.add("U");
        capitalAlphabet.add("V");
        capitalAlphabet.add("W");
        capitalAlphabet.add("X");
        capitalAlphabet.add("Y");
        capitalAlphabet.add("Z");
        capitalAlphabet.add("'");

        return capitalAlphabet;

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

    public ArrayList<String> getCharcsList(){ //should be here?
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

    public  double calculateIntensity(){
        double[] np = countingPositiveNegative(words);
        double neg = np[0];
        double pos = np[1];
        double negPercentage = (neg/words.size()) *100;
        double posPercentage = (pos/words.size()) *100;
        double intense = (posPercentage/(negPercentage+posPercentage)*100);
        return intense;
    }

    public double[] countingPositiveNegative(ArrayList<String> words){
        double negative = 0;
        double positive = 0;
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            if(!isNeutral(word)){
                if(i > 0 && i + 1 < words.size()) {
                    String next = words.get(i + 1);
                    String prev = words.get(i - 1);
                    if (!isNeutral(next)) {
                        int count = 1;
                        while (!isNeutral(next)) {
                            count++;
                            next = words.get(i + count);
                        }
                        if (isPositive(next)) positive++;
                        if (isNegative(next)) negative++;
                    }
                    else{
                        if(isNegative(word)){
                            if(prev.equals("not"))positive++;
                            else negative++;
                        }
                        if(isPositive(word)){
                            if(prev.equals("not"))negative++;
                            else positive++;
                        }
                    }
                }
            }
        }
        double[] negpos = {negative, positive};
        return negpos;
    }


}

//if (!isNeutral(word)) {
//                int count = 1;
//                if (i + 1 < words.size()) {
//                    int index = i + count;
//                    String next;
//                    next = words.get(index);
//                    if (!isNeutral(next)) {
//                        String lastWord = next;
//                        System.out.println(lastWord);
//                        while (!isNeutral(lastWord)) {
//                            count = count + 1;
//                            lastWord = words.get(i + count);
//                        }
//                        if (isPositive(lastWord)) {
//                            positive++;
//                            positives.add(lastWord);
//                            if (isInCaps(lastWord)) {
//                                positive = positive + 0.5;
//                            }
//                            if (next.contains("!")) {
//                                positive = positive + 0.5;
//                            }
//                        }
//                        if (isNegative(lastWord)) {
//                            negative++;
//                            negatives.add(lastWord);
//                            if (isInCaps(lastWord)) {
//                                negative = negative + 0.5;
//                            }
//                            if (next.contains("!")) {
//                                negative = negative + 0.5;
//                            }
//                            if (next.contains("?")) {
//                                negative = negative + 0.25;
//                            }
//                        }
//                    }
//                }
//           // }
//            }






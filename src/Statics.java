import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Locale;

public class Statics {
    static ArrayList<String> pun = createPun();
    public  static ArrayList<String> createPun(){
        ArrayList<String> punctuation = new ArrayList<>();
        punctuation.add("'");
        punctuation.add(";");
        punctuation.add("/");
        punctuation.add(":");
        punctuation.add(".");
        punctuation.add("!");
        punctuation.add("?");
        return punctuation;
    }

    public static ArrayList<String> splitIntoWords(ArrayList<String> sentences) {
        ArrayList<String> words = new ArrayList<>();
        for (String sentence : sentences) {
            String[] wordies = sentence.split(" ");
            for (int i = 0; i < wordies.length; i++) {
                for (int j = 0; j < pun.size(); j++) {
                    String wordi = wordies[i];
                    String puni = pun.get(j);
                    if(wordi.contains(puni)){
                        wordi.replace(puni, "");
                        for (String word : wordies) {
                            words.add(word);
                        }
                    }
                }
            }
        }
        return words;
    }

    public static ArrayList<String> splitIntoSentences(String text){

        ArrayList<String> output = new ArrayList<>();
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
}

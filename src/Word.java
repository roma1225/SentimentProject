public class Word {
    String word;
    long freq;
    public Word(String word, long frequency){
        this.freq = frequency;
        this.word = word;
    }
    public String getWord(){
        return word;
    }
    public long getFreq(){
        return freq;
    }
    public void setWord( String nWord){
        this.word = nWord;
    }
    public void setFreq(long nFreq){
        this.freq = nFreq;
    }

}

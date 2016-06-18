package com.nguyenhoang.khanhduy.letslearnenglish;

/**
 * Created by KhanhDuy on 31/05/2016.
 */
public class Word {
    String word, pron, mean, meanEx, examp, exampEx;

    public String get(String attribute){
        switch (attribute.toLowerCase()){
            case "word": return getWord();
            case "pron": return getPron();
            case "mean": return getMean();
            case "meanex": return getMeanEx();
            case "examp": return getExamp();
            case "exampEx": return getExampEx();
        default:
            return null;
        }
    }

    public void set(String attribute, String value){
        switch (attribute.toLowerCase()){
            case "word": setWord(value); break;
            case "pron": setPron(value); break;
            case "mean": setMean(value); break;
            case "meanex": setMeanEx(value); break;
            case "examp": setExamp(value); break;
            case "exampEx": setExampEx(value); break;
            default:
                return;
        }
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPron() {
        return pron;
    }

    public void setPron(String pron) {
        this.pron = pron;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getMeanEx() {
        return meanEx;
    }

    public void setMeanEx(String meanEx) {
        this.meanEx = meanEx;
    }

    public String getExamp() {
        return examp;
    }

    public void setExamp(String examp) {
        this.examp = examp;
    }

    public String getExampEx() {
        return exampEx;
    }

    public void setExampEx(String exampEx) {
        this.exampEx = exampEx;
    }
}

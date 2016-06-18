package Data;

import java.util.ArrayList;

/**
 * Created by KhanhDuy on 30/05/2016.
 */
public class Question {
    String ques;
    ArrayList<String> ans;
    int correct = -2;

    public Question(String ques, ArrayList<String> ans, String cor){
        this.ques = ques;
        this.ans = ans;
        this.correct = (cor.charAt(1) - '0');
    }

    public String getQuestion(){
        return ques;
    }

    public String getAns(int index){
        return ans.get(index);
    }

    public boolean isCorrect(int option){
        return correct == option;
    }
    public int getCorrectAns(){
        return correct;
    }
}

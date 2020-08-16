import java.util.*;
import java.util.Random;
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovModel extends AbstractMarkovModel {

    public int model;

    public MarkovModel(int model) {
        myRandom = new Random();
        this.model = model;
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }
    
    public void setModel(int model){
        this.model = model;
    }

    public String getRandomText(int numChars){
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length()-model);
        String key = myText.substring(index, index+model);
        sb.append(key);
        for(int k=0; k < numChars-model; k++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size() == 0){
                break;
            }
            
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1)+next;
        }
        return sb.toString();
    }
    
    public String toString(){
        return "MarkovModel of order "+model;
    }

    
}

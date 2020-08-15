import java.util.*;
import java.util.Random;
/**
 * Write a description of MarkovFour here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovFour {
    private String myText;
    private Random myRandom;

    public MarkovFour() {
        myRandom = new Random();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public String getRandomText(int numChars){
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length()-4);
        String key = myText.substring(index, index+4);
        sb.append(key);
        for(int k=0; k < numChars-4; k++){
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

    public ArrayList<String> getFollows(String key){
        ArrayList<String> result = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length()){
            int index = myText.indexOf(key, pos);
            if(index == -1){
                break; //not found
            }
            if(index+key.length() >= myText.length()){
                break;
            }
            result.add(myText.substring(index+key.length(), index+key.length()+1));
            pos = index + key.length();
        }
        /*
        String[] trainedText = myText.split("");
        for(int i = 0; i<trainedText.length-1; i++){
        if(trainedText[i].equals(key)){
        result.add(trainedText[i+1]);
        }
        }
         */
        return result;
}
}
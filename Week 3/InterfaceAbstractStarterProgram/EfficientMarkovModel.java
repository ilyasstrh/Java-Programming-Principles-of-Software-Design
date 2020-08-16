import java.util.*;
import java.util.Random;
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EfficientMarkovModel extends AbstractMarkovModel {
    private int model;
    private HashMap<String, ArrayList<String>> hashMap;

    public EfficientMarkovModel(int model) {
        myRandom = new Random();
        this.model = model;
        hashMap = new HashMap<String, ArrayList<String>>();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
        buildMap();
        //printHashMapInfo();
    }

    public void setModel(int model){
        this.model = model;
    }

    public String getRandomText(int numChars){
        if (myText == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - model);
        String key = myText.substring(index, index + model);
        sb.append(key);
        for(int k=0; k < numChars - model; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(key.length() - (model - 1)) + next;
        }
        return sb.toString();
    }

    private void buildMap() {
        for (int pos = 0; pos <= myText.length() - model; pos++) {
            int subEnd = pos + model;
            String sub = myText.substring(pos, subEnd);
            if (!hashMap.containsKey(sub)) {
                hashMap.put(sub, new ArrayList<String>());
            }
            if (subEnd < myText.length()) {
                String follower = myText.substring(subEnd, subEnd + 1);
                ArrayList<String> followers = hashMap.get(sub);
                followers.add(follower);
                hashMap.put(sub, followers);
            }
        }
    }
/*
    public void printHashMapInfo() {        
        System.out.println("Number of keys: " + hashMap.size());
        
        int largestSize = 0; 
        for (String key : hashMap.keySet()) {
            int keySize = hashMap.get(key).size();
            if (keySize > largestSize) {
                largestSize = keySize;
            }
        }
        System.out.println("Largest size: " + largestSize);
        
        System.out.println("max key value:");
        for (String key : hashMap.keySet()) {
            if (hashMap.get(key).size() == largestSize) {
                System.out.println(key);
            }
        }
        
        System.out.println("\n");
    }
    */
    public ArrayList<String> getFollows(String key) {
        return hashMap.get(key);
    }
    
    public String toString(){
        return "MarkovModel of model "+model;
    }

}

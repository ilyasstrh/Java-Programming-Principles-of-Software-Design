
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;

    public MarkovWord(int myOrder) {
        myRandom = new Random();
        myOrder = myOrder;
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            /*
            System.out.println("Followers of the key: "+key+" "+follows);
             */
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }

        return sb.toString().trim();
    }

    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int start = 0;
        while(true){
            start = indexOf(myText,kGram,start);
            if(start == -1){
                break;
            }

            if(start >= myText.length-1){
                break;
            }

            follows.add(myText[start+myOrder]);
            start = start + myOrder;
        }
        return follows;
    }

    private int indexOf(String[] words, WordGram target, int start){
        //List<String> listOfWords = Arrays.asList(words);
        for(int i=start; i<words.length - myOrder; i++){
            WordGram word = new WordGram(words, i, myOrder);
            if(words.equals(target)){
                return i;
            }
        }
        return -1;
    }

    public void testIndexOf(){
        String[] words = "this is just a test yes this is a simple test".split(" ");
        /*
        int ex1 = indexOf(words, "this", 0);
        System.out.println(ex1);
        int ex2 = indexOf(words, "this", 3);
        System.out.println(ex2);
        int ex3 = indexOf(words, "frog", 0);
        System.out.println(ex3);
        int ex4 = indexOf(words, "frog", 5);
        System.out.println(ex4);
        int ex5 = indexOf(words, "simple", 2);
        System.out.println(ex5);
        int ex6 = indexOf(words, "test", 5);
        System.out.println(ex6);
         */

    }

}

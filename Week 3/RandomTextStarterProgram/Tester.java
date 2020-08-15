import java.util.*;
import edu.duke.*;
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tester {
    public void testGetFollows(){
        MarkovOne markovOne = new MarkovOne();
        markovOne.setTraining("this is a test yes this is a test.");
        System.out.println(markovOne.getFollows("."));
    }
    
    public void testGetFollowsWithFile(){
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovOne markov = new MarkovOne();
		//markov.setRandom(42);
		markov.setTraining(st);
		System.out.println(markov.getFollows("t").size());
    }
}

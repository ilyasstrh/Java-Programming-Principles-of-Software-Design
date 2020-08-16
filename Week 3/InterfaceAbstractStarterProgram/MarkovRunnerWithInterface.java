
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        long start = System.nanoTime();
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
        long end = System.nanoTime();
        long totalTime = end - start;
        System.out.println("Total time: "+totalTime);
    }

    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        int seed = 6;

        MarkovZero mz = new MarkovZero();
        mz.toString();
        runModel(mz, st, size, seed);

        MarkovOne mOne = new MarkovOne();
        mOne.toString();
        runModel(mOne, st, size, seed);

        MarkovModel mThree = new MarkovModel(3);
        mThree.toString();
        runModel(mThree, st, size, seed);

        MarkovFour mFour = new MarkovFour();
        mFour.toString();
        runModel(mFour, st, size, seed);

    }
    public void testHashMap(){
        String st = "yes­this­is­a­thin­pretty­pink­thistle";
        int size = 50;
        int seed = 42;

        EfficientMarkovModel ef = new EfficientMarkovModel(2);
        ef.toString();
        runModel(ef, st, size, seed);
    }
    
    public void compareMethods(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 1000;
        int seed = 42;

        MarkovModel mm = new MarkovModel(2);
        mm.toString();
        runModel(mm, st, size, seed);
        
        EfficientMarkovModel ef = new EfficientMarkovModel(2);
        ef.toString();
        runModel(ef, st, size, seed);
        
    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }

}

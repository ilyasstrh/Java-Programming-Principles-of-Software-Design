import java.lang.*;
import java.util.Comparator;
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2){
        String q1End = q1.getInfo().substring(q1.getInfo().lastIndexOf(" ")+1);
        String q2End = q2.getInfo().substring(q2.getInfo().lastIndexOf(" ")+1);
        if(q1End.compareTo(q2End) == 0){
            return Double.compare(q1.getMagnitude(),q2.getMagnitude());
        }
        
        
        return q1End.compareTo(q2End);
    }
}

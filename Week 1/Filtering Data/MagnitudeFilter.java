
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter{
    
    private double minMag;
    private double maxMag;
    private String name;
    
    public MagnitudeFilter(double minMag, double maxMag){
        this.minMag = minMag;
        this.maxMag = maxMag;
        this.name = "Magnitude";
    }
    
    @Override
    public  boolean satisfies(QuakeEntry qe){
        if(minMag <= qe.getMagnitude() && qe.getMagnitude() <= maxMag){
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    public String getName(){
        return name;
    }
    

}

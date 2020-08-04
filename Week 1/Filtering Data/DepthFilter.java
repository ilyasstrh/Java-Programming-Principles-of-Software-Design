
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter {
    private double minDepth;
    private double maxDepth;
    
    public DepthFilter(double minDepth, double maxDepth){
        this.minDepth = minDepth;
        this.maxDepth = maxDepth;
    }
    
    @Override
    public  boolean satisfies(QuakeEntry qe){
        if(minDepth <= qe.getDepth() && qe.getDepth() <= maxDepth){
            return true;
        }else{
            return false;
        }
    }
}

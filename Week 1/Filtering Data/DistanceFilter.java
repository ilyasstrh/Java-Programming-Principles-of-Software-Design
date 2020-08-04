
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {
    private Location location;
    private double maxDistance;
    
    public DistanceFilter(Location location, double maxDistance){
        this.location = location;
        this.maxDistance = maxDistance;
    }
    
    @Override
    public  boolean satisfies(QuakeEntry qe){
        Location currentLocation = qe.getLocation();
        double distance = location.distanceTo(currentLocation);
        if(distance <= maxDistance){
            return true;
        }else{
            return false;
        }
    }

}

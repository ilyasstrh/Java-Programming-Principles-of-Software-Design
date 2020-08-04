
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {
    private Location location;
    private double maxDistance;
    private String name;
    
    public DistanceFilter(Location location, double maxDistance){
        this.location = location;
        this.maxDistance = maxDistance;
        this.name = "Distance";
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
    
    @Override
    public String getName(){
        return name;
    }

}

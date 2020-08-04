import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        /*
        Filter f = new MagnitudeFilter(4.0, 5.0); 
        ArrayList<QuakeEntry> m7  = filter(list, f);
        //new filter
        f = new DepthFilter(-35000.0, -12000.0);
        m7 = filter(m7, f); // filter by depth
        */
        Location location = new Location(35.42 ,139.43);
        Filter f = new DistanceFilter(location, 10000000.00); 
        ArrayList<QuakeEntry> m7  = filter(list, f);
        //new filter
        f = new PhraseFilter("end", "Japan");
        m7 = filter(m7, f); // filter by depth
       
        
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        } 
    }
    
    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        /*
        for(QuakeEntry qe : list){
            System.out.println(qe);
        }
        */
        MatchAllFilter maf = new MatchAllFilter();
        Filter magFilter = new MagnitudeFilter(0.0, 2.0);
        Filter depFilter = new DepthFilter(-100000.0, -10000.0);
        Filter prFilter = new PhraseFilter("any", "a");
        
        maf.addFilter(magFilter);
        maf.addFilter(depFilter);
        maf.addFilter(prFilter);
        
        //Now we will apply all the filters to our list
        list = filter(list, maf);
        
        //Print our list
        for(QuakeEntry qe : list){
            System.out.println(qe);
        }
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}

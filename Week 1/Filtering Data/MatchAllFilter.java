import java.util.*;
public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filters;
    
    //Constructor
    public MatchAllFilter(){
        filters = new ArrayList<Filter>();
    }
    
    public void addFilter(Filter filter){
        filters.add(filter); //add the filter to filters ArrayList
    }
    
    @Override
    public  boolean satisfies(QuakeEntry qe){
        for(Filter f : filters){
            if(!f.satisfies(qe)){
                return false;
            }
        }
        return true;
    }
    
    @Override
    public String getName(){
        String names = "";
        for(Filter f : filters){
            //add the name of each filter to names
            names += " "+f.getName();
  
        }
        return names;
    }


}

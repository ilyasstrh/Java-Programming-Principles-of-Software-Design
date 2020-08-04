
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
    private String typeOfRequest;
    private String phraseToSearch;
    private String name;
    
    public PhraseFilter(String typeOfRequest, String phraseToSearch){
        this.typeOfRequest = typeOfRequest;
        this.phraseToSearch = phraseToSearch;
        this.name = "Phrase";
    }
    
    @Override
    public  boolean satisfies(QuakeEntry qe){
        String title = qe.getInfo();
        if(typeOfRequest.equals("start") && title.startsWith(phraseToSearch)){
            return true;
        }
        else if(typeOfRequest.equals("end") && title.endsWith(phraseToSearch)){
            return true;
        }
        else if(typeOfRequest.equals("any") && title.contains(phraseToSearch)){
            return true;
        }
        else{
            return false;
        }
    }
    
    @Override
    public String getName(){
        return name;
    }

}

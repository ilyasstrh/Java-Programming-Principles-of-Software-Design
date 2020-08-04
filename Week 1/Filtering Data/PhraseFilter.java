
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
    private String typeOfRequest;
    private String phraseToSearch;
    
    public PhraseFilter(String typeOfRequest, String phraseToSearch){
        this.typeOfRequest = typeOfRequest;
        this.phraseToSearch = phraseToSearch;
    }
    
    @Override
    public  boolean satisfies(QuakeEntry qe){
        String title = qe.getInfo();
        if(typeOfRequest.equals("start") && title.contains(phraseToSearch)){
            return true;
        }
        else if(typeOfRequest.equals("end") && title.contains(phraseToSearch)){
            return true;
        }
        else if(typeOfRequest.equals("any") && title.contains(phraseToSearch)){
            return true;
        }
        else{
            return false;
        }
    }

}
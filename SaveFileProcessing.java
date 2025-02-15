import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

class SaveFileProcessing{
    private String inputFileLocation;
    private ArrayList<String> tokens = new ArrayList<>();
    private String question;
    private String type;
    private ArrayList<String> answers = new ArrayList<>();

    public SaveFileProcessing(){
    }

    private void sortInputs(){

    }

    public void scanInput() throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(inputFileLocation));
        String line;

        while((line = br.readLine())!= null){
            
            
            
        }
    }


}

/*File format:
    Question: 
        ...
        ...

    QuestionType: 
        (single/multi ans, multiple choice (has no functionality until further notice))
        ...

    Answers: 
        ...+ , ...* 
        ...
        ...
    
    
    Note: 
        - every new line is something from a different question/key
        - 
    Goal: Process the save file and return a hashtable with <list: question, questiontype, answers | key>

*/

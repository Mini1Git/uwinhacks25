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
        String state = "INITIAL";

        while((line = br.readLine())!= null){
            switch(state){
                case "INITIAL":
                    state = line.trim().split(":")[0];
                    
                break;
                
                case "QUESTION":
                    
                break;
                
                case "TYPE":
                    
                break;

                case "ANSWERS":

                break;

                case "DEFAULT":

                break;
            }      
        }
    }


}

/*File format:
    QUESTION: 
        ...
        ...

    TYPE: 
        (single/multi ans, multiple choice (has no functionality until further notice))
        ...

    ANSWERS: 
        ...+ , ...* 
        ...
        ...
    
    REWARDMOD:
        ([0-9],?)+
    
    
    Note: 
        - every new line is something from a different question/key
        - reward modifier will multiply by the base point reward
    Goal: Process the save file and return a hashtable with <list: question, questiontype, answers | key>

*/

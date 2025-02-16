import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.TreeMap;

class SaveFileProcessing{
    private String inputFileLocation;
    private int index = 0;
    private TreeMap<List<String>, int> map = new HashMap<>();

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
                    this.map.entrySet(new List<String>(), ++index);
                break;
                
                case "QUESTION":
    
                break;
                
                case "TYPE":
                    
                break;

                case "ANSWERS":

                break;

                case "DEFAULT":
                    System.err.println("WARNING! UNKNOWN STATE EXCEPTION DETECTED!");
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
        - second new line indicates that the section has ended.
        - reward modifier will set the answer to the desired points. If empty it will be indicated by - and will be empty
    Goal: Process the save file and return a hashtable with <list: question, questiontype, answers | key>

*/

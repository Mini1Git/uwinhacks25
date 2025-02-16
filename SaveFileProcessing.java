import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

class SaveFileProcessing{
    private final String inputFileLocation;
    private int index = 0;
    private HashMap<Integer, ArrayList<String>> map = new HashMap<>();

    public SaveFileProcessing(String file){
        this.inputFileLocation = file; 
    }

    private void sortInputs(){
    }

    public HashMap<Integer, ArrayList<String>> returnMap(){
        return map;
    }
    
    public void scanInput() throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(inputFileLocation));
        String line;
        String state = "INITIAL";
        ArrayList<String> arrList = new ArrayList<>(); //since java kept fucking complaining about it being potentially not initalized despite it being FORCED to initalize on startup. Thanks java

        while((line = br.readLine())!= null){
            switch(state){
                case "INITIAL":
                    state = line.trim().split(":")[0];
                    
                break;
                
                case "QUESTION":
                    if(line.trim().equals("\n")){
                        state = "INITIAL"; 
                        break;
                    }

                    map.put(++index, new ArrayList<String>());
                    map.get(index).add(line);
                break;
                
                case "TYPE":
                    if(line.trim().equals("\n")){
                        state = "INITIAL"; 
                        break;
                    }

                    map.put(++index, new ArrayList<String>());
                    map.get(index).add(line);
                break;

                case "ANSWERS":
                    if(line.trim().equals("\n")){
                        state = "INITIAL"; 
                        break;
                    }

                    map.put(++index, new ArrayList<>());
                    String[] answers = line.split(", ");

                    for(String token : answers){
                        map.get(index).add(token);
                    }
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

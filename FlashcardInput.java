import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

class FlashcardInput{
    private String inputFileLocation;
    private ArrayList tokens;

    public FlashcardInput(){
    }

    private void sortInputs(){

    }

    public void scanInput() throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(inputFileLocation));
        String line;
        while((line = br.readLine())!= null){
            String[] token = line.trim().split(": ");



        }
    }


}

/*File format:
    Question: ....
    QuestionType: (Single Answer / Multiple Choice)
    Answers: ...+ , ...*
    


*/

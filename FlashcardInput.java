import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

class FlashcardInput{
    private String inputFileLocation;

    public FlashcardInput(){
    }

    private void sortInputs(){

    }

    public void scanInput() throws FileNotFoundException{
        BufferedReader br = new BufferedReader(new FileReader(inputFileLocation));
        while(br.nextLine()!=null){
            
        }
    }


}

/*File format:
    Question: ....
    QuestionType: (Single Answer / Multiple Choice)
    Answers: ...+ , ...*
    


*/

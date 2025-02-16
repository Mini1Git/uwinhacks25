import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
class FlashCard {
    private String Question;
    private int nOptions;
    //this boolean is here to check if user answers in the required time. 
    public static boolean answered;
    List<String> listofOptions = Arrays.asList("a", "b", "c", "d");

    private HashMap<Integer, ArrayList<String>> answerMap;

    
    public FlashCard(){
    }

    public FlashCard(String text, int numOptions, String correctAnswer){
        this.Question = text;
        if (numOptions > 4){
            System.out.println("TOO MANY OPTIONS, PLEASE TRY AGAIN"); //maybe take off this limit if we have time
            System.exit(0);
        }
        nOptions = numOptions;
    }
    
    /*
    //testing constructor
    //flash card and also states the rules, aka how many options for the one question. // up to 4 options for each question.
    */
    
    public String getQuestion() {
        return this.Question;
    }

    public void setQuestion(String text) {
        this.Question = text;
    }

    // now do we want a seperate iption java file, to handle tohte optiomns logic, or do we cuz i was thinking we coauld use this  function to check which is the coererct answer. 
    public void correctAnswer(){
        for (int i = 0; i <= nOptions; i++){
            //for every option, if option = string in FlashCard(...,...,String correctAnswer), make that crorrect. or maybe retunr that???
            //basically, this will work whether the user needs 1,2,3, or 4 options.
        }
    }
    
    //Needed to display the text of all Options
    public void displayText(){
        //this could be implemented with Nash's UI stuff later.
    }

    // Calculates the points
    public void CalcPoints(){
        //we need to basically calculae this wiht the timer. oOOH, how about in the timer class, we handle the point logic, and we give the points to a player class. 
        //like, in Timer.java, we can set the timer, but also an interval for ex, timer sets to 10 seconds, but lose one point every 2 secs, but if player gets it right within 4 secs, will only lose 2 points. and if can't answer/answer wrong, get 0.
        
    }

    private void fetchSaveInfo(String fileLocation){
        SaveFileProcessing sp = new SaveFileProcessing(fileLocation);
        this.answerMap = sp.returnMap();
    }

    public void Play() {
        //Test timer
        Timer time = new Timer(10, 10, 2); // instead of having the points linked to the timer, points+timer earned for each question would technically be the same, so i feel like a MASTER class could set these, like a manager class or smt. 
        Thread t1 = new Thread(time); //so basically, 10 sec timer, total points you can get is 10, every 2 seconds that passes it decreases points by 1.
        t1.start();

        //Fetch data on randomized index
        int randKey = 1;
        ArrayList<String> fetchList = this.answerMap.get(randKey);

        //flashcard creation
        FlashCard question = new FlashCard(fetchList.get(0), 4, fetchList.get(2));
        question.displayText();
        //NOTE: IMPLEMENT WHERE MULTIPLE FLASHCARDS CAN BE USED, WITH MULTIPLE ANSWERS

        

        //should do the choosing in here. this entire block should be dedicated to when the user clicks a button.
        System.out.println("Choose an option.\n=");
        Scanner option = new Scanner(System.in);
        String chose = option.nextLine();
        FlashCard.answered = true;
        System.out.println("You earned " + time.points + " points!");
        //where should logic for options be?
        
        

    }
}
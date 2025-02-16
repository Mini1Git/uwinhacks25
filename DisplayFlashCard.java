public class DisplayFlashCard{
    String question;

    public DisplayFlashCard(String question){
        this.question = question;
    }

    //Displays the question
    public void displayText(){
        System.out.println(question + "?");
    }
}

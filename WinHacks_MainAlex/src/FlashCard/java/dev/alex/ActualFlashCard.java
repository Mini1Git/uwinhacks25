package dev.alex;

public class ActualFlashCard{
    String question;

    public ActualFlashCard(String question){
        this.question = question;
    }

    //Displays the question
    public void displayQuestion(){
        System.out.println(question + "?");
    }
}

import java.util.Scanner;
abstract class FlashCard {
    private String text;
    private double points = 0.0;
    //this boolean is here to check if user answers in the required time. 
    public static boolean answered;

    public FlashCard(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public double getPoints() {
        return points;
    }

    //Needed to display the text of all Options
    abstract void displayText();

    // Calculates the points
    abstract void CalcPoints();

    public static void main(String[] args) {
        //Test timer
        Timer time = new Timer(5);
        Thread t1 = new Thread(time);
        t1.start();

        
        //flashcard 
        DisplayFlashCard question = new DisplayFlashCard("what color is the wind");
        question.displayText();
        OptA a = new OptA("The wind is blue");
        a.displayText();
        OptB b = new OptB("The wind is red");
        b.displayText();
        OptC c = new OptC("The wind is yellow");
        c.displayText();

        //should do the choosing in here. should make a new class for choosing?
        System.out.println("Choose an option.\n=");
        Scanner option = new Scanner(System.in);
        String chose = option.nextLine();
        FlashCard.answered = true;
        //where should logic for options be?
        System.out.println(chose);
        

    }
}
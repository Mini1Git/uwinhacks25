
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
abstract class FlashCard {
    private String text;
    private double points = 0.0;

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
        //Test stuff
        ActualFlashCard newCard = new ActualFlashCard("Testing");
    }
}
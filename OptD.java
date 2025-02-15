

public class OptD extends FlashCard{
    boolean isVisible; //Incase only option A and B are needed
    double time = 0.0;

    public OptD(String D, boolean isVisible) {
        super(D);
        this.isVisible = isVisible;
        setPoints(5.0);
    }

    //Constructor used if only String is entered (Assumes D is visable)
    public OptD(String D) {
        super(D);
        this.isVisible = true;
        setPoints(5.0);
    }

    //Used to swap between visability
    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }

    //Used for testing purposes
    private boolean isVisible(){
        return isVisible;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    //Displays Text for d
    @Override
    void displayText(){
        System.out.println("D: " + getText());
    }

    void CalcPoints(){
        setPoints(getPoints() - (time*0.2));
    }
}

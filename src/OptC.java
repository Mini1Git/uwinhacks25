

public class OptC extends FlashCard {
    // Needed to turn it invisible in case only option A and B are needed
    public boolean visible;
    public double time = 0.0;

    public OptC(String C, boolean isVis) {
        super(C);
        visible = isVis;
        setPoints(5.0);
    }

    // Constructor in case only string is entered (assumes C is visible)
    public OptC(String C) {
        super(C);
        visible = true;
        setPoints(5.0);
    }

    // Used for swapping
    public void setvisible(boolean visible) {
        this.visible = visible;
    }

    // For Dev purposes to debug
    private boolean getvisible() {
        return visible;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    //Displays text for C
    @Override
    void displayText() {
        System.out.println("C: " + getText());
    }

    void CalcPoints() {
        setPoints(getPoints() - (time*0.2));
    }
}

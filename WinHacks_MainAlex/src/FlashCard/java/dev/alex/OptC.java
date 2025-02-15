package dev.alex;

public class OptC extends FlashCard {
    // Needed to turn it invisable incase only option A and B are needed
    public boolean visable;
    public double time = 0.0;

    public OptC(String C, boolean isVis){
        super(C);
        visable = isVis;
        setPoints(5.0);
    }

    // Constructor incase only string is entered (assumes C is visable)
    public OptC(String C){
        super(C);
        visable = true;
        setPoints(5.0);
    }

    // Used for swapping
    public void setVisable(boolean visable) {
        this.visable = visable;
    }

    // For Dev purposes to debug
    private boolean getVisable() {
        return visable;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    //Displays text for C
    @Override
    void displayText(){
        System.out.println("C: " + getText());
    }

    void CalcPoints(){
        setPoints(getPoints() - (time*0.2));
    }
}

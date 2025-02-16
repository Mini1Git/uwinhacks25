public class OptB extends FlashCard {
    
	public double time = 0.0;

    public OptB(String B) {
        super(B);
        setPoints(5.0);
    }

    public void setTime(double points) {
        super.setPoints(points);
    }

    public double getTime() {
        return time;
    }

    //Displays the text of option
    @Override
    void displayText(){
        System.out.println("B: " + getText());
    }

    void CalcPoints(){
        setPoints(getPoints() - (time*0.2));
    }
}
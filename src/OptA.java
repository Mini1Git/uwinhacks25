public class OptA extends FlashCard {
    
	double time = 0.0;

    public OptA(String A) {
        super(A);
        setPoints(5.0);
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getTime() {
        return time;
    }

    //Displays the text
    @Override
    void displayText() {
        System.out.println("A: " + getText());
    }

    void CalcPoints() {
        setPoints(getPoints() - (time*0.2));
    }
}

public class Timer {
    //make the timer class, that will handle a timer that is ticking down.
    int seconds;
  
    Timer(int s){
        seconds = s;
    }
    public void countDown(){
        System.out.println(seconds);
    }

   public static void main(String[] args) {
       Timer testTimer = new Timer(5);
       testTimer.countDown();
       
   } 
}

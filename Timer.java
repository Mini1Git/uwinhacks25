
public class Timer implements Runnable{ // use implements so that maybe in future can implement points system
    //make the timer class, that will handle a timer that is ticking down.
    int seconds;
    int points;
    int interval;
    //constructor for timer.
    Timer(int sec, int points, int interval){
        seconds = sec;
        this.points = points;
        this.interval = interval;
    }
    
    
//heres how you can see how its implemented.
//    public static void main(String[] args) {
//         Timer testTimer = new Timer(5);
//         Thread t1 = new Thread(testTimer);
//         t1.start();
//    }



//this is a function that is specific to threads. doing Thread.start() in void main runs run(). 
@Override
public void run() {
    int countUp = 0;
    //while seconds is bigger than 0
   while (seconds > 0 && !FlashCard.answered){
    // System.out.println(seconds);

    try { 
        Thread.sleep(1000); 
        seconds--;
        countUp++;
    if (countUp == interval){
        points--; // so basciallly reduce the amount of points you can earn based on how long the timer has. 
        //gotta reset the countup 
        countUp = 0;
    }
    if (seconds == 0 && !FlashCard.answered){
        System.out.println("YOU FAILED TO ANSWER!");
        System.exit(0);
    }


    } //the catch statement is just there in case an error occurs.
    catch (InterruptedException e) {
        e.printStackTrace();
    }
    
   }


} 

}

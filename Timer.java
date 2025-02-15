
public class Timer implements Runnable{ // use implements so that maybe in future can implement points system
    //make the timer class, that will handle a timer that is ticking down.
    int seconds;
    //constructor for timer.
    Timer(int s){
        seconds = s;
    }
    
    
//heres how you can see how its implemented.
//    public static void main(String[] args) {
//         Timer testTimer = new Timer(5);
//         Thread t1 = new Thread(testTimer);
//         t1.start();
//    }



//this is a function that is specific to threads. doing Thread.start() in void main runs run(). You can make multiple runs if you need.
@Override
public void run() {
    //while seconds is bigger than 0
   while (seconds > 0){
    System.out.println(seconds);
    try { 
        Thread.sleep(1000); 
        seconds--;
    if (seconds == 0){
        System.out.println("BOOOOOMMMM!");
    }


    } //the catch statement is just there in case an error occurs.
    catch (InterruptedException e) {
        e.printStackTrace();
    }
    
   }


} 
}

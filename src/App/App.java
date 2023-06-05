package src.App;

import src.FloatingCircles;

public class App {
    public static void main(String[] args) {
        
        // Create two instances of Runnable objects
        FloatingCircles floatingCircles = new FloatingCircles();

        Runnable runnable1 = new Runnable(){
            @Override
            public void run(){
                floatingCircles.run();
            }
        };

        Screen screen = new Screen();
 
        Runnable runnable2 = new Runnable(){
            @Override
            public void run(){
                screen.run();
            }
        };

        // Create two threads and start them
        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        thread1.start();
        thread2.start();
        
        // Wait for both threads to finish
        try {
            thread1.join();
            thread2.join();
            
        } catch (InterruptedException e) {
            // e.printStackTrace();
        }  
        
    }
}
    

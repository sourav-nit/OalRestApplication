package oal.oracle.apps.epm.utils.Multithreading;


public class MyRunnable implements Runnable {
    
    NotThreadSafe instance = null;
    
    public MyRunnable(NotThreadSafe instance){
      this.instance = instance;
    }
    
    public void run(){
      this.instance.add("some text");
    }
    public static void main(String[] args){
        NotThreadSafe sharedInstance = new NotThreadSafe();
        MyRunnable runnable=new MyRunnable(sharedInstance);
        Thread t1=new Thread(runnable,"Thread1");
        Thread t2=new Thread(runnable,"Thread2");
        t1.start();
        t2.start();
    }
}

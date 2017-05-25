package oal.oracle.apps.epm.utils.Multithreading;

public class Lock {

    public boolean isLocked=false;
    Thread lockedBy=null;
    int lockCount=0;
    
    public synchronized void lock() throws InterruptedException {
        while(isLocked && lockedBy!=Thread.currentThread()){
            this.wait();
        }
        isLocked=true;
        ++lockCount;
        lockedBy=Thread.currentThread();
    }
    public synchronized void unlock(){
        if(lockedBy==Thread.currentThread()){
            --lockCount;
        }
        if(lockCount==0){
            isLocked=false;
            this.notify();
        }
    }  
}

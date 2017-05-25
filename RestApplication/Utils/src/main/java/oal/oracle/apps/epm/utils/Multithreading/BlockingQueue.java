package oal.oracle.apps.epm.utils.Multithreading;

import java.util.LinkedList;
import java.util.List;

public class BlockingQueue {
    private List Queue=new LinkedList();
    private int limit;
    public BlockingQueue(int limit) {
        this.limit=limit;
    }
    public synchronized void add(int data) throws InterruptedException {
        while(this.Queue.size()==limit) wait();
        if(this.Queue.size() == 0){
        notifyAll();
        }
        Queue.add(data);
    }
    public synchronized void release() throws InterruptedException {
        while(this.Queue.size()==0) wait();
        if(this.Queue.size() == this.limit){
        notifyAll();
        }
        Queue.remove(0);

    }
}

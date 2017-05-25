package oal.oracle.apps.epm.utils.Multithreading;

public class NotThreadSafe {
    StringBuilder builder = new StringBuilder();

    public void add(String text){
        System.out.println(Thread.currentThread()+"Before-"+this.builder.toString());
        this.builder.append(text);
        System.out.println(Thread.currentThread()+"After-"+this.builder.toString());
    }
}

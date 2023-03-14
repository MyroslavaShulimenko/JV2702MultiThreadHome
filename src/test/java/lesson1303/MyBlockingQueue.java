package lesson1303;

import java.util.LinkedList;
import java.util.Queue;

public class MyBlockingQueue<T> {
    private Queue<T>queue=new LinkedList<>();

    public void put(T item){
        synchronized (queue){
        queue.add(item);
        queue.notify();}
    }
    public T teke(){
        synchronized (queue){
            while (queue.isEmpty()){
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
return queue.poll();}
    }
}

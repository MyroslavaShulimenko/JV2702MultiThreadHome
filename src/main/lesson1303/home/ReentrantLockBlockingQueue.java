package lesson1303.home;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockBlockingQueue<T> {

        private Queue<T> queue = new ArrayDeque<>();
        private ReentrantLock lock = new ReentrantLock(true);
        private Condition condition = lock.newCondition();
private int size=0;
    public ReentrantLockBlockingQueue(int size) {
        this.size = size;
    }
        public void put(T item) {
        while (queue.size()<=size){
            lock.lock();
            try {
                queue.add(item);
                condition.signal();
            } finally {
                lock.unlock();
            }
        }}

        public T take(){
            lock.lock();
            try {
                while (queue.isEmpty()){
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return queue.poll();
            } finally {
                lock.unlock();
            }

        }

        public int getSize(){
            lock.lock();
            try {
                return queue.size();
            } finally {
                lock.unlock();
            }
        }


    }


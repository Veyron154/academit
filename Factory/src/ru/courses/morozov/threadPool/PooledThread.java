package ru.courses.morozov.threadPool;

import java.util.Queue;

public class PooledThread extends Thread{
    private Queue<Runnable> taskQueue;
    private final Object lock;

    public PooledThread(Queue<Runnable> taskQueue, Object lock){
        this.taskQueue = taskQueue;
        this.lock = lock;
    }

    public void run() {
        while (true) {
            synchronized (lock) {
                while (taskQueue.isEmpty()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                Runnable toExecute = taskQueue.remove();
                toExecute.run();
            }
        }
    }
}

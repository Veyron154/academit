package ru.courses.morozov.threadPool;

import java.util.LinkedList;
import java.util.Queue;

public class ThreadPool {
    private Queue<Runnable> taskQueue;
    private final Object lock;

    public ThreadPool (int poolSize){
        this.taskQueue = new LinkedList<>();
        this.lock = new Object();

        for (int i = 0; i < poolSize; ++i){
            PooledThread pooledThread = new PooledThread(taskQueue, lock);
            pooledThread.start();
        }
    }

    public void addTask (Runnable task){
        synchronized (lock) {
            taskQueue.add(task);
            lock.notifyAll();
        }
    }
}

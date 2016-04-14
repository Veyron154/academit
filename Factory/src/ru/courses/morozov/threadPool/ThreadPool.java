package ru.courses.morozov.threadPool;

import java.util.LinkedList;
import java.util.Queue;

public class ThreadPool {
    private Queue<Runnable> taskQueue;
    private final Object lock;

    public ThreadPool(int poolSize) {
        this.taskQueue = new LinkedList<>();
        this.lock = new Object();

        for (int i = 0; i < poolSize; ++i) {
            PooledTask pooledTask = new PooledTask(this);
            Thread newThread = new Thread(pooledTask);
            newThread.start();
        }
    }

    public void addTask(Runnable task) {
        synchronized (lock) {
            taskQueue.add(task);
            lock.notifyAll();
        }
    }

    public Runnable getTask() throws InterruptedException {
        synchronized (lock) {
            while (taskQueue.isEmpty()) {
                lock.wait();
            }
        }
        return taskQueue.remove();
    }
}

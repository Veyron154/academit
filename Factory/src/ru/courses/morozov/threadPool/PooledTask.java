package ru.courses.morozov.threadPool;

public class PooledTask implements Runnable {
    private ThreadPool threadPool;

    public PooledTask(ThreadPool threadPool) {
        this.threadPool = threadPool;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                threadPool.getTask().run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

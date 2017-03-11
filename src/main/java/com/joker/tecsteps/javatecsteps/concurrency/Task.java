package com.joker.tecsteps.javatecsteps.concurrency;

/**
 * Created by joker on 17-3-6.
 */
public class Task implements Runnable {
    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;
    public String status(){
        return "#" + id +"("+(countDown >0 ? countDown : "liftoff!") +"),";
    }
    public void run() {
        while (countDown-- > 0){
            System.out.print(status());
            Thread.yield();
        }

    }
}

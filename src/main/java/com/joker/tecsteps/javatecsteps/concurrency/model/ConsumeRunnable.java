package com.joker.tecsteps.javatecsteps.concurrency.model;

/**
 * Created by joker on 17-3-11.
 */
public class ConsumeRunnable implements Runnable{
    private IStorage storage;
    private int num;
    public ConsumeRunnable(IStorage storage ,int num){
        this.storage = storage;
        this.num = num;
    }

    public void run() {
        this.storage.consume(num);
    }
}

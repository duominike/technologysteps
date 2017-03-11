package com.joker.tecsteps.javatecsteps.concurrency.model;

/**
 * Created by joker on 17-3-11.
 */
public class ProduceRunnable implements Runnable{
    private IStorage storage;
    private int num;
    public ProduceRunnable(IStorage storage ,int num){
        this.storage = storage;
        this.num = num;
    }

    public void run() {
        this.storage.produce(num);
    }
}

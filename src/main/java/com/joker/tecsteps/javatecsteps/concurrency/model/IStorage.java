package com.joker.tecsteps.javatecsteps.concurrency.model;

/**
 * Created by joker on 17-3-11.
 */
public interface IStorage {
     void produce(int num);
     void consume(int num);
}

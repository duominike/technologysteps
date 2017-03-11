package com.joker.tecsteps.javatecsteps.concurrency.model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by joker on 17-3-11.
 */
public class Restaurant {
     WaitPerson waitPerson = new WaitPerson(this);
     Chief chief = new Chief(this);
     Meal meal;
     ExecutorService executor = Executors.newCachedThreadPool();
    public Restaurant(){
        executor.execute(chief);
        executor.execute(waitPerson);
    }

    public static class Meal{
        private final int orderNum;
        public Meal(int orderNum){
            this.orderNum = orderNum;
        }

        @Override
        public String toString() {
            return "Meal " + orderNum;
        }
    }
}

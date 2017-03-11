package com.joker.tecsteps.javatecsteps.concurrency.model;

/**
 * Created by joker on 17-3-11.
 */
public class WaitPerson implements Runnable{
    private Restaurant restaurant;
    public WaitPerson(Restaurant restaurant){
        this.restaurant = restaurant;
    }

    public void run() {
        try{
            while (!Thread.interrupted()){
                System.out.println("wait person running");
                synchronized (this){
                    while (restaurant.meal == null){
                        System.out.println("waitPerson wait");
                        wait();
                    }
                    System.out.println("WaitPerson got "+ restaurant.meal);
                }
                synchronized (restaurant.chief){
                    restaurant.meal = null;
                    restaurant.chief.notifyAll();
                }
                Thread.sleep(1000);
            }

        }catch (InterruptedException e){
            System.out.println("WaitPerson interrupted");
        }
    }
}
package com.joker.tecsteps.javatecsteps.concurrency.model;

/**
 * Created by joker on 17-3-11.
 */
public class Chief implements Runnable{
    private int count = 0;
    private Restaurant restaurant;
    public Chief(Restaurant restaurant){
        this.restaurant = restaurant;
    }
    public void run() {
        try{
            while (!Thread.interrupted()){
                synchronized (this){
                    while (restaurant.meal != null){
                        System.out.println("Chief wait");
                        wait();
                    }
                }

                if(++count == 10){
                    System.out.println("out of foods");
                    restaurant.executor.shutdown();
                    return;
                }

                System.out.println("Order Up !");
                synchronized (restaurant.waitPerson){
                    restaurant.meal = new Restaurant.Meal(count);
                    System.out.println("chief produce !" + restaurant.meal);
                    restaurant.waitPerson.notifyAll();
                }
            }
        }catch (InterruptedException e){
            System.out.println("chief interrupted");
        }
    }
}

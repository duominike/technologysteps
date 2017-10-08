package com.joker.tecsteps.javatecsteps.concurrency.model;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * Created by joker on 17-3-11.
 */
public class ModelTest {

    public static void testWaitAndNotify(){
        new Restaurant();
    }

    public static void test_await_and_signal(){
        // 仓库对象
        IStorage storage = new Storage();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new ProduceRunnable(storage, 10));
        executorService.execute(new ProduceRunnable(storage, 30));
        executorService.execute(new ProduceRunnable(storage, 20));
        executorService.execute(new ProduceRunnable(storage, 90));
        executorService.execute(new ConsumeRunnable(storage, 10));
        executorService.execute(new ConsumeRunnable(storage, 90));
        executorService.execute(new ConsumeRunnable(storage, 60));
    }

    public static void test_BlockQueue(){
        // 仓库对象
        IStorage storage = new Storage();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new ProduceRunnable(storage, 10));
        executorService.execute(new ProduceRunnable(storage, 30));
        executorService.execute(new ProduceRunnable(storage, 20));
        executorService.execute(new ProduceRunnable(storage, 90));
        executorService.execute(new ConsumeRunnable(storage, 10));
        executorService.execute(new ConsumeRunnable(storage, 90));
        executorService.execute(new ConsumeRunnable(storage, 60));
    }


    public static  void testDieLock(){
        try{
        int ponder = 5;//当思考时间短时，死锁很快就发生，当思考时间长时，死锁可能不会发生
        int size = 5;
        ExecutorService exec = Executors.newCachedThreadPool();
        Chopstick[] sticks = new Chopstick[size];
        for (int i = 0; i < size; i++)
            sticks[i] = new Chopstick();
        for (int i = 0; i < size; i++){
            /**
             * 修复死锁条件
             */

            if( i < size - 1){
                exec.execute(new Philosopher(
                        sticks[i], sticks[(i + 1) % size], i, ponder));
            }else{
                exec.execute(new Philosopher(
                        sticks[0], sticks[i], i, ponder));
            }
            /*
            exec.execute(new Philosopher(
                    sticks[i], sticks[(i + 1) % size], i, ponder));
                    */
        }
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

//    private  volatile int number;
//    private   volatile boolean ready = false;
//
//
//    private class ReaderThread extends Thread{
//        @Override
//        public void run() {
//            while (!ready){
//                Thread.yield();
//                System.out.println(number);
//            }
//        }
//    }



}

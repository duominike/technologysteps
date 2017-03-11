package com.joker.tecsteps.javatecsteps.concurrency;

import java.util.concurrent.Callable;

/**
 * Created by joker on 17-3-6.
 */
public class TaskWithResult implements Callable<String>{
    private int id;
    public TaskWithResult(int id){
        this.id = id;
    }
    public String call() throws Exception {
        return "result of TaskWithResult: " + id;
    }
}

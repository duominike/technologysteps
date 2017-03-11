package com.joker.tecsteps.javatecsteps.concurrency;

import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by joker on 17-3-6.
 */
public class ConcurrencyTest {
    @Test
    public void testCachedThreadPool() {
        // CachedThreadPool 将为每个任务都创建一个线程
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Task());
        }
        //可以防止新任务被提交给这个Exector 当前线程将继续执行在shutdown()被调用之前的所有任务
        // 这个程序将在Executor中的所有任务完成之后尽快退出
        executorService.shutdown();
    }

    @Test
    public void testFixedThreadPool() {
        // FixedThreadPool 预先创建固定数量的线程 限制线程的数量
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Task());
        }
        //可以防止新任务被提交给这个Exector 当前线程将继续执行在shutdown()被调用之前的所有任务
        // 这个程序将在Executor中的所有任务完成之后尽快退出
        executorService.shutdown();
    }

    @Test
    public void testSingleThreadExecutor() {
        // SingleThreadExecutor 线程数为1的FixedThreadPool
        // 如果向它提交多个任务 这些任务将排队
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Task());
        }
        //可以防止新任务被提交给这个Exector 当前线程将继续执行在shutdown()被调用之前的所有任务
        // 这个程序将在Executor中的所有任务完成之后尽快退出
        executorService.shutdown();
    }

    @Test
    public void testTaskWithResult() {
        // CachedThreadPool 将为每个任务都创建一个线程
        ExecutorService executorService = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<Future<String>>();
        /**
         * submit 将产生 Future对象，它用Callable返回结果的特定类型进行了参数化
         * 你可以用isDone()判断Future是否已经完成
         */
        for (int i = 0; i < 5; i++) {
            results.add(executorService.submit(new TaskWithResult(i)));
        }
        try {
            for (Future<String> fs : results) {
                System.out.println(fs.get());
            }
        } catch (Exception e) {
            System.out.print(e);
        }finally {
            executorService.shutdown();
        }
        //可以防止新任务被提交给这个Exector 当前线程将继续执行在shutdown()被调用之前的所有任务
        // 这个程序将在Executor中的所有任务完成之后尽快退出
        executorService.shutdown();
    }

}

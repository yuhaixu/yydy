
package com.freesheep.common.util;

import java.util.List;
import java.util.concurrent.*;

public abstract class ExecutorsUtil {
    private final static ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void shutdown() {
        EXECUTOR_SERVICE.shutdown();
    }

    /**
     * 有返回值的提交任务
     *
     * @param task 任务
     * @return 结果
     */
    public static Future submit(Callable task) {
        return EXECUTOR_SERVICE.submit(task);
    }

    /**
     * 无返回值的提交任务
     *
     * @param task 任务
     */
    public static void execute(Runnable task) {
        EXECUTOR_SERVICE.execute(task);
    }

    public static List<Future<Object>> invokeAll(List<? extends Callable<Object>> callableList, long timeout, TimeUnit unit) throws InterruptedException {
        return EXECUTOR_SERVICE.invokeAll(callableList, timeout, unit);
    }

}

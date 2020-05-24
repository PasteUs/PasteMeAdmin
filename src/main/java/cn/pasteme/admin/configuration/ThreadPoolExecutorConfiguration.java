package cn.pasteme.admin.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Lucien
 * @version 1.0.0
 */
@Configuration
public class ThreadPoolExecutorConfiguration {

    @Bean("threadPoolExecutor")
    public ThreadPoolExecutor threadPoolExecutorFactory() {
        return new ThreadPoolExecutor(
                1,
                1024,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                new ThreadFactory() {

                    private final AtomicInteger threadNum = new AtomicInteger(1);

                    @Override
                    public Thread newThread(Runnable runnable) {
                        return new Thread(
                                runnable,
                                String.format("concurrent-executor-thread-%d", threadNum.getAndIncrement())
                        );
                    }
                }

        );
    }
}

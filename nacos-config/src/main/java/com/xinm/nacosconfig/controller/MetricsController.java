package com.xinm.nacosconfig.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author: xinm
 * @Date: 2022/08/26 16:15
 * @Email: abc5232033@163.com
 */
@Slf4j
@RestController
@RequestMapping("/nacos-config")
public class MetricsController {

    /**
     * Gauge（计量器）
     */
    @GetMapping("/hello")
    public void hello() {
        // 计量器
        gauge();
        // 计数器
        counter();
        // 计时器
        timer();
        // 摘要
        summary();
    }

    /**
     * Gauge（计量器）
     */
    private void gauge() {
        Metrics.gauge("user.test.gauge", RandomUtils.nextInt(100));
    }

    /**
     * Counter（计数器）
     */
    static final Counter userCounter = Metrics.counter("user.counter.total", "services", "demo");

    public void counter() {
        userCounter.increment(1D);
    }

    /**
     * Timer（计时器）
     */
    private Timer timer = Metrics.timer("user.test.timer", "timer", "timersample");

    private void timer() {
        // 执行createOrder方法并记录执行时间
        timer.record(() -> createOrder());
    }

    //模拟方法耗时
    private void createOrder() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
        }
    }

    /**
     * Summary（摘要）
     */
    private DistributionSummary summary = Metrics.summary("user.test.summary", "summary", "summarysample");

    private void summary() {
        summary.record(2D);
        summary.record(3D);
        summary.record(4D);
    }
}

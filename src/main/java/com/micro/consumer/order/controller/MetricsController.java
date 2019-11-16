package com.micro.consumer.order.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Meter;
import com.codahale.metrics.Timer;

import lombok.extern.slf4j.Slf4j;

/**
 * Metrics提供了五个基本的度量类型： Gauges（度量） Counters（计数器） Histograms（直方图数据）
 * Meters（TPS计算器） Timers（计时器）
 * 
 * @author Polin
 */
@Slf4j
@RestController
@RequestMapping("metrics")
public class MetricsController {
	// 使用metrics统计controller的访问数
	@Autowired
	@Qualifier("getMeter")
	private Meter getMeter;

	// 使用Metrics统计某个方法的调用时间
	@Autowired
	@Qualifier("getTimer")
	private Timer getTimer;

	// 实时绘制直方图的数据
	@Autowired
	@Qualifier("responseSizes")
	private Histogram responseSizes;

	@Autowired
	@Qualifier("pendingJobs")
	private Counter pendingJobs;

	@GetMapping("/getMetrics")
	public void getMetrics(@RequestParam(value = "id", required = true) Integer id) {
		getMeter.mark();

		// 增加计数
		pendingJobs.inc();
		// 减去计数
		pendingJobs.dec();

		responseSizes.update(new Random().nextInt(10));

		Timer.Context context = getTimer.time();
		// 这里模拟是一个耗时的操作，比如从其他系统获取用户信息。
		long sleepTime = (long) (Math.random() * 10 * 1000 + 1);
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			context.stop();
		}

	}
}

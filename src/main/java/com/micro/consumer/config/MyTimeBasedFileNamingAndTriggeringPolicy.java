package com.micro.consumer.config;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import ch.qos.logback.core.rolling.DefaultTimeBasedFileNamingAndTriggeringPolicy;

@NoAutoStart
public class MyTimeBasedFileNamingAndTriggeringPolicy<E> extends DefaultTimeBasedFileNamingAndTriggeringPolicy<E> {
	// 这个用来指定时间间隔
	private Integer multiple = 1;

	//重写这个方法，把multiple当作动态入参配置在logback.xml
	@Override
	protected void computeNextCheck() {
		nextCheck = rc.getEndOfNextNthPeriod(dateInCurrentPeriod, multiple).getTime();
	}

	public Integer getMultiple() {
		return multiple;
	}

	public void setMultiple(Integer multiple) {
		if (multiple > 1) {
			this.multiple = multiple;
		}
	}
}

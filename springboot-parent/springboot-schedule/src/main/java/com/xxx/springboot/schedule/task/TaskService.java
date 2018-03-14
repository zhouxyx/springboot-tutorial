package com.xxx.springboot.schedule.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class TaskService {
	
	private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

	/*
	 *  1.        Seconds
		2.        Minutes
		3.        Hours
		4.        Day-of-Month
		5.        Month
		6.        Day-of-Week
		7.        Year (可选字段)
	 * 
	 */
	@Scheduled(cron="*/2 * * * * ?")
	public void print() {
		logger.info("hello world");
	}
}

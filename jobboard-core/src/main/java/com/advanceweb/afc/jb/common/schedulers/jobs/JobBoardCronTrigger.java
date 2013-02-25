/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common.schedulers.jobs;


import java.text.ParseException;

import org.apache.log4j.Logger;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * TeaCronTrigger is used to read the Cron Expression from the property. It
 * overrides {@link CronTriggerBean}, This trigger is then scheduled using
 * {@link SchedulerFactoryBean}
 * 
 * @author muraliananthr
 * 
 */
public class JobBoardCronTrigger extends CronTriggerBean {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(JobBoardCronTrigger.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The cron exp. */
	private String cronExp;

	/**
	 * Cron Expression is to be set based upon the value in the property file
	 * 
	 * @see org.springframework.scheduling.quartz.CronTriggerBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws ParseException {
		setCronExpression(getCronExp());
		try {
			super.afterPropertiesSet();
		} catch (Exception e) {
			LOGGER.error(e);
		}
		LOGGER.debug("Set cron expression from property file");
	}

	/**
	 * Gets the cronExpressionParameterName attribute.
	 * 
	 * @return the cronExp
	 */
	public String getCronExp() {
		return cronExp;
	}

	/**
	 * Sets the cronExpressionParameterName attribute value.
	 * 
	 * @param cronExp
	 *            the cronExp to set
	 */
	public void setCronExp(String cronExp) {
		this.cronExp = cronExp;
	}

}

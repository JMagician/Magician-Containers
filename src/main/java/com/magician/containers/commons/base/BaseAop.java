package com.magician.containers.commons.base;

/**
 * AOP Definition Template
 * @author yuye
 *
 */
public interface BaseAop {

	/**
	 * Called before the method starts
	 * @param args
	 */
	void startMethod(Object[] args);
	
	/**
	 * Called after the method starts
	 * @param args
	 * @param result
	 */
	void endMethod(Object[] args, Object result);
	
	/**
	 * Called after an exception
	 * @param e
	 */
	void exp(Throwable e);
}

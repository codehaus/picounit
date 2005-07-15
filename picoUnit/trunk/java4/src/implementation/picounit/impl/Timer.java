/*****************************************************************************
 * Copyright (C) Stacy Curl. All rights reserved.                            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *****************************************************************************/
package picounit.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Timer implements Runnable {
	private static final boolean PRINT_TIMES =
		"true".equals(System.getProperty("picounit.timer.print"));

	private final String name;

	private long min = Long.MAX_VALUE;
	private long max = 0;
	private long calls = 0;
	private long sum;
	
	private long start;
	private final List times = new LinkedList();

	public Timer(String name) {
		this.name = name;
		if (PRINT_TIMES) {
			Runtime.getRuntime().addShutdownHook(new Thread(this));
		}
	}
	
	public void start() {
		start = System.currentTimeMillis();
	}
	
	public void end() {
		long time = System.currentTimeMillis() - start;
		times.add(new Long(time));
		
		min = Math.min(time, min);
		max = Math.max(time, max);
		
		sum += time;
		calls++;
	}

	public void run() {
		long sum = 0;
		long min = Long.MAX_VALUE;
		long max = 0;
		
		for (Iterator iterator = times.iterator(); iterator.hasNext();) {
			long time = ((Long) iterator.next()).longValue();
			min = Math.min(time, min);
			max = Math.max(time, max);
			sum += time;
		}
		
		System.out.println("Time spent in '" + name + "' : " + sum + ", min: " + min +
			", max: " + max + ", calls: " + times.size());
	}
}
package com.lk.jedis;

public class RedisConfig {
	public static int maxTotal=20;

	public static int maxIdle=20;
	
	public static int maxWaitMillis=1000;

	public static int timeout=60 * 1000;
	
	public static int getMaxTotal() {
		return maxTotal;
	}

	public static int getMaxIdle() {
		return maxIdle;
	}

	public static int getMaxWaitMillis() {
		return maxWaitMillis;
	}

	public static int getTimeout() {
		return timeout;
	}
	
	
}

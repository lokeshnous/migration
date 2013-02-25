/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.common.ads;

import java.util.LinkedHashMap;

/**
 * AdLocationCache is a crude implementation of LRU cache for storing the ad
 * location keywords for the advertisement. All the methods in this class is
 * synchronized. This class uses a LinkedHashMap in the back. The LinkedHashMap
 * is used to ensure the order of insertions properly.
 * 
 * The LRU algorithm is implemented by removing an item from the list and
 * re-inserting it again every time it is accessed. The re-insertion will ensure
 * that the last used data is always at the bottom of the stack.
 * 
 * The put operation will ensure that the map size is not increased beyond the
 * pre defined size. If the size exceeds, the top items from the list is
 * removed.
 * 
 * It may be noted that both get and put operations are costly in this
 * implementation.
 * 
 * @author sukeshnambiar
 * 
 */
public class AdLocationCache {

	/** The max size. */
	private int maxSize = 100;

	/** The cache. */
	private LinkedHashMap<String, String> cache = new LinkedHashMap<String, String>();

	/**
	 * Creates a cache with a size of <code> maxSize
	 * 
	 * @param maxSize
	 *            The maximum size of the cache
	 */
	public AdLocationCache(int maxSize) {
		this.maxSize = maxSize;
	}

	/**
	 * Get the cache entry identified by the key
	 * 
	 * @param key
	 *            The key to the entry
	 * @return The value corresponding to the key, null if key is not found.
	 */
	public synchronized String get(String key) {
		if (cache.containsKey(key)) {
			String value = cache.get(key);
			cache.remove(key);
			cache.put(key, value);
		}
		return cache.get(key);
	}

	/**
	 * Put a new entry to the cache. If the cache size exceeds
	 * <code>maxSize</code> after the insertion, the topmost item is removed
	 * 
	 * @param key
	 *            Key for the entry
	 * @param value
	 *            Value for the entry
	 * @return the inserted item
	 */
	public synchronized String put(String key, String value) {
		String result = cache.put(key, value);

		// Check if the size is exceeded and implement a crude LRU
		int excess = cache.size() - maxSize;
		if (excess > 0) {
			String[] keys = cache.keySet().toArray(new String[0]);
			for (int i = 0; i < excess; i++) {
				cache.remove(keys[i]);
			}
		}

		return result;
	}

	/**
	 * Clears the contents of the cache for reuse
	 */
	public synchronized void clear() {
		cache.clear();
	}

}

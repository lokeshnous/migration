/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.common.ads;

/**
 * This class is used to represent the advertisement sizes. The standard IAB
 * sizez are added as constants in this class for reference within the
 * application.
 * 
 * @author sukeshnambiar
 * 
 */
public class AdSize {

	// Standard IAB sizes
	/** The Constant IAB_MEDIUM_RECTANGLE. */
	public static final AdSize IAB_MEDIUM_RECTANGLE = new AdSize(
			"Medium Rectangle", 300, 250);
	
	/** The Constant IAB_RECTANGLE. */
	public static final AdSize IAB_RECTANGLE = new AdSize("Rectangle", 180, 150);
	
	/** The Constant IAB_LEADERBOARD. */
	public static final AdSize IAB_LEADERBOARD = new AdSize("Leaderboard", 728,
			90);
	
	/** The Constant IAB_WIDE_SKYSCRAPER. */
	public static final AdSize IAB_WIDE_SKYSCRAPER = new AdSize(
			"Wide skyscraper", 160, 600);

	// Other standard sizes
	/** The Constant SQUARE_POP_UP. */
	public static final AdSize SQUARE_POP_UP = new AdSize("Square Pop-Up", 250,
			250);
	
	/** The Constant VERTICAL_RECTANGLE. */
	public static final AdSize VERTICAL_RECTANGLE = new AdSize(
			"Vertical Rectangle", 240, 400);
	
	/** The Constant LARGE_RECTANGLE. */
	public static final AdSize LARGE_RECTANGLE = new AdSize("Large Rectangle",
			336, 280);
	
	/** The Constant RECTANGLE_3X1. */
	public static final AdSize RECTANGLE_3X1 = new AdSize("3:1 Rectangle", 300,
			100);
	
	/** The Constant POP_UNDER. */
	public static final AdSize POP_UNDER = new AdSize("Pop-Under", 720, 300);
	
	/** The Constant FULL_BANNER. */
	public static final AdSize FULL_BANNER = new AdSize("Full banner", 468, 60);
	
	/** The Constant HALF_BANNER. */
	public static final AdSize HALF_BANNER = new AdSize("Half banner", 234, 60);
	
	/** The Constant MICRO_BAR. */
	public static final AdSize MICRO_BAR = new AdSize("Micro bar", 88, 31);
	
	/** The Constant BUTTON_1. */
	public static final AdSize BUTTON_1 = new AdSize("Button 1", 120, 90);
	
	/** The Constant BUTTON_2. */
	public static final AdSize BUTTON_2 = new AdSize("Button 2", 120, 60);
	
	/** The Constant VERTICAL_BANNER. */
	public static final AdSize VERTICAL_BANNER = new AdSize("Vertical banner",
			120, 240);
	
	/** The Constant SQUARE_BUTTON. */
	public static final AdSize SQUARE_BUTTON = new AdSize("Square button", 125,
			125);
	
	/** The Constant SKYSCRAPER. */
	public static final AdSize SKYSCRAPER = new AdSize("Skyscraper", 120, 600);
	
	/** The Constant HALF_PAGE_AD. */
	public static final AdSize HALF_PAGE_AD = new AdSize("Half page ad", 300,
			600);

	// Default name for the custom size
	/** The Constant CUSTOM_SIZE. */
	private static final String CUSTOM_SIZE = "Custom Size";

	/** The width. */
	private long width;
	
	/** The height. */
	private long height;
	
	/** The name. */
	private String name;

	/**
	 * Copy constructore. Create a copy of an existing adsize
	 * 
	 * @param size
	 */
	public AdSize(AdSize size) {
		width = size.width;
		height = size.height;
		name = size.name;
	}

	/**
	 * Constructs and AdSize from the given inputs
	 * 
	 * @param name
	 *            Name of the size
	 * @param width
	 *            width in pixels
	 * @param height
	 *            height in pixels
	 */
	public AdSize(String name, long width, long height) {
		this.width = width;
		this.height = height;
		this.name = name;
	}

	/**
	 * Instantiates a new ad size.
	 *
	 * @param width the width
	 * @param height the height
	 */
	public AdSize(long width, long height) {
		this(CUSTOM_SIZE, width, height);
	}

	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public long getWidth() {
		return width;
	}

	/**
	 * Sets the width.
	 *
	 * @param width the new width
	 */
	public void setWidth(long width) {
		this.width = width;
	}

	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public long getHeight() {
		return height;
	}

	/**
	 * Sets the height.
	 *
	 * @param height the new height
	 */
	public void setHeight(long height) {
		this.height = height;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("%s (%s x %s)", name, width, height);
	}

}

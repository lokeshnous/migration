package com.advanceweb.afc.jb.advt.service;

public class AdSize {

	// Standard IAB sizes
	public static final AdSize IAB_MEDIUM_RECTANGLE = new AdSize(
			"Medium Rectangle", 300, 250);
	public static final AdSize IAB_RECTANGLE = new AdSize("Rectangle", 180, 150);
	public static final AdSize IAB_LEADERBOARD = new AdSize("Leaderboard", 728,
			90);
	public static final AdSize IAB_WIDE_SKYSCRAPER = new AdSize(
			"Wide skyscraper", 160, 600);

	// Other standard sizes
	public static final AdSize SQUARE_POP_UP = new AdSize("Square Pop-Up", 250,
			250);
	public static final AdSize VERTICAL_RECTANGLE = new AdSize(
			"Vertical Rectangle", 240, 400);
	public static final AdSize LARGE_RECTANGLE = new AdSize("Large Rectangle",
			336, 280);
	public static final AdSize RECTANGLE_3X1 = new AdSize("3:1 Rectangle", 300,
			100);
	public static final AdSize POP_UNDER = new AdSize("Pop-Under", 720, 300);
	public static final AdSize FULL_BANNER = new AdSize("Full banner", 468, 60);
	public static final AdSize HALF_BANNER = new AdSize("Half banner", 234, 60);
	public static final AdSize MICRO_BAR = new AdSize("Micro bar", 88, 31);
	public static final AdSize BUTTON_1 = new AdSize("Button 1", 120, 90);
	public static final AdSize BUTTON_2 = new AdSize("Button 2", 120, 60);
	public static final AdSize VERTICAL_BANNER = new AdSize("Vertical banner",
			120, 240);
	public static final AdSize SQUARE_BUTTON = new AdSize("Square button", 125,
			125);
	public static final AdSize SKYSCRAPER = new AdSize("Skyscraper", 120, 600);
	public static final AdSize HALF_PAGE_AD = new AdSize("Half page ad", 300,
			600);

	// Default name for the custom size
	private static final String CUSTOM_SIZE = "Custom Size";

	private long width;
	private long height;
	private String name;

	public AdSize(AdSize size) {
		width = size.width;
		height = size.height;
		name = size.name;
	}

	public AdSize(String name, long width, long height) {
		this.width = width;
		this.height = height;
		this.name = name;
	}

	public AdSize(long width, long height) {
		this(CUSTOM_SIZE, width, height);
	}

	public long getWidth() {
		return width;
	}

	public void setWidth(long width) {
		this.width = width;
	}

	public long getHeight() {
		return height;
	}

	public void setHeight(long height) {
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

package com.advanceweb.afc.jb.common;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:24:26 PM
 */
public class SubscriptionListDTO {

	private boolean newsLetters;
	private boolean emailer;
	private boolean magazines;
	/**
	 * @return the newsLetters
	 */
	public boolean isNewsLetters() {
		return newsLetters;
	}
	/**
	 * @param newsLetters the newsLetters to set
	 */
	public void setNewsLetters(boolean newsLetters) {
		this.newsLetters = newsLetters;
	}
	/**
	 * @return the emailer
	 */
	public boolean isEmailer() {
		return emailer;
	}
	/**
	 * @param emailer the emailer to set
	 */
	public void setEmailer(boolean emailer) {
		this.emailer = emailer;
	}
	/**
	 * @return the magazines
	 */
	public boolean isMagazines() {
		return magazines;
	}
	/**
	 * @param magazines the magazines to set
	 */
	public void setMagazines(boolean magazines) {
		this.magazines = magazines;
	}


}
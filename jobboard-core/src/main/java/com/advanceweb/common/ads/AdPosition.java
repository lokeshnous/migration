/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.common.ads;

/**
 * The enumeration AdPosition defines the location wihin a page where ad is
 * displayed. The location is as follows<br/>
 * <table border="1">
 * <tr>
 * <td colspan=3>Top</td>
 * </tr>
 * <tr>
 * <td>Top Left</td>
 * <td>Top Center</td>
 * <td>Top Right</td>
 * </tr>
 * <tr>
 * <td colspan=3>&nbsp</td>
 * </tr>
 * <tr>
 * <td>Left Top</td>
 * <td>Center Top</td>
 * <td>Right Top</td>
 * </tr>
 * <tr>
 * <td>Left Middle</td>
 * <td>Center Middle</td>
 * <td>Right Middle</td>
 * </tr>
 * <tr>
 * <td>Left Bottom</td>
 * <td>Center Bottom</td>
 * <td>Right Bottom</td>
 * </tr>
 * <tr>
 * <td colspan=3>&nbsp</td>
 * </tr>
 * <tr>
 * <td>Bottom Left</td>
 * <td>Bottom Center</td>
 * <td>Bottom Right</td>
 * </tr>
 * <tr>
 * <td colspan=3>Bottom</td>
 * </tr>
 * </table>
 * 
 * @author sukeshnambiar
 * 
 */

public enum AdPosition {
	
	/** The top. */
	TOP, // Top of page
	/** The middle. */
 MIDDLE,//
	/** The bottom. */
BOTTOM, //
	/** The left. */
 LEFT, //
	/** The center. */
 CENTER, //
	/** The right. */
 RIGHT, //
	/** The top left. */
 TOP_LEFT, //
	/** The top center. */
 TOP_CENTER, //
	/** The top right. */
 TOP_RIGHT, //
	/** The left top. */
 LEFT_TOP, //
	/** The left middle. */
 LEFT_MIDDLE, //
	/** The left botom. */
 LEFT_BOTOM, //
	/** The center top. */
 CENTER_TOP, //
	/** The center middle. */
 CENTER_MIDDLE, //
	/** The center botom. */
 CENTER_BOTOM, //
	/** The right top. */
 RIGHT_TOP, //
	/** The right middle. */
 RIGHT_MIDDLE, //
	/** The right botom. */
 RIGHT_BOTOM, //
	/** The bottom left. */
 BOTTOM_LEFT, //
	/** The bottom center. */
 BOTTOM_CENTER, //
	/** The bottom right. */
 BOTTOM_RIGHT //
}

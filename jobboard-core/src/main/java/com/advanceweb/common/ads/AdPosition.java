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
	TOP, // Top of page
	MIDDLE,//
	BOTTOM, //
	LEFT, //
	CENTER, //
	RIGHT, //
	TOP_LEFT, //
	TOP_CENTER, //
	TOP_RIGHT, //
	LEFT_TOP, //
	LEFT_MIDDLE, //
	LEFT_BOTOM, //
	CENTER_TOP, //
	CENTER_MIDDLE, //
	CENTER_BOTOM, //
	RIGHT_TOP, //
	RIGHT_MIDDLE, //
	RIGHT_BOTOM, //
	BOTTOM_LEFT, //
	BOTTOM_CENTER, //
	BOTTOM_RIGHT //
}

package com.advanceweb.common.template;

import java.util.Map;

/**
 * AdvanceTemplate is a generic template interface to be used by the Advance
 * applications.
 * 
 * @author sukeshnambiar
 * 
 */
public interface AdvanceTemplate {
	/**
	 * The process method will use the Map of parameters to merge the template
	 * text with the place holders marked in the template text.
	 * 
	 * @param params
	 *            Map containing the parameters to be replaced at the
	 *            placeholders.
	 * @return The merged string
	 */
	String process(Map<String, Object> params);

}

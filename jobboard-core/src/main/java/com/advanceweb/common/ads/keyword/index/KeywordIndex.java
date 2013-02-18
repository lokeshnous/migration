package com.advanceweb.common.ads.keyword.index;

/**
 * This interfeace defines the field names for the keyword index
 * 
 * @author sukeshnambiar
 * 
 */
public interface KeywordIndex {
	/**
	 * The filed name for the keyword (Content topic)
	 */
	String KEYWORD_FIELD = "keyword";
	
	/**
	 * Field name for the synonyms or related keywords
	 */
	String RELATED_FIELD = "related";
	
	/**
	 * Field name for the tag id (tid)
	 */
	String TOPIC_ID_FIELD = "tid";
}

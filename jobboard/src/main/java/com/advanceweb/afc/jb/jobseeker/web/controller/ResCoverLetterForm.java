/**
 * 
 */
package com.advanceweb.afc.jb.jobseeker.web.controller;

/**
  * This method is called for create cover letter
  * etc.
  * @author kartikm
  * @version V.0.1
  *
  */
public class ResCoverLetterForm {
	private int coverletterId;
	private String name;
	private String coverletterText;
	private String createDt;
	private String updateDt;
	private String deleteDt;
	private int userId;
	private int active;
	private String description;
	
	/**
	 * @return the coverletterId
	 */
	public int getCoverletterId() {
		return coverletterId;
	}
	/**
	 * @param coverletterId the coverletterId to set
	 */
	public void setCoverletterId(int coverletterId) {
		this.coverletterId = coverletterId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the coverletterText
	 */
	public String getCoverletterText() {
		return coverletterText;
	}
	/**
	 * @param coverletterText the coverletterText to set
	 */
	public void setCoverletterText(String coverletterText) {
		this.coverletterText = coverletterText;
	}
	/**
	 * @return the createDt
	 */
	public String getCreateDt() {
		return createDt;
	}
	/**
	 * @param createDt the createDt to set
	 */
	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}
	/**
	 * @return the updateDt
	 */
	public String getUpdateDt() {
		return updateDt;
	}
	/**
	 * @param updateDt the updateDt to set
	 */
	public void setUpdateDt(String updateDt) {
		this.updateDt = updateDt;
	}
	/**
	 * @return the deleteDt
	 */
	public String getDeleteDt() {
		return deleteDt;
	}
	/**
	 * @param deleteDt the deleteDt to set
	 */
	public void setDeleteDt(String deleteDt) {
		this.deleteDt = deleteDt;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the active
	 */
	public int getActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(int active) {
		this.active = active;
	}
	
	public String getDescription() {
		return description;
	}
	/**
	 * @param coverletterText the coverletterText to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}

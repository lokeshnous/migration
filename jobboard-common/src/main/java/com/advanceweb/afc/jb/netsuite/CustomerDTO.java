package com.advanceweb.afc.jb.netsuite;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerDTO {

	
	private int customerId;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	
	
	/*private Custentity_Region custentity_Region;
	private String phone;
	private SalesTeam salesTeam;
	private boolean giveAccess;
	private GlobalSubscriptionStatus globalSubscriptionStatus;
	private Terms terms;
	private double custEntity_legacyRevenue;
	private Date dateCreated;
	private boolean custEntity_ahaFacility;
	private double balance;
	private boolean emailTransactions;
	private boolean custEntity_poRequired;
	private boolean custEntity_holdsecNotice;
	private boolean isBudgetApproved;
	private String recordType;
	private EmailPreference emailPreference;
	private boolean sendEmail;
	private String fax;
	private double unbilledOrders;
	private List<Subscriptions> subscriptions = new ArrayList<Subscriptions>();
	
	private boolean taxable;
	private boolean isInactive;
	private double depositBalance;

	private double custEntity_legacyRevenue2010;

	private Category category;
	private int custEntity_numBeds;
	private String webLead;

	private List<AddressBook> addressBook = new ArrayList<AddressBook>();
	private ReceivablesAccount receivablesAccount;

	private CreditHoldOverride creditHoldOverride;

	// need to check
	private List<CustEntity_ApplicMarkets> custEntity_ApplicMarkets = new ArrayList<CustEntity_ApplicMarkets>();

	private List<ContactRoles> contactRoles = new ArrayList<ContactRoles>();

	private Territory territory;

	private boolean isPerson;

	private boolean custEntity_ccRequired;

	private Date lastModifiedDate;

	private String ID;

	private boolean custEntity_invIndividually;
	// need to check
	private List<CustEntity_FacilityType> custEntity_FacilityType = new ArrayList<CustEntity_FacilityType>();

	private EntityStatus entityStatus;

	private double custEntity_legacyRevenue2009;

	private String custEntity_legacyId;

	private boolean faxTransactions;

	private boolean custEntity_autoPickUp;

	private String entityId;

	private boolean printTransactions;

	private String companyName;

	private CustEntity_creditStatus custEntity_creditStatus;

	private double overDueBalance;

	private CustEntityCreditAppStatus custEntityCreditAppStatus;

	private double custEntity_Legacy_Revenue_2007;

	private CustEntity_TearsheetOptions custEntity_TearsheetOptions;

	private double custEntity_Legacy_Revenue_2008;

	public Custentity_Region getCustentity_Region() {
		return custentity_Region;
	}

	public void setCustentity_Region(Custentity_Region custentity_Region) {
		this.custentity_Region = custentity_Region;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public SalesTeam getSalesTeam() {
		return salesTeam;
	}

	public void setSalesTeam(SalesTeam salesTeam) {
		this.salesTeam = salesTeam;
	}

	public boolean isGiveAccess() {
		return giveAccess;
	}

	public void setGiveAccess(boolean giveAccess) {
		this.giveAccess = giveAccess;
	}

	public GlobalSubscriptionStatus getGlobalSubscriptionStatus() {
		return globalSubscriptionStatus;
	}

	public void setGlobalSubscriptionStatus(
			GlobalSubscriptionStatus globalSubscriptionStatus) {
		this.globalSubscriptionStatus = globalSubscriptionStatus;
	}

	public Terms getTerms() {
		return terms;
	}

	public void setTerms(Terms terms) {
		this.terms = terms;
	}

	public double getCustEntity_legacyRevenue() {
		return custEntity_legacyRevenue;
	}

	public void setCustEntity_legacyRevenue(double custEntity_legacyRevenue) {
		this.custEntity_legacyRevenue = custEntity_legacyRevenue;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public boolean isCustEntity_ahaFacility() {
		return custEntity_ahaFacility;
	}

	public void setCustEntity_ahaFacility(boolean custEntity_ahaFacility) {
		this.custEntity_ahaFacility = custEntity_ahaFacility;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public boolean isEmailTransactions() {
		return emailTransactions;
	}

	public void setEmailTransactions(boolean emailTransactions) {
		this.emailTransactions = emailTransactions;
	}

	public boolean isCustEntity_poRequired() {
		return custEntity_poRequired;
	}

	public void setCustEntity_poRequired(boolean custEntity_poRequired) {
		this.custEntity_poRequired = custEntity_poRequired;
	}

	public boolean isCustEntity_holdsecNotice() {
		return custEntity_holdsecNotice;
	}

	public void setCustEntity_holdsecNotice(boolean custEntity_holdsecNotice) {
		this.custEntity_holdsecNotice = custEntity_holdsecNotice;
	}

	public boolean isBudgetApproved() {
		return isBudgetApproved;
	}

	public void setBudgetApproved(boolean isBudgetApproved) {
		this.isBudgetApproved = isBudgetApproved;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public EmailPreference getEmailPreference() {
		return emailPreference;
	}

	public void setEmailPreference(EmailPreference emailPreference) {
		this.emailPreference = emailPreference;
	}

	public boolean isSendEmail() {
		return sendEmail;
	}

	public void setSendEmail(boolean sendEmail) {
		this.sendEmail = sendEmail;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public double getUnbilledOrders() {
		return unbilledOrders;
	}

	public void setUnbilledOrders(double unbilledOrders) {
		this.unbilledOrders = unbilledOrders;
	}

	public List<Subscriptions> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Subscriptions> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public boolean isTaxable() {
		return taxable;
	}

	public void setTaxable(boolean taxable) {
		this.taxable = taxable;
	}

	public boolean isInactive() {
		return isInactive;
	}

	public void setInactive(boolean isInactive) {
		this.isInactive = isInactive;
	}

	public double getDepositBalance() {
		return depositBalance;
	}

	public void setDepositBalance(double depositBalance) {
		this.depositBalance = depositBalance;
	}

	public double getCustEntity_legacyRevenue2010() {
		return custEntity_legacyRevenue2010;
	}

	public void setCustEntity_legacyRevenue2010(double custEntity_legacyRevenue2010) {
		this.custEntity_legacyRevenue2010 = custEntity_legacyRevenue2010;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getCustEntity_numBeds() {
		return custEntity_numBeds;
	}

	public void setCustEntity_numBeds(int custEntity_numBeds) {
		this.custEntity_numBeds = custEntity_numBeds;
	}

	public String getWebLead() {
		return webLead;
	}

	public void setWebLead(String webLead) {
		this.webLead = webLead;
	}

	public List<AddressBook> getAddressBook() {
		return addressBook;
	}

	public void setAddressBook(List<AddressBook> addressBook) {
		this.addressBook = addressBook;
	}

	public ReceivablesAccount getReceivablesAccount() {
		return receivablesAccount;
	}

	public void setReceivablesAccount(ReceivablesAccount receivablesAccount) {
		this.receivablesAccount = receivablesAccount;
	}

	public CreditHoldOverride getCreditHoldOverride() {
		return creditHoldOverride;
	}

	public void setCreditHoldOverride(CreditHoldOverride creditHoldOverride) {
		this.creditHoldOverride = creditHoldOverride;
	}

	public List<CustEntity_ApplicMarkets> getCustEntity_ApplicMarkets() {
		return custEntity_ApplicMarkets;
	}

	public void setCustEntity_ApplicMarkets(
			List<CustEntity_ApplicMarkets> custEntity_ApplicMarkets) {
		this.custEntity_ApplicMarkets = custEntity_ApplicMarkets;
	}

	public List<ContactRoles> getContactRoles() {
		return contactRoles;
	}

	public void setContactRoles(List<ContactRoles> contactRoles) {
		this.contactRoles = contactRoles;
	}

	public Territory getTerritory() {
		return territory;
	}

	public void setTerritory(Territory territory) {
		this.territory = territory;
	}

	public boolean isPerson() {
		return isPerson;
	}

	public void setPerson(boolean isPerson) {
		this.isPerson = isPerson;
	}

	public boolean isCustEntity_ccRequired() {
		return custEntity_ccRequired;
	}

	public void setCustEntity_ccRequired(boolean custEntity_ccRequired) {
		this.custEntity_ccRequired = custEntity_ccRequired;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public boolean isCustEntity_invIndividually() {
		return custEntity_invIndividually;
	}

	public void setCustEntity_invIndividually(boolean custEntity_invIndividually) {
		this.custEntity_invIndividually = custEntity_invIndividually;
	}

	public List<CustEntity_FacilityType> getCustEntity_FacilityType() {
		return custEntity_FacilityType;
	}

	public void setCustEntity_FacilityType(
			List<CustEntity_FacilityType> custEntity_FacilityType) {
		this.custEntity_FacilityType = custEntity_FacilityType;
	}

	public EntityStatus getEntityStatus() {
		return entityStatus;
	}

	public void setEntityStatus(EntityStatus entityStatus) {
		this.entityStatus = entityStatus;
	}

	public double getCustEntity_legacyRevenue2009() {
		return custEntity_legacyRevenue2009;
	}

	public void setCustEntity_legacyRevenue2009(double custEntity_legacyRevenue2009) {
		this.custEntity_legacyRevenue2009 = custEntity_legacyRevenue2009;
	}

	public String getCustEntity_legacyId() {
		return custEntity_legacyId;
	}

	public void setCustEntity_legacyId(String custEntity_legacyId) {
		this.custEntity_legacyId = custEntity_legacyId;
	}

	public boolean isFaxTransactions() {
		return faxTransactions;
	}

	public void setFaxTransactions(boolean faxTransactions) {
		this.faxTransactions = faxTransactions;
	}

	public boolean isCustEntity_autoPickUp() {
		return custEntity_autoPickUp;
	}

	public void setCustEntity_autoPickUp(boolean custEntity_autoPickUp) {
		this.custEntity_autoPickUp = custEntity_autoPickUp;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public boolean isPrintTransactions() {
		return printTransactions;
	}

	public void setPrintTransactions(boolean printTransactions) {
		this.printTransactions = printTransactions;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public CustEntity_creditStatus getCustEntity_creditStatus() {
		return custEntity_creditStatus;
	}

	public void setCustEntity_creditStatus(
			CustEntity_creditStatus custEntity_creditStatus) {
		this.custEntity_creditStatus = custEntity_creditStatus;
	}

	public double getOverDueBalance() {
		return overDueBalance;
	}

	public void setOverDueBalance(double overDueBalance) {
		this.overDueBalance = overDueBalance;
	}

	public CustEntityCreditAppStatus getCustEntityCreditAppStatus() {
		return custEntityCreditAppStatus;
	}

	public void setCustEntityCreditAppStatus(
			CustEntityCreditAppStatus custEntityCreditAppStatus) {
		this.custEntityCreditAppStatus = custEntityCreditAppStatus;
	}

	public double getCustEntity_Legacy_Revenue_2007() {
		return custEntity_Legacy_Revenue_2007;
	}

	public void setCustEntity_Legacy_Revenue_2007(
			double custEntity_Legacy_Revenue_2007) {
		this.custEntity_Legacy_Revenue_2007 = custEntity_Legacy_Revenue_2007;
	}

	public CustEntity_TearsheetOptions getCustEntity_TearsheetOptions() {
		return custEntity_TearsheetOptions;
	}

	public void setCustEntity_TearsheetOptions(
			CustEntity_TearsheetOptions custEntity_TearsheetOptions) {
		this.custEntity_TearsheetOptions = custEntity_TearsheetOptions;
	}

	public double getCustEntity_Legacy_Revenue_2008() {
		return custEntity_Legacy_Revenue_2008;
	}

	public void setCustEntity_Legacy_Revenue_2008(
			double custEntity_Legacy_Revenue_2008) {
		this.custEntity_Legacy_Revenue_2008 = custEntity_Legacy_Revenue_2008;
	}
*/
	

}

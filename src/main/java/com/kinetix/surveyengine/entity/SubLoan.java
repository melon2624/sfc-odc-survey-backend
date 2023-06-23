package com.kinetix.surveyengine.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


/**
 * @author melo
 * @date 2023-06-16 11:27
 */
public class SubLoan {
    private String caseId;

    private String formType;

    private Date applDate;

    private String assoLoanId;

    private String ceref;

    private String feeReceivedMaster;

    private Date feePaymentReceiptDate;

    private Date acknowledgingApplicationReceiptDate;

    private Date acknowledgingFeePaymentReceiptDate;

    private String meetPerformancePledge;

    private Date loanAgreeDate;

    private String currencyMaster;

    private BigDecimal loanFacilityAmt;

    private String borrower;

    private String lenderName1;

    private String lenderName2;

    private String lenderName3;

    private String lenderName4;

    private String lenderName5;

    private String loanTypeNewappSubmission;

    private String currencyNewappSubmission;

    private BigDecimal loanFacilityAmountNewappSubmission;

    private String interestRateNewappSubmission;

    private String durationNewappSubmission;

    private String applStatusNewapp;

    private Date inprincipleApplLetterIssuedDateNewapp;

    private Date finalApplLetterDateNewapp;

    private Date rejectLetterDateNewapp;

    private Date returnLetterDateNewapp;

    private Date applRejectWithdlDateNewapp;

    private Date loanAgreementDateNewapp;

    private Date loanExpiryDateNewapp;

    private String approvedCurrencyNewapp;

    private String approvedLoanAmountFacilityNewapp;

    private String approvedInterestRateNewapp;

    private String approvedDurationNewapp;

    private Date dateOfDrawdownSubmission;

    private String currencyDrawdownSubmission;

    private String drawdownAmtSubmission;

    private String applStatusDrawdown;

    private Date confirmLetterIssueDateDrawdown;

    private Date rejectLetterDateDrawdown;

    private Date returnLetterDateDrawdown;

    private Date applRejectWithdlDateDrawdown;

    private Date approvedDateOfDrawdown;

    private String currencyApprovedDrawdown;

    private BigDecimal approvedDrawdownAmt;

    private Date expiryDateOfTheLoanRenewalSubmission;

    private String applStatusRenewal;

    private Date applLetterIssueDateRenewal;

    private Date finalApplLetterDateRenewal;

    private Date rejectLetterDateRenewal;

    private Date returnLetterDateRenewal;

    private Date loanExpiryDateApprovrdRenewal;

    private String proposeVariationVarSubmission;

    private String applStatusVar;

    private Date applLetterIssuedDateVar;

    private Date rejectLetterIssuedDateVar;

    private Date returnLetterDateVar;

    private Date applRejectWithdlDateVar;

    private String applVariationLoanAgreement;

    private Date dateOfRepaymentSubmission;

    private String currencyRepaymentSubmission;

    private BigDecimal repaymentAmtSubmission;

    private String applStatusRepayment;

    private Date priorConsentLetterIssuedDateRepayment;

    private Date applLetterIssuedDateRepayment;

    private Date rejectLetterIssuedDateRepayment;

    private Date returnLetterDateRepayment;

    private Date applRejectWithdlDateRepayment;

    private Date approvedDateOfRepayment;

    private String approvedCurrencyRepayment;

    private BigDecimal approvedRepaymentAmt;

    private String proposeAmendmentConditionSubmission;

    private String applStatusAmendmentCondition;

    private Date inprincipleApplLetterIssuedDateAmendmentCondition;

    private Date applLetterIssuedDateAmendmentCondition;

    private Date rejectLetterIssuedDateAmendmentCondition;

    private Date returnLetterDateAmendmentCondition;

    private Date applRejectWithdlDateAmendmentCondition;

    private String approvedAmendmentCondition;

    private Date dateOfTermSubmission;

    private String applStatusTerm;

    private Date applLetterIssueDateTerm;

    private Date finalApplLetterDateTerm;

    private Date rejectLetterDateTerm;

    private Date returnLetterDateTerm;

    private Date loanExpiryDateApprovrdTerm;

    private Date approvedDateOfTerm;

    private Date lastApplExtRenewDateMovement;

    private Date loanExpiryDateMovement;

    private String expiredLoanMovement;

    private Date termDateBorrowerLoanMovement;

    private Date termDateSfcLoanMovement;

    private String variationOfTermsApprovalLoanMovement;

    private String impositionAmendmentRevocationConditionApprlSfcMovement;

    private String currencyLoanMovement;

    private BigDecimal facilityLimitLoanMovement;

    private BigDecimal loanAmtMovement;

    private BigDecimal outstandingLoanAmtMovement;

    private Date creationDate;

    private String createdBy;

    private Date lastUpdateDate;

    private String lastUpdatedBy;

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public Date getApplDate() {
        return applDate;
    }

    public void setApplDate(Date applDate) {
        this.applDate = applDate;
    }

    public String getAssoLoanId() {
        return assoLoanId;
    }

    public void setAssoLoanId(String assoLoanId) {
        this.assoLoanId = assoLoanId;
    }

    public String getCeref() {
        return ceref;
    }

    public void setCeref(String ceref) {
        this.ceref = ceref;
    }

    public String getFeeReceivedMaster() {
        return feeReceivedMaster;
    }

    public void setFeeReceivedMaster(String feeReceivedMaster) {
        this.feeReceivedMaster = feeReceivedMaster;
    }

    public Date getFeePaymentReceiptDate() {
        return feePaymentReceiptDate;
    }

    public void setFeePaymentReceiptDate(Date feePaymentReceiptDate) {
        this.feePaymentReceiptDate = feePaymentReceiptDate;
    }

    public Date getAcknowledgingApplicationReceiptDate() {
        return acknowledgingApplicationReceiptDate;
    }

    public void setAcknowledgingApplicationReceiptDate(Date acknowledgingApplicationReceiptDate) {
        this.acknowledgingApplicationReceiptDate = acknowledgingApplicationReceiptDate;
    }

    public Date getAcknowledgingFeePaymentReceiptDate() {
        return acknowledgingFeePaymentReceiptDate;
    }

    public void setAcknowledgingFeePaymentReceiptDate(Date acknowledgingFeePaymentReceiptDate) {
        this.acknowledgingFeePaymentReceiptDate = acknowledgingFeePaymentReceiptDate;
    }

    public String getMeetPerformancePledge() {
        return meetPerformancePledge;
    }

    public void setMeetPerformancePledge(String meetPerformancePledge) {
        this.meetPerformancePledge = meetPerformancePledge;
    }

    public Date getLoanAgreeDate() {
        return loanAgreeDate;
    }

    public void setLoanAgreeDate(Date loanAgreeDate) {
        this.loanAgreeDate = loanAgreeDate;
    }

    public String getCurrencyMaster() {
        return currencyMaster;
    }

    public void setCurrencyMaster(String currencyMaster) {
        this.currencyMaster = currencyMaster;
    }

    public BigDecimal getLoanFacilityAmt() {
        return loanFacilityAmt;
    }

    public void setLoanFacilityAmt(BigDecimal loanFacilityAmt) {
        this.loanFacilityAmt = loanFacilityAmt;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public String getLenderName1() {
        return lenderName1;
    }

    public void setLenderName1(String lenderName1) {
        this.lenderName1 = lenderName1;
    }

    public String getLenderName2() {
        return lenderName2;
    }

    public void setLenderName2(String lenderName2) {
        this.lenderName2 = lenderName2;
    }

    public String getLenderName3() {
        return lenderName3;
    }

    public void setLenderName3(String lenderName3) {
        this.lenderName3 = lenderName3;
    }

    public String getLenderName4() {
        return lenderName4;
    }

    public void setLenderName4(String lenderName4) {
        this.lenderName4 = lenderName4;
    }

    public String getLenderName5() {
        return lenderName5;
    }

    public void setLenderName5(String lenderName5) {
        this.lenderName5 = lenderName5;
    }

    public String getLoanTypeNewappSubmission() {
        return loanTypeNewappSubmission;
    }

    public void setLoanTypeNewappSubmission(String loanTypeNewappSubmission) {
        this.loanTypeNewappSubmission = loanTypeNewappSubmission;
    }

    public String getCurrencyNewappSubmission() {
        return currencyNewappSubmission;
    }

    public void setCurrencyNewappSubmission(String currencyNewappSubmission) {
        this.currencyNewappSubmission = currencyNewappSubmission;
    }

    public BigDecimal getLoanFacilityAmountNewappSubmission() {
        return loanFacilityAmountNewappSubmission;
    }

    public void setLoanFacilityAmountNewappSubmission(BigDecimal loanFacilityAmountNewappSubmission) {
        this.loanFacilityAmountNewappSubmission = loanFacilityAmountNewappSubmission;
    }

    public String getInterestRateNewappSubmission() {
        return interestRateNewappSubmission;
    }

    public void setInterestRateNewappSubmission(String interestRateNewappSubmission) {
        this.interestRateNewappSubmission = interestRateNewappSubmission;
    }

    public String getDurationNewappSubmission() {
        return durationNewappSubmission;
    }

    public void setDurationNewappSubmission(String durationNewappSubmission) {
        this.durationNewappSubmission = durationNewappSubmission;
    }

    public String getApplStatusNewapp() {
        return applStatusNewapp;
    }

    public void setApplStatusNewapp(String applStatusNewapp) {
        this.applStatusNewapp = applStatusNewapp;
    }

    public Date getInprincipleApplLetterIssuedDateNewapp() {
        return inprincipleApplLetterIssuedDateNewapp;
    }

    public void setInprincipleApplLetterIssuedDateNewapp(Date inprincipleApplLetterIssuedDateNewapp) {
        this.inprincipleApplLetterIssuedDateNewapp = inprincipleApplLetterIssuedDateNewapp;
    }

    public Date getFinalApplLetterDateNewapp() {
        return finalApplLetterDateNewapp;
    }

    public void setFinalApplLetterDateNewapp(Date finalApplLetterDateNewapp) {
        this.finalApplLetterDateNewapp = finalApplLetterDateNewapp;
    }

    public Date getRejectLetterDateNewapp() {
        return rejectLetterDateNewapp;
    }

    public void setRejectLetterDateNewapp(Date rejectLetterDateNewapp) {
        this.rejectLetterDateNewapp = rejectLetterDateNewapp;
    }

    public Date getReturnLetterDateNewapp() {
        return returnLetterDateNewapp;
    }

    public void setReturnLetterDateNewapp(Date returnLetterDateNewapp) {
        this.returnLetterDateNewapp = returnLetterDateNewapp;
    }

    public Date getApplRejectWithdlDateNewapp() {
        return applRejectWithdlDateNewapp;
    }

    public void setApplRejectWithdlDateNewapp(Date applRejectWithdlDateNewapp) {
        this.applRejectWithdlDateNewapp = applRejectWithdlDateNewapp;
    }

    public Date getLoanAgreementDateNewapp() {
        return loanAgreementDateNewapp;
    }

    public void setLoanAgreementDateNewapp(Date loanAgreementDateNewapp) {
        this.loanAgreementDateNewapp = loanAgreementDateNewapp;
    }

    public Date getLoanExpiryDateNewapp() {
        return loanExpiryDateNewapp;
    }

    public void setLoanExpiryDateNewapp(Date loanExpiryDateNewapp) {
        this.loanExpiryDateNewapp = loanExpiryDateNewapp;
    }

    public String getApprovedCurrencyNewapp() {
        return approvedCurrencyNewapp;
    }

    public void setApprovedCurrencyNewapp(String approvedCurrencyNewapp) {
        this.approvedCurrencyNewapp = approvedCurrencyNewapp;
    }

    public String getApprovedLoanAmountFacilityNewapp() {
        return approvedLoanAmountFacilityNewapp;
    }

    public void setApprovedLoanAmountFacilityNewapp(String approvedLoanAmountFacilityNewapp) {
        this.approvedLoanAmountFacilityNewapp = approvedLoanAmountFacilityNewapp;
    }

    public String getApprovedInterestRateNewapp() {
        return approvedInterestRateNewapp;
    }

    public void setApprovedInterestRateNewapp(String approvedInterestRateNewapp) {
        this.approvedInterestRateNewapp = approvedInterestRateNewapp;
    }

    public String getApprovedDurationNewapp() {
        return approvedDurationNewapp;
    }

    public void setApprovedDurationNewapp(String approvedDurationNewapp) {
        this.approvedDurationNewapp = approvedDurationNewapp;
    }

    public Date getDateOfDrawdownSubmission() {
        return dateOfDrawdownSubmission;
    }

    public void setDateOfDrawdownSubmission(Date dateOfDrawdownSubmission) {
        this.dateOfDrawdownSubmission = dateOfDrawdownSubmission;
    }

    public String getCurrencyDrawdownSubmission() {
        return currencyDrawdownSubmission;
    }

    public void setCurrencyDrawdownSubmission(String currencyDrawdownSubmission) {
        this.currencyDrawdownSubmission = currencyDrawdownSubmission;
    }

    public String getDrawdownAmtSubmission() {
        return drawdownAmtSubmission;
    }

    public void setDrawdownAmtSubmission(String drawdownAmtSubmission) {
        this.drawdownAmtSubmission = drawdownAmtSubmission;
    }

    public String getApplStatusDrawdown() {
        return applStatusDrawdown;
    }

    public void setApplStatusDrawdown(String applStatusDrawdown) {
        this.applStatusDrawdown = applStatusDrawdown;
    }

    public Date getConfirmLetterIssueDateDrawdown() {
        return confirmLetterIssueDateDrawdown;
    }

    public void setConfirmLetterIssueDateDrawdown(Date confirmLetterIssueDateDrawdown) {
        this.confirmLetterIssueDateDrawdown = confirmLetterIssueDateDrawdown;
    }

    public Date getRejectLetterDateDrawdown() {
        return rejectLetterDateDrawdown;
    }

    public void setRejectLetterDateDrawdown(Date rejectLetterDateDrawdown) {
        this.rejectLetterDateDrawdown = rejectLetterDateDrawdown;
    }

    public Date getReturnLetterDateDrawdown() {
        return returnLetterDateDrawdown;
    }

    public void setReturnLetterDateDrawdown(Date returnLetterDateDrawdown) {
        this.returnLetterDateDrawdown = returnLetterDateDrawdown;
    }

    public Date getApplRejectWithdlDateDrawdown() {
        return applRejectWithdlDateDrawdown;
    }

    public void setApplRejectWithdlDateDrawdown(Date applRejectWithdlDateDrawdown) {
        this.applRejectWithdlDateDrawdown = applRejectWithdlDateDrawdown;
    }

    public Date getApprovedDateOfDrawdown() {
        return approvedDateOfDrawdown;
    }

    public void setApprovedDateOfDrawdown(Date approvedDateOfDrawdown) {
        this.approvedDateOfDrawdown = approvedDateOfDrawdown;
    }

    public String getCurrencyApprovedDrawdown() {
        return currencyApprovedDrawdown;
    }

    public void setCurrencyApprovedDrawdown(String currencyApprovedDrawdown) {
        this.currencyApprovedDrawdown = currencyApprovedDrawdown;
    }

    public BigDecimal getApprovedDrawdownAmt() {
        return approvedDrawdownAmt;
    }

    public void setApprovedDrawdownAmt(BigDecimal approvedDrawdownAmt) {
        this.approvedDrawdownAmt = approvedDrawdownAmt;
    }

    public Date getExpiryDateOfTheLoanRenewalSubmission() {
        return expiryDateOfTheLoanRenewalSubmission;
    }

    public void setExpiryDateOfTheLoanRenewalSubmission(Date expiryDateOfTheLoanRenewalSubmission) {
        this.expiryDateOfTheLoanRenewalSubmission = expiryDateOfTheLoanRenewalSubmission;
    }

    public String getApplStatusRenewal() {
        return applStatusRenewal;
    }

    public void setApplStatusRenewal(String applStatusRenewal) {
        this.applStatusRenewal = applStatusRenewal;
    }

    public Date getApplLetterIssueDateRenewal() {
        return applLetterIssueDateRenewal;
    }

    public void setApplLetterIssueDateRenewal(Date applLetterIssueDateRenewal) {
        this.applLetterIssueDateRenewal = applLetterIssueDateRenewal;
    }

    public Date getFinalApplLetterDateRenewal() {
        return finalApplLetterDateRenewal;
    }

    public void setFinalApplLetterDateRenewal(Date finalApplLetterDateRenewal) {
        this.finalApplLetterDateRenewal = finalApplLetterDateRenewal;
    }

    public Date getRejectLetterDateRenewal() {
        return rejectLetterDateRenewal;
    }

    public void setRejectLetterDateRenewal(Date rejectLetterDateRenewal) {
        this.rejectLetterDateRenewal = rejectLetterDateRenewal;
    }

    public Date getReturnLetterDateRenewal() {
        return returnLetterDateRenewal;
    }

    public void setReturnLetterDateRenewal(Date returnLetterDateRenewal) {
        this.returnLetterDateRenewal = returnLetterDateRenewal;
    }

    public Date getLoanExpiryDateApprovrdRenewal() {
        return loanExpiryDateApprovrdRenewal;
    }

    public void setLoanExpiryDateApprovrdRenewal(Date loanExpiryDateApprovrdRenewal) {
        this.loanExpiryDateApprovrdRenewal = loanExpiryDateApprovrdRenewal;
    }

    public String getProposeVariationVarSubmission() {
        return proposeVariationVarSubmission;
    }

    public void setProposeVariationVarSubmission(String proposeVariationVarSubmission) {
        this.proposeVariationVarSubmission = proposeVariationVarSubmission;
    }

    public String getApplStatusVar() {
        return applStatusVar;
    }

    public void setApplStatusVar(String applStatusVar) {
        this.applStatusVar = applStatusVar;
    }

    public Date getApplLetterIssuedDateVar() {
        return applLetterIssuedDateVar;
    }

    public void setApplLetterIssuedDateVar(Date applLetterIssuedDateVar) {
        this.applLetterIssuedDateVar = applLetterIssuedDateVar;
    }

    public Date getRejectLetterIssuedDateVar() {
        return rejectLetterIssuedDateVar;
    }

    public void setRejectLetterIssuedDateVar(Date rejectLetterIssuedDateVar) {
        this.rejectLetterIssuedDateVar = rejectLetterIssuedDateVar;
    }

    public Date getReturnLetterDateVar() {
        return returnLetterDateVar;
    }

    public void setReturnLetterDateVar(Date returnLetterDateVar) {
        this.returnLetterDateVar = returnLetterDateVar;
    }

    public Date getApplRejectWithdlDateVar() {
        return applRejectWithdlDateVar;
    }

    public void setApplRejectWithdlDateVar(Date applRejectWithdlDateVar) {
        this.applRejectWithdlDateVar = applRejectWithdlDateVar;
    }

    public String getApplVariationLoanAgreement() {
        return applVariationLoanAgreement;
    }

    public void setApplVariationLoanAgreement(String applVariationLoanAgreement) {
        this.applVariationLoanAgreement = applVariationLoanAgreement;
    }

    public Date getDateOfRepaymentSubmission() {
        return dateOfRepaymentSubmission;
    }

    public void setDateOfRepaymentSubmission(Date dateOfRepaymentSubmission) {
        this.dateOfRepaymentSubmission = dateOfRepaymentSubmission;
    }

    public String getCurrencyRepaymentSubmission() {
        return currencyRepaymentSubmission;
    }

    public void setCurrencyRepaymentSubmission(String currencyRepaymentSubmission) {
        this.currencyRepaymentSubmission = currencyRepaymentSubmission;
    }

    public BigDecimal getRepaymentAmtSubmission() {
        return repaymentAmtSubmission;
    }

    public void setRepaymentAmtSubmission(BigDecimal repaymentAmtSubmission) {
        this.repaymentAmtSubmission = repaymentAmtSubmission;
    }

    public String getApplStatusRepayment() {
        return applStatusRepayment;
    }

    public void setApplStatusRepayment(String applStatusRepayment) {
        this.applStatusRepayment = applStatusRepayment;
    }

    public Date getPriorConsentLetterIssuedDateRepayment() {
        return priorConsentLetterIssuedDateRepayment;
    }

    public void setPriorConsentLetterIssuedDateRepayment(Date priorConsentLetterIssuedDateRepayment) {
        this.priorConsentLetterIssuedDateRepayment = priorConsentLetterIssuedDateRepayment;
    }

    public Date getApplLetterIssuedDateRepayment() {
        return applLetterIssuedDateRepayment;
    }

    public void setApplLetterIssuedDateRepayment(Date applLetterIssuedDateRepayment) {
        this.applLetterIssuedDateRepayment = applLetterIssuedDateRepayment;
    }

    public Date getRejectLetterIssuedDateRepayment() {
        return rejectLetterIssuedDateRepayment;
    }

    public void setRejectLetterIssuedDateRepayment(Date rejectLetterIssuedDateRepayment) {
        this.rejectLetterIssuedDateRepayment = rejectLetterIssuedDateRepayment;
    }

    public Date getReturnLetterDateRepayment() {
        return returnLetterDateRepayment;
    }

    public void setReturnLetterDateRepayment(Date returnLetterDateRepayment) {
        this.returnLetterDateRepayment = returnLetterDateRepayment;
    }

    public Date getApplRejectWithdlDateRepayment() {
        return applRejectWithdlDateRepayment;
    }

    public void setApplRejectWithdlDateRepayment(Date applRejectWithdlDateRepayment) {
        this.applRejectWithdlDateRepayment = applRejectWithdlDateRepayment;
    }

    public Date getApprovedDateOfRepayment() {
        return approvedDateOfRepayment;
    }

    public void setApprovedDateOfRepayment(Date approvedDateOfRepayment) {
        this.approvedDateOfRepayment = approvedDateOfRepayment;
    }

    public String getApprovedCurrencyRepayment() {
        return approvedCurrencyRepayment;
    }

    public void setApprovedCurrencyRepayment(String approvedCurrencyRepayment) {
        this.approvedCurrencyRepayment = approvedCurrencyRepayment;
    }

    public BigDecimal getApprovedRepaymentAmt() {
        return approvedRepaymentAmt;
    }

    public void setApprovedRepaymentAmt(BigDecimal approvedRepaymentAmt) {
        this.approvedRepaymentAmt = approvedRepaymentAmt;
    }

    public String getProposeAmendmentConditionSubmission() {
        return proposeAmendmentConditionSubmission;
    }

    public void setProposeAmendmentConditionSubmission(String proposeAmendmentConditionSubmission) {
        this.proposeAmendmentConditionSubmission = proposeAmendmentConditionSubmission;
    }

    public String getApplStatusAmendmentCondition() {
        return applStatusAmendmentCondition;
    }

    public void setApplStatusAmendmentCondition(String applStatusAmendmentCondition) {
        this.applStatusAmendmentCondition = applStatusAmendmentCondition;
    }

    public Date getInprincipleApplLetterIssuedDateAmendmentCondition() {
        return inprincipleApplLetterIssuedDateAmendmentCondition;
    }

    public void setInprincipleApplLetterIssuedDateAmendmentCondition(Date inprincipleApplLetterIssuedDateAmendmentCondition) {
        this.inprincipleApplLetterIssuedDateAmendmentCondition = inprincipleApplLetterIssuedDateAmendmentCondition;
    }

    public Date getApplLetterIssuedDateAmendmentCondition() {
        return applLetterIssuedDateAmendmentCondition;
    }

    public void setApplLetterIssuedDateAmendmentCondition(Date applLetterIssuedDateAmendmentCondition) {
        this.applLetterIssuedDateAmendmentCondition = applLetterIssuedDateAmendmentCondition;
    }

    public Date getRejectLetterIssuedDateAmendmentCondition() {
        return rejectLetterIssuedDateAmendmentCondition;
    }

    public void setRejectLetterIssuedDateAmendmentCondition(Date rejectLetterIssuedDateAmendmentCondition) {
        this.rejectLetterIssuedDateAmendmentCondition = rejectLetterIssuedDateAmendmentCondition;
    }

    public Date getReturnLetterDateAmendmentCondition() {
        return returnLetterDateAmendmentCondition;
    }

    public void setReturnLetterDateAmendmentCondition(Date returnLetterDateAmendmentCondition) {
        this.returnLetterDateAmendmentCondition = returnLetterDateAmendmentCondition;
    }

    public Date getApplRejectWithdlDateAmendmentCondition() {
        return applRejectWithdlDateAmendmentCondition;
    }

    public void setApplRejectWithdlDateAmendmentCondition(Date applRejectWithdlDateAmendmentCondition) {
        this.applRejectWithdlDateAmendmentCondition = applRejectWithdlDateAmendmentCondition;
    }

    public String getApprovedAmendmentCondition() {
        return approvedAmendmentCondition;
    }

    public void setApprovedAmendmentCondition(String approvedAmendmentCondition) {
        this.approvedAmendmentCondition = approvedAmendmentCondition;
    }

    public Date getDateOfTermSubmission() {
        return dateOfTermSubmission;
    }

    public void setDateOfTermSubmission(Date dateOfTermSubmission) {
        this.dateOfTermSubmission = dateOfTermSubmission;
    }

    public String getApplStatusTerm() {
        return applStatusTerm;
    }

    public void setApplStatusTerm(String applStatusTerm) {
        this.applStatusTerm = applStatusTerm;
    }

    public Date getApplLetterIssueDateTerm() {
        return applLetterIssueDateTerm;
    }

    public void setApplLetterIssueDateTerm(Date applLetterIssueDateTerm) {
        this.applLetterIssueDateTerm = applLetterIssueDateTerm;
    }

    public Date getFinalApplLetterDateTerm() {
        return finalApplLetterDateTerm;
    }

    public void setFinalApplLetterDateTerm(Date finalApplLetterDateTerm) {
        this.finalApplLetterDateTerm = finalApplLetterDateTerm;
    }

    public Date getRejectLetterDateTerm() {
        return rejectLetterDateTerm;
    }

    public void setRejectLetterDateTerm(Date rejectLetterDateTerm) {
        this.rejectLetterDateTerm = rejectLetterDateTerm;
    }

    public Date getReturnLetterDateTerm() {
        return returnLetterDateTerm;
    }

    public void setReturnLetterDateTerm(Date returnLetterDateTerm) {
        this.returnLetterDateTerm = returnLetterDateTerm;
    }

    public Date getLoanExpiryDateApprovrdTerm() {
        return loanExpiryDateApprovrdTerm;
    }

    public void setLoanExpiryDateApprovrdTerm(Date loanExpiryDateApprovrdTerm) {
        this.loanExpiryDateApprovrdTerm = loanExpiryDateApprovrdTerm;
    }

    public Date getApprovedDateOfTerm() {
        return approvedDateOfTerm;
    }

    public void setApprovedDateOfTerm(Date approvedDateOfTerm) {
        this.approvedDateOfTerm = approvedDateOfTerm;
    }

    public Date getLastApplExtRenewDateMovement() {
        return lastApplExtRenewDateMovement;
    }

    public void setLastApplExtRenewDateMovement(Date lastApplExtRenewDateMovement) {
        this.lastApplExtRenewDateMovement = lastApplExtRenewDateMovement;
    }

    public Date getLoanExpiryDateMovement() {
        return loanExpiryDateMovement;
    }

    public void setLoanExpiryDateMovement(Date loanExpiryDateMovement) {
        this.loanExpiryDateMovement = loanExpiryDateMovement;
    }

    public String getExpiredLoanMovement() {
        return expiredLoanMovement;
    }

    public void setExpiredLoanMovement(String expiredLoanMovement) {
        this.expiredLoanMovement = expiredLoanMovement;
    }

    public Date getTermDateBorrowerLoanMovement() {
        return termDateBorrowerLoanMovement;
    }

    public void setTermDateBorrowerLoanMovement(Date termDateBorrowerLoanMovement) {
        this.termDateBorrowerLoanMovement = termDateBorrowerLoanMovement;
    }

    public Date getTermDateSfcLoanMovement() {
        return termDateSfcLoanMovement;
    }

    public void setTermDateSfcLoanMovement(Date termDateSfcLoanMovement) {
        this.termDateSfcLoanMovement = termDateSfcLoanMovement;
    }

    public String getVariationOfTermsApprovalLoanMovement() {
        return variationOfTermsApprovalLoanMovement;
    }

    public void setVariationOfTermsApprovalLoanMovement(String variationOfTermsApprovalLoanMovement) {
        this.variationOfTermsApprovalLoanMovement = variationOfTermsApprovalLoanMovement;
    }

    public String getImpositionAmendmentRevocationConditionApprlSfcMovement() {
        return impositionAmendmentRevocationConditionApprlSfcMovement;
    }

    public void setImpositionAmendmentRevocationConditionApprlSfcMovement(String impositionAmendmentRevocationConditionApprlSfcMovement) {
        this.impositionAmendmentRevocationConditionApprlSfcMovement = impositionAmendmentRevocationConditionApprlSfcMovement;
    }

    public String getCurrencyLoanMovement() {
        return currencyLoanMovement;
    }

    public void setCurrencyLoanMovement(String currencyLoanMovement) {
        this.currencyLoanMovement = currencyLoanMovement;
    }

    public BigDecimal getFacilityLimitLoanMovement() {
        return facilityLimitLoanMovement;
    }

    public void setFacilityLimitLoanMovement(BigDecimal facilityLimitLoanMovement) {
        this.facilityLimitLoanMovement = facilityLimitLoanMovement;
    }

    public BigDecimal getLoanAmtMovement() {
        return loanAmtMovement;
    }

    public void setLoanAmtMovement(BigDecimal loanAmtMovement) {
        this.loanAmtMovement = loanAmtMovement;
    }

    public BigDecimal getOutstandingLoanAmtMovement() {
        return outstandingLoanAmtMovement;
    }

    public void setOutstandingLoanAmtMovement(BigDecimal outstandingLoanAmtMovement) {
        this.outstandingLoanAmtMovement = outstandingLoanAmtMovement;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
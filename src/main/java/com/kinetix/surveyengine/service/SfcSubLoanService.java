package com.kinetix.surveyengine.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aspose.cells.*;
import com.kinetix.surveyengine.entity.SubLoan;
import com.kinetix.surveyengine.exception.MyRuntimeException;
import com.kinetix.surveyengine.mapper.SubLoanMapper;
import com.kinetix.surveyengine.restful.RetResponse;
import com.kinetix.surveyengine.restful.RetResult;
import com.kinetix.surveyengine.util.DateUtils;
import com.kinetix.surveyengine.util.StringUtil;
import com.sun.scenario.effect.impl.state.LinearConvolveKernel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author melo
 * @date 2023-06-16 11:53
 */
@Service
public class SfcSubLoanService {

    private Logger logger = LoggerFactory.getLogger(SfcSubLoanService.class);


    @Resource
    private SubLoanMapper subLoanMapper;

    public int insert(SubLoan record) {
        return subLoanMapper.insert(record);
    }


    public RetResult<JSONObject> validation(MultipartFile file, HttpServletResponse response) throws Exception {
        LinkedHashMap<Integer, LinkedHashMap<String, String>> subLoanMap = new LinkedHashMap<>();
        LinkedHashMap<Integer, LinkedHashMap<String, String>> subLoanErrorMap = new LinkedHashMap<>();
        Workbook workbook = new Workbook(file.getInputStream());
        Worksheet worksheet = workbook.getWorksheets().get("Data Migration");
        Worksheet worksheetForm = workbook.getWorksheets().get("Form");
        if (worksheet == null || worksheetForm == null) {
            System.out.println("error");
            throw new MyRuntimeException(500, "worksheet error");
        }
        Cells cellCollection = worksheet.getCells();
        Cells cellCollectionFrom = worksheetForm.getCells();
        int row = cellCollection.getMaxDataRow();
        int formRow = cellCollectionFrom.getMaxDataRow();
        int formColumn = cellCollectionFrom.getMaxDataColumn();
        LinkedHashMap<Integer, LinkedHashMap<String, String>> dataMigrationMap = new LinkedHashMap<>();
        LinkedHashMap<Integer, LinkedHashMap<String, String>> allrowMap = new LinkedHashMap<>();
        LinkedHashMap<String, String> errorAllMap = new LinkedHashMap();
        try {
            HashSet<String> caseIdSet = new HashSet<>();

            for (int i = 2; i <= row-4; i++) {

                LinkedHashMap<String, String> rowMap = new LinkedHashMap<>();

                LinkedHashMap<String, String> hangTrueMap = new LinkedHashMap();

                //rowMap.put("filed", cellCollection.get(i, 0).getStringValue());
                rowMap.put("tableName", cellCollection.get(i, 1).getStringValue());
                rowMap.put("columnName", cellCollection.get(i, 2).getStringValue());
                rowMap.put("dataType", cellCollection.get(i, 3).getStringValue());
                rowMap.put("length/Format", cellCollection.get(i, 4).getStringValue());
                rowMap.put("mandatory", cellCollection.get(i, 5).getStringValue());
                rowMap.put("Mapping", cellCollection.get(i, 7).getStringValue());
                dataMigrationMap.put(i, rowMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Map.Entry<Integer, LinkedHashMap<String, String>> entry : dataMigrationMap.entrySet()) {
            Integer key = entry.getKey();
            LinkedHashMap<String, String> value = entry.getValue();
            String mapping = value.get("Mapping");
            if (mapping.equals("AB")){
                System.out.printf("----");
            }
            Integer column = StringUtil.columnToIndex(mapping);
            for (int j = 3; j <= formRow; j++) {
                Cell cell = cellCollectionFrom.get(j, column);
                LinkedHashMap<String, String> rowMap = new LinkedHashMap<>();
                checkCell(cell, value, errorAllMap, rowMap);
                allrowMap.put(j, rowMap);
            }
        }
   /*     JSONObject jsonObject = null;
        if (!subLoanErrorMap.isEmpty()) {
            jsonObject = JSONObject.parseObject(JSON.toJSONString(subLoanErrorMap));

            System.out.println(JSON.toJSONString(subLoanErrorMap));

            return RetResponse.say(500, "error", jsonObject);
        } else {
            saveSubLoanMap(subLoanMap, response);
            return RetResponse.say(200, "success", jsonObject);
        }*/
        return null;
    }

    private void checkCell(Cell cell, LinkedHashMap<String, String> valueMap, LinkedHashMap<String, String> errorMap, LinkedHashMap<String, String> rowMap) {

        String dataType = valueMap.get("dataType");

        if (dataType.equals("VARCHAR2")) {
            checkVarcharCell(cell, valueMap, errorMap, rowMap);
        } else if (dataType.equals("DATE")) {
            checkDateCell(cell, valueMap, errorMap, rowMap);
        } else if (dataType.equals("NUMBER")) {
            checkNumber(cell, valueMap, errorMap, rowMap);
        }
    }

    private void checkNumber(Cell cell, LinkedHashMap<String, String> valueMap, LinkedHashMap<String, String> errorMap, LinkedHashMap<String, String> rowMap) {

        int row = cell.getRow() + 1;
        String column = valueMap.get("Mapping");
        String mandatory = valueMap.get("mandatory");
        String errorMessage = "";
        String numberCell = (cell == null || cell.getValue() == null) ? null : cell.getValue().toString();

        if (mandatory.equals("Y") && StringUtils.isEmpty(numberCell)) {
            errorMessage = "The " + row + "row and the " + column + " column are empty";
            logger.error(errorMessage);
            errorMap.put(column, errorMessage);
        } else {
            if (numberCell != null) {
                //获取单元格类型
                int type = cell.getType();
                if (CellValueType.IS_NUMERIC != type) {
                    errorMessage = "Type error at line " + row + "column " + column + " numeric type required";
                    logger.error(errorMessage);
                    errorMap.put(column, errorMessage);
                } else {
                    BigDecimal numberValue = new BigDecimal(cell.getValue().toString());
                    rowMap.put(column, numberValue.toPlainString());
                }
            }
        }
        if (StringUtils.isEmpty(errorMessage) && rowMap.get(column) == null) {
            rowMap.put(column, null);
        }
        return;

    }

    private void checkDateCell(Cell cell, LinkedHashMap<String, String> valueMap, LinkedHashMap<String, String> errorMap, LinkedHashMap<String, String> rowMap) {

        int row = cell.getRow() + 1;
        String column = valueMap.get("Mapping");
        String mandatory = valueMap.get("mandatory");
        String errorMessage = "";
        String dateCell = (cell == null || cell.getValue() == null || cell.getValue().toString().trim().equals("")) ? null : cell.getValue().toString();
        if (StringUtils.isEmpty(dateCell) && mandatory.equals("Y")) {
            errorMessage = "The " + row + "row and the " + column + " column are empty";
            logger.error(errorMessage);
            errorMap.put(column, errorMessage);
        } else {
            if (dateCell != null) {
                if (dateCell.contains(" ")) {
                    errorMessage = "There is a space in line " + row + ",column " + column;
                    logger.error(errorMessage);
                    errorMap.put(column, errorMessage);
                } else if (!StringUtil.checkDateFormat(dateCell)) {
                    errorMessage = "The time format of the " + row + " row and the  " + column + "  column is wrong";
                    logger.error(errorMessage);
                    errorMap.put(column, errorMessage);
                }
            }
        }
        if (StringUtils.isEmpty(errorMessage)) {
            rowMap.put(column, dateCell);
        }
        return;
    }

    private void checkVarcharCell(Cell cell, LinkedHashMap<String, String> valueMap, LinkedHashMap<String, String> errorMap, LinkedHashMap<String, String> rowMap) {

        int row = cell.getRow() + 1;
        String column = valueMap.get("Mapping");
        String mandatory = valueMap.get("mandatory");
        String errorMessage = "";

        String length = valueMap.get("length/Format").trim();
        if (mandatory.equals("Y")) {
            if (cell == null || cell.getValue() == null || "".equals(StringUtils.isEmpty(cell.getValue().toString()))) {
                errorMessage = "The " + row + "row and the " + column + " column can not be empty";
                logger.error(errorMessage);
                errorMap.put(column, errorMessage);
            } else {
                if (cell.getValue().toString().length() > Integer.parseInt(length)) {
                    errorMessage = "The length of the " + row + " row and the " + column + " column is greater than" + length;
                    logger.error(errorMessage);
                    errorMap.put(column, errorMessage);
                } else {
                    //  System.out.println(cell.getValue().toString() + " , ");
                    rowMap.put(column, cell.getValue().toString());
                }
            }
        } else {

            if (cell != null && cell.getValue() != null) {

                if (cell.getValue().toString().length() > Integer.parseInt(length)) {
                    errorMessage = "The length of the " + row + " row and the " + column + " column is greater than " + length;
                    logger.error(errorMessage);
                    errorMap.put(column, errorMessage);
                } else {
                    errorMap.put(column, cell.getValue().toString());
                }
            }
        }
        if (StringUtils.isEmpty(errorMessage) && rowMap.get(column) == null) {
            rowMap.put(column, null);
        }
        return;

    }

    @Transactional(rollbackFor = Exception.class)
    public void saveSubLoanMap(LinkedHashMap<Integer, LinkedHashMap<String, String>> subLoanMap, HttpServletResponse response) throws IOException {
        List<SubLoan> subLoanList = new ArrayList<>();
        try {

            for (Map.Entry<Integer, LinkedHashMap<String, String>> mapEntry : subLoanMap.entrySet()) {
                LinkedHashMap<String, String> subMap = mapEntry.getValue();
                SubLoan subLoan = new SubLoan();
                subLoan.setCaseId(subMap.get("A"));
                subLoan.setFormType(subMap.get("B"));
                subLoan.setApplDate(DateUtils.StringToDate(subMap.get("C")));
                subLoan.setAssoLoanId(subMap.get("D"));
                subLoan.setCeref(subMap.get("E"));
                subLoan.setFeeReceivedMaster(subMap.get("F"));
                subLoan.setFeePaymentReceiptDate(DateUtils.StringToDate(subMap.get("G")));
                subLoan.setAcknowledgingApplicationReceiptDate(DateUtils.StringToDate(subMap.get("H")));
                subLoan.setAcknowledgingFeePaymentReceiptDate(DateUtils.StringToDate(subMap.get("I")));
                subLoan.setMeetPerformancePledge(subMap.get("J"));
                subLoan.setLoanAgreeDate(DateUtils.StringToDate(subMap.get("K")));
                subLoan.setCurrencyMaster(subMap.get("L"));
                subLoan.setLoanFacilityAmt(StringUtils.isEmpty(subMap.get("M")) ? null : new BigDecimal(subMap.get("M")));
                subLoan.setBorrower(subMap.get("P"));
                subLoan.setLenderName1(subMap.get("Q"));
                subLoan.setLenderName2(subMap.get("R"));
                subLoan.setLenderName3(subMap.get("S"));
                subLoan.setLenderName4(subMap.get("T"));
                subLoan.setLenderName5(subMap.get("U"));
                subLoan.setLoanTypeNewappSubmission(subMap.get("V"));
                subLoan.setCurrencyNewappSubmission(subMap.get("W"));
                subLoan.setLoanFacilityAmountNewappSubmission(StringUtils.isEmpty(subMap.get("X")) ? null : new BigDecimal(subMap.get("X")));
                subLoan.setInterestRateNewappSubmission(subMap.get("Y"));
                subLoan.setDurationNewappSubmission(subMap.get("Z"));
                subLoan.setApplStatusNewapp(subMap.get("AA"));
                subLoan.setInprincipleApplLetterIssuedDateNewapp(DateUtils.StringToDate(subMap.get("AB")));
                subLoan.setFinalApplLetterDateNewapp(DateUtils.StringToDate(subMap.get("AC")));
                subLoan.setRejectLetterDateNewapp(DateUtils.StringToDate(subMap.get("AD")));
                subLoan.setReturnLetterDateNewapp(DateUtils.StringToDate(subMap.get("AE")));
                subLoan.setApplRejectWithdlDateNewapp(DateUtils.StringToDate(subMap.get("AF")));
                subLoan.setLoanAgreementDateNewapp(DateUtils.StringToDate(subMap.get("AG")));
                subLoan.setLoanExpiryDateNewapp(DateUtils.StringToDate(subMap.get("AH")));
                subLoan.setApprovedCurrencyNewapp(subMap.get("AI"));
                subLoan.setApprovedLoanAmountFacilityNewapp(subMap.get("AJ"));
                subLoan.setApprovedInterestRateNewapp(subMap.get("AK"));
                subLoan.setApprovedDurationNewapp(subMap.get("AL"));
                subLoan.setDateOfDrawdownSubmission(DateUtils.StringToDate(subMap.get("AN")));
                subLoan.setCurrencyDrawdownSubmission(subMap.get("AO"));
                subLoan.setDrawdownAmtSubmission("AP");
                subLoan.setApplStatusDrawdown("AQ");
                subLoan.setConfirmLetterIssueDateDrawdown(DateUtils.StringToDate(subMap.get("AR")));
                subLoan.setRejectLetterDateDrawdown(DateUtils.StringToDate(subMap.get("AS")));
                subLoan.setReturnLetterDateDrawdown(DateUtils.StringToDate(subMap.get("AT")));
                subLoan.setApplRejectWithdlDateDrawdown(DateUtils.StringToDate(subMap.get("AU")));
                subLoan.setApprovedDateOfDrawdown(DateUtils.StringToDate(subMap.get("AV")));
                subLoan.setCurrencyApprovedDrawdown(subMap.get("AW"));
                subLoan.setApprovedDrawdownAmt(StringUtils.isEmpty(subMap.get("AX")) ? null : new BigDecimal(subMap.get("AX")));
                subLoan.setExpiryDateOfTheLoanRenewalSubmission(DateUtils.StringToDate(subMap.get("AZ")));
                subLoan.setApplStatusRenewal(subMap.get("BA"));
                subLoan.setApplLetterIssueDateRenewal(DateUtils.StringToDate(subMap.get("BB")));
                subLoan.setFinalApplLetterDateRenewal(DateUtils.StringToDate(subMap.get("BC")));
                subLoan.setRejectLetterDateRenewal(DateUtils.StringToDate(subMap.get("BD")));
                subLoan.setReturnLetterDateRenewal(DateUtils.StringToDate(subMap.get("BE")));
                subLoan.setLoanExpiryDateApprovrdRenewal(DateUtils.StringToDate(subMap.get("BF")));
                subLoan.setProposeAmendmentConditionSubmission(subMap.get("BH"));
                subLoan.setApplStatusVar(subMap.get("BI"));
                subLoan.setApplLetterIssuedDateVar(DateUtils.StringToDate(subMap.get("BJ")));
                subLoan.setRejectLetterIssuedDateVar(DateUtils.StringToDate(subMap.get("BK")));
                subLoan.setReturnLetterDateVar(DateUtils.StringToDate(subMap.get("BL")));
                subLoan.setApplRejectWithdlDateVar(DateUtils.StringToDate(subMap.get("BM")));
                subLoan.setApplVariationLoanAgreement(subMap.get("BN"));
                subLoan.setDateOfRepaymentSubmission(DateUtils.StringToDate(subMap.get("BP")));
                subLoan.setCurrencyRepaymentSubmission(subMap.get("BQ"));
                subLoan.setRepaymentAmtSubmission(StringUtils.isEmpty(subMap.get("BR")) ? null : new BigDecimal(subMap.get("BR")));
                subLoan.setApplStatusRepayment(subMap.get("BS"));
                subLoan.setPriorConsentLetterIssuedDateRepayment(DateUtils.StringToDate(subMap.get("BT")));
                subLoan.setApplLetterIssuedDateRepayment(DateUtils.StringToDate(subMap.get("BU")));
                subLoan.setRejectLetterIssuedDateRepayment(DateUtils.StringToDate(subMap.get("BV")));
                subLoan.setReturnLetterDateRepayment(DateUtils.StringToDate(subMap.get("BW")));
                subLoan.setApplRejectWithdlDateRepayment(DateUtils.StringToDate(subMap.get("BX")));
                subLoan.setApprovedDateOfRepayment(DateUtils.StringToDate(subMap.get("BY")));
                subLoan.setApprovedCurrencyRepayment(subMap.get("BZ"));
                subLoan.setApprovedRepaymentAmt(StringUtils.isEmpty(subMap.get("CA")) ? null : new BigDecimal(subMap.get("CA")));
                subLoan.setProposeAmendmentConditionSubmission(subMap.get("CC"));
                subLoan.setApplStatusAmendmentCondition(subMap.get("CD"));
                subLoan.setInprincipleApplLetterIssuedDateAmendmentCondition(DateUtils.StringToDate(subMap.get("CE")));
                subLoan.setApplLetterIssuedDateAmendmentCondition(DateUtils.StringToDate(subMap.get("CF")));
                subLoan.setRejectLetterIssuedDateAmendmentCondition(DateUtils.StringToDate(subMap.get("CG")));
                subLoan.setReturnLetterDateAmendmentCondition(DateUtils.StringToDate(subMap.get("CH")));
                subLoan.setApplRejectWithdlDateAmendmentCondition(DateUtils.StringToDate(subMap.get("CI")));
                subLoan.setApprovedAmendmentCondition(subMap.get("CJ"));
                subLoan.setDateOfTermSubmission(DateUtils.StringToDate(subMap.get("CL")));
                subLoan.setApplStatusTerm(subMap.get("CM"));
                subLoan.setApplLetterIssueDateTerm(DateUtils.StringToDate(subMap.get("CN")));
                subLoan.setFinalApplLetterDateTerm(DateUtils.StringToDate(subMap.get("CO")));
                subLoan.setRejectLetterDateTerm(DateUtils.StringToDate(subMap.get("CP")));
                subLoan.setReturnLetterDateTerm(DateUtils.StringToDate(subMap.get("CQ")));
                subLoan.setLoanExpiryDateApprovrdTerm(DateUtils.StringToDate(subMap.get("CR")));
                subLoan.setApprovedDateOfTerm(DateUtils.StringToDate(subMap.get("CS")));
                subLoan.setLastApplExtRenewDateMovement(DateUtils.StringToDate(subMap.get("CU")));
                subLoan.setLoanExpiryDateMovement(DateUtils.StringToDate(subMap.get("CV")));
                subLoan.setExpiredLoanMovement(subMap.get("CW"));
                subLoan.setTermDateBorrowerLoanMovement(DateUtils.StringToDate(subMap.get("CX")));
                subLoan.setTermDateSfcLoanMovement(DateUtils.StringToDate(subMap.get("CY")));
                subLoan.setVariationOfTermsApprovalLoanMovement(subMap.get("CZ"));
                subLoan.setImpositionAmendmentRevocationConditionApprlSfcMovement(subMap.get("DA"));
                subLoan.setCurrencyLoanMovement(subMap.get("DB"));
                subLoan.setFacilityLimitLoanMovement(StringUtils.isEmpty(subMap.get("DC")) ? null : new BigDecimal(subMap.get("DC")));
                subLoan.setLoanAmtMovement(StringUtils.isEmpty(subMap.get("DD")) ? null : new BigDecimal(subMap.get("DD")));
                subLoan.setOutstandingLoanAmtMovement(StringUtils.isEmpty(subMap.get("DE")) ? null : new BigDecimal(subMap.get("DE")));
                subLoanList.add(subLoan);
                // subLoanMapper.insert(subLoan);
            }

            Set<String> subCaseIdSet = new HashSet<>();

            for (SubLoan subLoan : subLoanList) {
                boolean add = subCaseIdSet.add(subLoan.getCaseId());
                if (!add) {
                    throw new MyRuntimeException(500, "caseId duplicate");
                }
            }

            List<String> subLoanScriptList = new ArrayList<>();
            for (SubLoan subLoan : subLoanList) {
                StringBuilder scriptBuilder = new StringBuilder();
                scriptBuilder.append("INSERT INTO YOUR_TABLE_NAME (");
                scriptBuilder.append("CASE_ID,");
                scriptBuilder.append("FORM_TYPE,");
                scriptBuilder.append("APPL_DATE,");
                scriptBuilder.append("ASSO_LOAN_ID,");
                scriptBuilder.append("CEREF,");
                scriptBuilder.append("FEE_RECEIVED_MASTER,");
                scriptBuilder.append("FEE_PAYMENT_RECEIPT_DATE,");
                scriptBuilder.append("ACKNOWLEDGING_APPLICATION_RECEIPT_DATE,");
                scriptBuilder.append("ACKNOWLEDGING_FEE_PAYMENT_RECEIPT_DATE,");
                scriptBuilder.append("MEET_PERFORMANCE_PLEDGE,");
                scriptBuilder.append("LOAN_AGREE_DATE,");
                scriptBuilder.append("CURRENCY_MASTER,");
                scriptBuilder.append("LOAN_FACILITY_AMT,");
                scriptBuilder.append("BORROWER,");
                scriptBuilder.append("LENDER_NAME_1,");
                scriptBuilder.append("LENDER_NAME_2,");
                scriptBuilder.append("LENDER_NAME_3,");
                scriptBuilder.append("LENDER_NAME_4,");
                scriptBuilder.append("LENDER_NAME_5,");
                scriptBuilder.append("LOAN_TYPE_NEWAPP_SUBMISSION,");
                scriptBuilder.append("CURRENCY_NEWAPP_SUBMISSION,");
                scriptBuilder.append("LOAN_FACILITY_AMOUNT_NEWAPP_SUBMISSION,");
                scriptBuilder.append("INTEREST_RATE_NEWAPP_SUBMISSION,");
                scriptBuilder.append("DURATION_NEWAPP_SUBMISSION,");
                scriptBuilder.append("APPL_STATUS_NEWAPP,");
                scriptBuilder.append("INPRINCIPLE_APPL_LETTER_ISSUED_DATE_NEWAPP, ");
                scriptBuilder.append("FINAL_APPL_LETTER_DATE_NEWAPP, ");
                scriptBuilder.append("REJECT_LETTER_DATE_NEWAPP, ");
                scriptBuilder.append("RETURN_LETTER_DATE_NEWAPP, ");
                scriptBuilder.append("APPL_REJECT_WITHDL_DATE_NEWAPP, ");
                scriptBuilder.append("LOAN_AGREEMENT_DATE_NEWAPP, ");
                scriptBuilder.append("LOAN_EXPIRY_DATE_NEWAPP, ");
                scriptBuilder.append("APPROVED_CURRENCY_NEWAPP, ");
                scriptBuilder.append("APPROVED_LOAN_AMOUNT_FACILITY_NEWAPP, ");
                scriptBuilder.append("APPROVED_INTEREST_RATE_NEWAPP, ");
                scriptBuilder.append("APPROVED_DURATION_NEWAPP, ");
                scriptBuilder.append("DATE_OF_DRAWDOWN_SUBMISSION, ");
                scriptBuilder.append("CURRENCY_DRAWDOWN_SUBMISSION, ");
                scriptBuilder.append("DRAWDOWN_AMT_SUBMISSION, ");
                scriptBuilder.append("APPL_STATUS_DRAWDOWN, ");
                scriptBuilder.append("CONFIRM_LETTER_ISSUE_DATE_DRAWDOWN, ");
                scriptBuilder.append("REJECT_LETTER_DATE_DRAWDOWN, ");
                scriptBuilder.append("RETURN_LETTER_DATE_DRAWDOWN, ");
                scriptBuilder.append("APPL_REJECT_WITHDL_DATE_DRAWDOWN, ");
                scriptBuilder.append("APPROVED_DATE_OF_DRAWDOWN, ");
                scriptBuilder.append("CURRENCY_APPROVED_DRAWDOWN, ");
                scriptBuilder.append("APPROVED_DRAWDOWN_AMT, ");
                scriptBuilder.append("EXPIRY_DATE_OF_THE_LOAN_RENEWAL_SUBMISSION, ");
                scriptBuilder.append("APPL_STATUS_RENEWAL, ");
                scriptBuilder.append("APPL_LETTER_ISSUE_DATE_RENEWAL, ");
                scriptBuilder.append("FINAL_APPL_LETTER_DATE_RENEWAL, ");
                scriptBuilder.append("REJECT_LETTER_DATE_RENEWAL, ");
                scriptBuilder.append("RETURN_LETTER_DATE_RENEWAL, ");
                scriptBuilder.append("LOAN_EXPIRY_DATE_APPROVRD_RENEWAL, ");
                scriptBuilder.append("PROPOSE_VARIATION_VAR_SUBMISSION, ");
                scriptBuilder.append("APPL_STATUS_VAR, ");
                scriptBuilder.append("APPL_LETTER_ISSUED_DATE_VAR, ");
                scriptBuilder.append("REJECT_LETTER_ISSUED_DATE_VAR, ");
                scriptBuilder.append("RETURN_LETTER_DATE_VAR, ");
                scriptBuilder.append("APPL_REJECT_WITHDL_DATE_VAR, ");
                scriptBuilder.append("APPL_VARIATION_LOAN_AGREEMENT, ");
                scriptBuilder.append("DATE_OF_REPAYMENT_SUBMISSION, ");
                scriptBuilder.append("CURRENCY_REPAYMENT_SUBMISSION, ");
                scriptBuilder.append("REPAYMENT_AMT_SUBMISSION, ");
                scriptBuilder.append("APPL_STATUS_REPAYMENT, ");
                scriptBuilder.append("PRIOR_CONSENT_LETTER_ISSUED_DATE_REPAYMENT, ");
                scriptBuilder.append("APPL_LETTER_ISSUED_DATE_REPAYMENT, ");
                scriptBuilder.append("REJECT_LETTER_ISSUED_DATE_REPAYMENT, ");
                scriptBuilder.append("RETURN_LETTER_DATE_REPAYMENT, ");
                scriptBuilder.append("APPL_REJECT_WITHDL_DATE_REPAYMENT, ");
                scriptBuilder.append("APPROVED_DATE_OF_REPAYMENT, ");
                scriptBuilder.append("APPROVED_CURRENCY_REPAYMENT, ");
                scriptBuilder.append("APPROVED_REPAYMENT_AMT, ");
                scriptBuilder.append("PROPOSE_AMENDMENT_CONDITION_SUBMISSION, ");
                scriptBuilder.append("APPL_STATUS_AMENDMENT_CONDITION, ");
                scriptBuilder.append("INPRINCIPLE_APPL_LETTER_ISSUED_DATE_AMENDMENT_CONDITION, ");
                scriptBuilder.append("APPL_LETTER_ISSUED_DATE_AMENDMENT_CONDITION, ");
                scriptBuilder.append("REJECT_LETTER_ISSUED_DATE_AMENDMENT_CONDITION, ");
                scriptBuilder.append("RETURN_LETTER_DATE_AMENDMENT_CONDITION, ");
                scriptBuilder.append("APPL_REJECT_WITHDL_DATE_AMENDMENT_CONDITION, ");
                scriptBuilder.append("APPROVED_AMENDMENT_CONDITION, ");
                scriptBuilder.append("DATE_OF_TERM_SUBMISSION, ");
                scriptBuilder.append("APPL_STATUS_TERM, ");
                scriptBuilder.append("APPL_LETTER_ISSUE_DATE_TERM, ");
                scriptBuilder.append("FINAL_APPL_LETTER_DATE_TERM, ");
                scriptBuilder.append("REJECT_LETTER_DATE_TERM, ");
                scriptBuilder.append("RETURN_LETTER_DATE_TERM, ");
                scriptBuilder.append("LOAN_EXPIRY_DATE_APPROVRD_TERM, ");
                scriptBuilder.append("APPROVED_DATE_OF_TERM, ");
                scriptBuilder.append("LAST_APPL_EXT_RENEW_DATE_MOVEMENT, ");
                scriptBuilder.append("LOAN_EXPIRY_DATE_MOVEMENT, ");
                scriptBuilder.append("EXPIRED_LOAN_MOVEMENT, ");
                scriptBuilder.append("TERM_DATE_BORROWER_LOAN_MOVEMENT, ");
                scriptBuilder.append("TERM_DATE_SFC_LOAN_MOVEMENT, ");
                scriptBuilder.append("VARIATION_OF_TERMS_APPROVAL_LOAN_MOVEMENT, ");
                scriptBuilder.append("IMPOSITION_AMENDMENT_REVOCATION_CONDITION_APPRL_SFC_MOVEMENT, ");
                scriptBuilder.append("CURRENCY_LOAN_MOVEMENT, ");
                scriptBuilder.append("FACILITY_LIMIT_LOAN_MOVEMENT, ");
                scriptBuilder.append("LOAN_AMT_MOVEMENT, ");
                scriptBuilder.append("OUTSTANDING_LOAN_AMT_MOVEMENT, ");
                scriptBuilder.append("CREATION_DATE, ");
                scriptBuilder.append("CREATED_BY, ");
                scriptBuilder.append("LAST_UPDATE_DATE, ");
                scriptBuilder.append("LAST_UPDATED_BY)");
                scriptBuilder.append(" VALUES (");
                scriptBuilder.append(stringFormat(subLoan.getCaseId()));
                scriptBuilder.append(stringFormat(subLoan.getFormType()));
                scriptBuilder.append(StringDateFormat(subLoan.getApplDate()));
                scriptBuilder.append(stringFormat(subLoan.getAssoLoanId()));
                scriptBuilder.append(stringFormat(subLoan.getCeref()));
                scriptBuilder.append(stringFormat(subLoan.getFeeReceivedMaster()));
                scriptBuilder.append(StringDateFormat(subLoan.getFeePaymentReceiptDate()));
                scriptBuilder.append(StringDateFormat(subLoan.getAcknowledgingApplicationReceiptDate()));
                scriptBuilder.append(StringDateFormat(subLoan.getAcknowledgingFeePaymentReceiptDate()));
                scriptBuilder.append(stringFormat(subLoan.getMeetPerformancePledge()));
                scriptBuilder.append(StringDateFormat(subLoan.getLoanAgreeDate()));
                scriptBuilder.append(stringFormat(subLoan.getCurrencyMaster()));
                scriptBuilder.append(numberFormat(subLoan.getLoanFacilityAmt()));
                scriptBuilder.append(stringFormat(subLoan.getBorrower()));
                scriptBuilder.append(stringFormat(subLoan.getLenderName1()));
                scriptBuilder.append(stringFormat(subLoan.getLenderName2()));
                scriptBuilder.append(stringFormat(subLoan.getLenderName3()));
                scriptBuilder.append(stringFormat(subLoan.getLenderName4()));
                scriptBuilder.append(stringFormat(subLoan.getLenderName5()));
                scriptBuilder.append(stringFormat(subLoan.getLoanTypeNewappSubmission()));
                scriptBuilder.append(stringFormat(subLoan.getCurrencyNewappSubmission()));
                scriptBuilder.append(subLoan.getLoanFacilityAmountNewappSubmission() + ", ");
                scriptBuilder.append(stringFormat(subLoan.getInterestRateNewappSubmission()));
                scriptBuilder.append(stringFormat(subLoan.getDurationNewappSubmission()));
                scriptBuilder.append(stringFormat(subLoan.getApplStatusNewapp()));
                scriptBuilder.append(StringDateFormat(subLoan.getInprincipleApplLetterIssuedDateNewapp()));

                scriptBuilder.append(StringDateFormat(subLoan.getFinalApplLetterDateNewapp()));
                scriptBuilder.append(StringDateFormat(subLoan.getRejectLetterDateNewapp()));
                scriptBuilder.append(StringDateFormat(subLoan.getReturnLetterDateNewapp()));
                scriptBuilder.append(StringDateFormat(subLoan.getApplRejectWithdlDateNewapp()));
                scriptBuilder.append(StringDateFormat(subLoan.getLoanAgreementDateNewapp()));
                scriptBuilder.append(StringDateFormat(subLoan.getLoanExpiryDateNewapp()));
                scriptBuilder.append(stringFormat(subLoan.getApprovedCurrencyNewapp()));
                scriptBuilder.append(stringFormat(subLoan.getApprovedLoanAmountFacilityNewapp()));
                scriptBuilder.append(stringFormat(subLoan.getApprovedInterestRateNewapp()));
                scriptBuilder.append(stringFormat(subLoan.getApprovedDurationNewapp()));
                scriptBuilder.append(StringDateFormat(subLoan.getDateOfDrawdownSubmission()));
                scriptBuilder.append(stringFormat(subLoan.getCurrencyDrawdownSubmission()));
                scriptBuilder.append(stringFormat(subLoan.getDrawdownAmtSubmission()));
                scriptBuilder.append(stringFormat(subLoan.getApplStatusDrawdown()));
                scriptBuilder.append(StringDateFormat(subLoan.getConfirmLetterIssueDateDrawdown()));
                scriptBuilder.append(StringDateFormat(subLoan.getRejectLetterDateDrawdown()));
                scriptBuilder.append(StringDateFormat(subLoan.getReturnLetterDateDrawdown()));
                scriptBuilder.append(StringDateFormat(subLoan.getApplRejectWithdlDateDrawdown()));
                scriptBuilder.append(StringDateFormat(subLoan.getApprovedDateOfDrawdown()));
                scriptBuilder.append(stringFormat(subLoan.getCurrencyApprovedDrawdown()));
                scriptBuilder.append(numberFormat(subLoan.getApprovedDrawdownAmt()));
                scriptBuilder.append(StringDateFormat(subLoan.getExpiryDateOfTheLoanRenewalSubmission()));
                scriptBuilder.append(stringFormat(subLoan.getApplStatusRenewal()));
                scriptBuilder.append(StringDateFormat(subLoan.getApplLetterIssueDateRenewal()));
                scriptBuilder.append(StringDateFormat(subLoan.getFinalApplLetterDateRenewal()));
                scriptBuilder.append(StringDateFormat(subLoan.getRejectLetterDateRenewal()));
                scriptBuilder.append(StringDateFormat(subLoan.getReturnLetterDateRenewal()));
                scriptBuilder.append(StringDateFormat(subLoan.getLoanExpiryDateApprovrdRenewal()));
                scriptBuilder.append(stringFormat(subLoan.getProposeVariationVarSubmission()));
                scriptBuilder.append(stringFormat(subLoan.getApplStatusVar()));
                scriptBuilder.append(StringDateFormat(subLoan.getApplLetterIssuedDateVar()));
                scriptBuilder.append(StringDateFormat(subLoan.getRejectLetterIssuedDateVar()));
                scriptBuilder.append(StringDateFormat(subLoan.getReturnLetterDateVar()));
                scriptBuilder.append(StringDateFormat(subLoan.getApplRejectWithdlDateVar()));
                scriptBuilder.append(stringFormat(subLoan.getApplVariationLoanAgreement()));
                scriptBuilder.append(StringDateFormat(subLoan.getDateOfRepaymentSubmission()));
                scriptBuilder.append(stringFormat(subLoan.getCurrencyRepaymentSubmission()));
                scriptBuilder.append(numberFormat(subLoan.getRepaymentAmtSubmission()));
                scriptBuilder.append(stringFormat(subLoan.getApplStatusRepayment()));
                scriptBuilder.append(StringDateFormat(subLoan.getPriorConsentLetterIssuedDateRepayment()));
                scriptBuilder.append(StringDateFormat(subLoan.getApplLetterIssuedDateRepayment()));
                scriptBuilder.append(StringDateFormat(subLoan.getRejectLetterIssuedDateRepayment()));
                scriptBuilder.append(StringDateFormat(subLoan.getReturnLetterDateRepayment()));
                scriptBuilder.append(StringDateFormat(subLoan.getApplRejectWithdlDateRepayment()));
                scriptBuilder.append(StringDateFormat(subLoan.getApprovedDateOfRepayment()));
                scriptBuilder.append(stringFormat(subLoan.getApprovedCurrencyRepayment()));
                scriptBuilder.append(numberFormat(subLoan.getApprovedRepaymentAmt()));
                scriptBuilder.append(stringFormat(subLoan.getProposeAmendmentConditionSubmission()));
                scriptBuilder.append(stringFormat(subLoan.getApplStatusAmendmentCondition()));
                scriptBuilder.append(StringDateFormat(subLoan.getInprincipleApplLetterIssuedDateAmendmentCondition()));
                scriptBuilder.append(StringDateFormat(subLoan.getApplLetterIssuedDateAmendmentCondition()));
                scriptBuilder.append(StringDateFormat(subLoan.getRejectLetterIssuedDateAmendmentCondition()));
                scriptBuilder.append(StringDateFormat(subLoan.getReturnLetterDateAmendmentCondition()));
                scriptBuilder.append(StringDateFormat(subLoan.getApplRejectWithdlDateAmendmentCondition()));
                scriptBuilder.append(stringFormat(subLoan.getApprovedAmendmentCondition()));
                scriptBuilder.append(StringDateFormat(subLoan.getDateOfTermSubmission()));
                scriptBuilder.append(stringFormat(subLoan.getApplStatusTerm()));
                scriptBuilder.append(StringDateFormat(subLoan.getApplLetterIssueDateTerm()));
                scriptBuilder.append(StringDateFormat(subLoan.getFinalApplLetterDateTerm()));
                scriptBuilder.append(StringDateFormat(subLoan.getRejectLetterDateTerm()));
                scriptBuilder.append(StringDateFormat(subLoan.getReturnLetterDateTerm()));
                scriptBuilder.append(StringDateFormat(subLoan.getLoanExpiryDateApprovrdTerm()));
                scriptBuilder.append(StringDateFormat(subLoan.getApprovedDateOfTerm()));
                scriptBuilder.append(StringDateFormat(subLoan.getLastApplExtRenewDateMovement()));
                scriptBuilder.append(StringDateFormat(subLoan.getLoanExpiryDateMovement()));
                scriptBuilder.append(stringFormat(subLoan.getExpiredLoanMovement()));
                scriptBuilder.append(StringDateFormat(subLoan.getTermDateBorrowerLoanMovement()));
                scriptBuilder.append(StringDateFormat(subLoan.getTermDateSfcLoanMovement()));
                scriptBuilder.append(stringFormat(subLoan.getVariationOfTermsApprovalLoanMovement()));
                scriptBuilder.append(stringFormat(subLoan.getImpositionAmendmentRevocationConditionApprlSfcMovement()));
                scriptBuilder.append(stringFormat(subLoan.getCurrencyLoanMovement()));
                scriptBuilder.append(numberFormat(subLoan.getFacilityLimitLoanMovement()));
                scriptBuilder.append(numberFormat(subLoan.getLoanAmtMovement()));
                scriptBuilder.append(numberFormat(subLoan.getOutstandingLoanAmtMovement()));
                scriptBuilder.append(StringDateFormat(subLoan.getCreationDate()));
                scriptBuilder.append(stringFormat(subLoan.getCreatedBy()));
                scriptBuilder.append(StringDateFormat(subLoan.getLastUpdateDate()));

                String lastUpdatedBy = subLoan.getLastUpdatedBy() == null ? "null" : subLoan.getLastUpdatedBy().replace("'", "''");
                scriptBuilder.append(lastUpdatedBy);
                scriptBuilder.append(");");
                scriptBuilder.append("\n");
                String script = scriptBuilder.toString();
                subLoanScriptList.add(script);
            }
            StringBuilder sb = new StringBuilder();
            for (String script : subLoanScriptList) {
                sb.append(script).append("\n");
            }

            String content = sb.toString(); // 获取字符串内容
            response.setContentType("text/plain; charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=insert.txt");

            OutputStream outputStream = response.getOutputStream();
            outputStream.write(content.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            throw e;
        }
    }

    private void validationCaseId(Cell CASE_ID_CELL, int row, String column, LinkedHashMap hangErrorMap, LinkedHashMap hangTrueMap, HashSet<String> caseIdSet) {

        String CASE_ID = CASE_ID_CELL.getValue() == null ? null : CASE_ID_CELL.getValue().toString();
        String errorMessage = "";

        if (CASE_ID == null || "".equals(CASE_ID.trim())) {
            errorMessage = "The" + row + "row and the " + column + " column are empty";
            logger.error(errorMessage);
            hangErrorMap.put(column, errorMessage);
        } else if (CASE_ID.trim().contains(" ")) {

            errorMessage = "There is a space in row " + row + "column " + column;
            logger.error(errorMessage);
            hangErrorMap.put(column, errorMessage);
        } else if (CASE_ID.length() > 12) {

            errorMessage = "The length of row " + row + "and column " + column + "cannot exceed 12";
            logger.error(errorMessage);
            hangErrorMap.put(column, errorMessage);
        }
        if (StringUtils.isEmpty(errorMessage)) {
            hangTrueMap.put(column, CASE_ID);
        }
        return;
    }


    public void validationDate(Cell cell, int row, String column, LinkedHashMap hangErrorMap, LinkedHashMap hangTrueMap) {

        String dateCell = (cell == null || cell.getValue() == null) ? null : cell.getValue().toString();
        // System.out.print(dateCell + " , ");
        String errorMessage = "";

        if (!(dateCell == null || dateCell.trim().equals(""))) {
            if (dateCell.contains(" ")) {
                errorMessage = "There is a space in line " + row + ",column " + column;
                logger.error(errorMessage);
                hangErrorMap.put(column, errorMessage);
            } else if (!StringUtil.checkDateFormat(dateCell)) {

                errorMessage = "The time format of the " + row + " row and the  " + column + "  column is wrong";
                logger.error(errorMessage);
                hangErrorMap.put(column, errorMessage);
            }
        }
        if (StringUtils.isEmpty(errorMessage)) {
            hangTrueMap.put(column, dateCell);
        }
        return;
    }


    public void validationDateNotNull(Cell cell, int row, String column, LinkedHashMap hangErrorMap, LinkedHashMap hangTrueMap) {

        String dateCell = (cell == null || cell.getValue() == null) ? null : cell.getValue().toString().trim();
        // System.out.print(dateCell + " , ");
        String errorMessage = "";

        if (StringUtils.isEmpty(dateCell)) {
            //  errorMessage = "第" + hang + "行第" + lie + "列为空";
            errorMessage = "The " + row + "row and the " + column + " column are empty";
            logger.error(errorMessage);
            hangErrorMap.put(column, errorMessage);
        } else {
            //  There is a space in the fourth row and the third column
            if (!(dateCell == null || dateCell.trim().equals(""))) {
                if (dateCell.contains(" ")) {

                    errorMessage = "There is a space in line " + row + ",column " + column;
                    logger.error(errorMessage);
                    hangErrorMap.put(column, errorMessage);
                } else if (!StringUtil.checkDateFormat(dateCell)) {
                    //Incorrect time format at line 4, column 5
                    errorMessage = "Incorrect time format at line " + row + "column " + column;
                    logger.error(errorMessage);
                    hangErrorMap.put(column, errorMessage);
                }
            }
        }

        if (StringUtils.isEmpty(errorMessage)) {
            hangTrueMap.put(column, dateCell);
        }
        return;
    }

    private void validationVARCHAR(Cell cell, int length, int row, String column, LinkedHashMap hangErrorMap, LinkedHashMap hangTrueMap) {
        String errorMessage = "";

        if (cell != null && cell.getValue() != null) {

            if (cell.getValue().toString().length() > length) {
                errorMessage = "The length of the " + row + " row and the " + column + " column is greater than " + length;

                logger.error(errorMessage);
                hangErrorMap.put(column, errorMessage);
            } else {
                //  System.out.println(cell.getValue().toString() + " , ");
                hangTrueMap.put(column, cell.getValue().toString());
            }
        }

        if (StringUtils.isEmpty(errorMessage) && hangTrueMap.get(column) == null) {
            hangTrueMap.put(column, null);
        }
        return;
    }

    private void validationVARCHARNotNull(Cell cell, int length, int row, String column, LinkedHashMap hangErrorMap, LinkedHashMap hangTrueMap) {
        String errorMessage = "";

        if (cell == null || cell.getValue() == null || "".equals(StringUtils.isEmpty(cell.getValue().toString()))) {
            errorMessage = "The " + row + "row and the " + column + " column are empty";
            logger.error(errorMessage);
            hangErrorMap.put(column, errorMessage);
        } else {
            if (cell.getValue() != null) {

                if (cell.getValue().toString().length() > length) {
                    errorMessage = "The length of the " + row + " row and the " + column + " column is greater than" + length;
                    logger.error(errorMessage);
                    hangErrorMap.put(column, errorMessage);
                } else {
                    //  System.out.println(cell.getValue().toString() + " , ");
                    hangTrueMap.put(column, cell.getValue().toString());
                }
            }
        }
        if (StringUtils.isEmpty(errorMessage) && hangTrueMap.get(column) == null) {
            hangTrueMap.put(column, null);
        }
        return;
    }

    private void validationNUMBER(Cell cell, int row, String column, LinkedHashMap hangErrorMap, LinkedHashMap hangTrueMap) {
        String errorMessage = "";

        if (cell != null && cell.getValue() != null) {
            //获取单元格类型
            int type = cell.getType();

            if (CellValueType.IS_NUMERIC != type) {

                errorMessage = "Type error at line " + row + "column " + column + " numeric type required";
                logger.error(errorMessage);
                hangErrorMap.put(column, errorMessage);
            } else {
                BigDecimal LOAN_FACILITY_AMT = new BigDecimal(cell.getValue().toString());
                //  System.out.println(LOAN_FACILITY_AMT.toPlainString());
                hangTrueMap.put(column, LOAN_FACILITY_AMT.toPlainString());
            }
        }

        if (StringUtils.isEmpty(errorMessage) && hangTrueMap.get(column) == null) {
            hangTrueMap.put(column, null);
        }
        return;
    }


    private String stringFormat(String sub) {
        //    scriptBuilder.append("'" + subLoan.getApplStatusTerm() + "', ");
        if (sub == null) {
            return "null,";
        } else {

            if (sub.contains("'")) {
                sub = sub.replace("'", "''");
            }
            return "'" + sub + "', ";
        }
    }

    public String StringDateFormat(Date sub) {
        if (sub == null) {
            return "null,";
        } else {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(sub);

            return ("to_date('" + formattedDate + "', 'YYYY-MM-DD'), ");
        }
    }

    public String numberFormat(BigDecimal sub) {

        if (sub == null) {
            return "null,";
        } else {
            return sub.toString() + ", ";
        }

    }


}

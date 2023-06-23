package com.evamp.saanga.bankmanagement.requestresponse;

import com.evamp.saanga.bankmanagement.model.Transaction;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ExcelReportResponse {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Transaction> transactionList;

    public ExcelReportResponse() {
        workbook = new XSSFWorkbook();
    }

    public XSSFWorkbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(XSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public XSSFSheet getSheet() {
        return sheet;
    }

    public void setSheet(XSSFSheet sheet) {
        this.sheet = sheet;
    }
}

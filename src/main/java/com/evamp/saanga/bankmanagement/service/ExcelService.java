package com.evamp.saanga.bankmanagement.service;

import com.evamp.saanga.bankmanagement.model.Transaction;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ExcelService {

    private XSSFWorkbook workbook;

    private XSSFSheet sheet;
    private List<Transaction> transactionList;


    private void writeHeaderLine() {


        sheet = workbook.createSheet("Transactions");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Transaction ID", style);
        createCell(row, 1, "Transaction Date & Time", style);
        createCell(row, 2, "Sender Account", style);
        createCell(row, 3, "Receiver Account", style);
        createCell(row, 4, "Amount", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }
        else if (value instanceof LocalDateTime){
            cell.setCellValue((LocalDateTime) value);
        }
        else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Transaction transaction : transactionList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, transaction.getId(), style);
            createCell(row, columnCount++, transaction.getDateAndTime(), style);
            createCell(row, columnCount++, transaction.getObj().getaccountNo(), style);
            createCell(row, columnCount++, transaction.getReceiverAccountNo().toString(), style);
            createCell(row, columnCount++, transaction.getAmount(), style);

        }
    }

    public void exportExcel(List<Transaction> transactionList1, int workbookNumber) throws IOException {

        transactionList = transactionList1;
        workbook = new XSSFWorkbook();

        writeHeaderLine();
        writeDataLines();

        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + workbookNumber + "temp.xlsx";


        FileOutputStream outputStream = new FileOutputStream(fileLocation);
        workbook.write(outputStream);
        System.out.println(workbook);
        workbook.close();

    }
}

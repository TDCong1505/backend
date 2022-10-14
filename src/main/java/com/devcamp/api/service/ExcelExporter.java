package com.devcamp.api.service;

import java.util.List;
import java.io.IOException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.devcamp.api.model.Customer;

/**
 * @author HieuHN
 *
 */
public class ExcelExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<Customer> customers;

	/**
	 * Constructor khởi tạo server export danh sách Customer 
	 * @param customers
	 */
	public ExcelExporter(List<Customer> customers) {
		this.customers = customers;
		workbook = new XSSFWorkbook();
	}

	/**
	 * Tạo các ô cho excel file.
	 * @param row
	 * @param columnCount
	 * @param value
	 * @param style
	 */
	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	/**
	 * Khai báo cho sheet và các dòng đầu tiên
	 */
	private void writeHeaderLine() {
		sheet = workbook.createSheet("Customers");

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "Customer ID", style);
		createCell(row, 1, "Phone Number", style);
		createCell(row, 2, "Full Name", style);
		createCell(row, 3, "Address", style);
		createCell(row, 4, "City", style);
		createCell(row, 5, "State", style);
		createCell(row, 6, "Postal Code", style);
		createCell(row, 7, "Country", style);
		createCell(row, 8, "Sale", style);
		createCell(row, 9, "Credit Limit", style);

	}

	/**
	 * fill dữ liệu cho các dòng tiếp theo.
	 */
	private void writeDataLines() {
		int rowCount = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for (Customer user : this.customers) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;

			createCell(row, columnCount++, user.getId(), style);
			createCell(row, columnCount++, user.getPhoneNumber(), style);
			createCell(row, columnCount++, user.getFirstName() + " " + user.getLastName(), style);
			createCell(row, columnCount++, user.getAddress(), style);
			createCell(row, columnCount++, user.getCity(), style);
			createCell(row, columnCount++, user.getState(), style);
			createCell(row, columnCount++, user.getPostalCode(), style);
			createCell(row, columnCount++, user.getCountry(), style);
			createCell(row, columnCount++, user.getSalesRepEmployeeNumber(), style);
			createCell(row, columnCount++, user.getCreditLimit(), style);
		}
	}

	/**
	 * xuất dữ liệu ra dạng file
	 * @param response
	 * @throws IOException
	 */
	public void export(HttpServletResponse response) throws IOException {
		writeHeaderLine();
		writeDataLines();

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();

	}
    
}

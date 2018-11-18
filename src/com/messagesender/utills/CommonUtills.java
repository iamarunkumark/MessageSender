package com.messagesender.utills;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.messagesender.dto.ExcelContentDTO;

public class CommonUtills {
	
	public List<ExcelContentDTO> readExcel(String filePath) {
		try {
			Workbook workbook ;
			InputStream inputStream = null;
			inputStream = new FileInputStream(filePath);
			workbook = WorkbookFactory.create(inputStream);
			Sheet sheet = workbook.getSheetAt(0);
		} catch (IOException | InvalidFormatException e) {
			System.err.println("Exception at ExcelUtills: readExcel() : " + e);
		}
		return Collections.emptyList();
	}
	
	public String frameMessage(ExcelContentDTO excelContentDTO) {
		StringBuilder message = new StringBuilder("");
		message.append("Dear " + excelContentDTO.getToName() + ",\n");
		message.append("Greeting from Anbu!");
		message.append("Your balance amount is : " + excelContentDTO.getAmount());
		return message.toString();
		
	}
	
}

package com.messagesender.utills;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.messagesender.dto.ExcelContentDTO;

public class CommonUtils {
	
	public List<ExcelContentDTO> readExcel(String filePath) {
		List<ExcelContentDTO> excelContentDTOs = new ArrayList<>();
		File excelFile = new File(filePath);
		if ( ! excelFile.exists()) {
			return Collections.emptyList();
		}
		try ( InputStream inputStream =  new FileInputStream(filePath); Workbook workbook = WorkbookFactory.create(inputStream); ){
				Sheet sheet = workbook.getSheetAt(0);
				DataFormatter dataFormatter = new DataFormatter();
				for (Row row: sheet) {
					ExcelContentDTO excelContentDTO = new ExcelContentDTO();
					for(Cell cell: row) {
						String cellValue = dataFormatter.formatCellValue(cell);
						cellValue = isEmpty(cellValue) ? "" : cellValue;
						if ( cell.getColumnIndex() == 0 )
							excelContentDTO.setToName(cellValue);
						else if ( cell.getColumnIndex() == 1 )
							excelContentDTO.setPhoneNumber(cellValue);
						else if ( cell.getColumnIndex() == 2 )
							excelContentDTO.setAmount(cellValue);
		                System.out.print(cellValue + "\t");
		            }
		            System.out.println();
		            //setting message
		            excelContentDTO.setMessage(frameMessage(excelContentDTO));
		            excelContentDTOs.add(excelContentDTO);
		        }
				return excelContentDTOs;
		} catch (IOException e) {
			System.err.println("Exception at ExcelUtills: readExcel() : " + e);
		}
		return Collections.emptyList();
	}
	
	public String frameMessage(ExcelContentDTO excelContentDTO) {
		StringBuilder message = new StringBuilder("");
		message.append("Dear " + excelContentDTO.getToName()  + ",\n");
		message.append("Greeting from Anbu!");
		message.append("Your Outstanding balance amount is Rs." + excelContentDTO.getAmount());
		return message.toString();
	}
	
	public static boolean isEmpty(String value) 
	{
		return (value == null || value.trim().equals("")) ? true : false;
	}
}

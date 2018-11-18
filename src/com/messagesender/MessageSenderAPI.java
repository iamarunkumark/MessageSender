package com.messagesender;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.messagesender.dto.DeliveryStatusDTO;
import com.messagesender.dto.ExcelContentDTO;
import com.messagesender.dto.TextLocalResponseDTO;

public class MessageSenderAPI {
	
	private static final String HASHCODE = "76100f0f25e52cca94ae26453d31458798cd6463";
	private static final String USERNAME = "2233arunkumar@gmail.com";
	private static final String SUCCESS = "SUCCESS";
	private static final String FAILED = "FAILED";
	private static final boolean ISTEST = false;
	
	
	public DeliveryStatusDTO sendBulkSms (List<ExcelContentDTO> excelContentDTOs) {
		DeliveryStatusDTO deliveryStatusDTO = new DeliveryStatusDTO();
		try { 
		long cost = 0;
		long balance = 0;
		List<Integer> failedIndex = new ArrayList<>();
		long numberDelivered = 0;
		for ( int i = 1 ; i < excelContentDTOs.size() ; i++ ) {
			TextLocalResponseDTO textLocalResponseDTO = sendSms(excelContentDTOs.get(i));
			if ( SUCCESS.equalsIgnoreCase(textLocalResponseDTO.getStatus())) {
				cost = cost + textLocalResponseDTO.getCost();
				numberDelivered = numberDelivered + textLocalResponseDTO.getNumMessages();
				balance =  textLocalResponseDTO.getBalance();
			} else {
				failedIndex.add(i);
			}
		}
		if ( ! excelContentDTOs.isEmpty()) {
			deliveryStatusDTO.setCost(cost);
			deliveryStatusDTO.setBalance(balance);
			deliveryStatusDTO.setNumMsgDelivered(numberDelivered);
			deliveryStatusDTO.setFailedIndex(failedIndex);
			deliveryStatusDTO.setStatus(deliveryStatusDTO.getFailedIndex().isEmpty() ? SUCCESS : FAILED);
			return deliveryStatusDTO;
		}
		}
		catch (Exception ex) {
			System.err.println("Exception at MessageSenderAPI:sendBulkSms:" +ex);
		}
		return deliveryStatusDTO;
	}
	
	
	public TextLocalResponseDTO sendSms(ExcelContentDTO excelContentDTO) {
		try {
			// Construct data
			String usernameField = "username=" + USERNAME;
			String hashField = "&hash=" + HASHCODE;
			String message = "&message=" + excelContentDTO.getMessage();
			String sender = "&sender=" + "TXTLCL";
			String numbers = "&numbers=" + excelContentDTO.getPhoneNumber();
			String istest = "&test=" + ISTEST;

			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = usernameField + hashField+ numbers + message + sender + istest;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuilder stringBuilder = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuilder.append(line);
			}
			rd.close();
			System.out.println("API Response: " + stringBuilder.toString());
			ObjectMapper mapper = new ObjectMapper();
			mapper = mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);	
			return mapper.readValue(stringBuilder.toString(), new TypeReference<TextLocalResponseDTO>(){});
		} catch (Exception e) {
			System.err.println("Error SMS "+e);
		}
		return null;
	}
	
	public String sendSms() {
		try {
			// Construct data
			String username = "username=" + USERNAME;
			String hash = "&hash=" + HASHCODE;
			String message = "&message=" + "This is your message";
			String sender = "&sender=" + "TXTLCL";
			String numbers = "&numbers=" + "918861634326";
			String istest = "&test=" + true;

			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = username + hash+ numbers + message + sender + istest;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuilder stringBuilder = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuilder.append(line);
			}
			rd.close();

			return stringBuilder.toString();
		} catch (Exception e) {
			System.err.println("Error SMS "+e);
			return "Error "+e;
		}
	}
}

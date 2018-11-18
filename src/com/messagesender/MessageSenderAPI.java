package com.messagesender;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.messagesender.dto.ExcelContentDTO;

public class MessageSenderAPI {
	
	private final String hashCode = "76100f0f25e52cca94ae26453d31458798cd6463";
	private final String userName = "2233arunkumar@gmail.com";
	
	public String sendSms(ExcelContentDTO excelContentDTO) {
		try {
			// Construct data
			String usernameField = "username=" + userName;
			String hashField = "&hash=" + hashCode;
			String message = "&message=" + excelContentDTO.getMessage();
			String sender = "&sender=" + "TXTLCL";
			String numbers = "&numbers=" + excelContentDTO.getPhoneNumber();
			String istest = "&test=" + true;

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

			return stringBuilder.toString();
		} catch (Exception e) {
			System.err.println("Error SMS "+e);
			return "Error "+e;
		}
	}
	
	public String sendSms() {
		try {
			// Construct data
			String username = "username=" + "2233arunkumar@gmail.com";
			String hash = "&hash=" + "76100f0f25e52cca94ae26453d31458798cd6463";
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

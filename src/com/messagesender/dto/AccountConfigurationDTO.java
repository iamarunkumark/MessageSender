package com.messagesender.dto;

public class AccountConfigurationDTO {

	private String mailId ;
	private String hashId;
	
	public String getMailId() {
		return mailId != null ? mailId : "";
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getHashId() {
		return hashId;
	}
	public void setHashId(String hashId) {
		this.hashId = hashId != null ? hashId : "";
	}
}
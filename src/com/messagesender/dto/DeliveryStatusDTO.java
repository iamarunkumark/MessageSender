package com.messagesender.dto;

import java.util.ArrayList;
import java.util.List;

public class DeliveryStatusDTO {
	private String status = "FAILED";
	private long balance= -1;
	private long cost = -1;
	private long numMsgDelivered = -1;
	List<Integer> failedIndex = new ArrayList<>();
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	public long getCost() {
		return cost;
	}
	public void setCost(long cost) {
		this.cost = cost;
	}
	public long getNumMsgDelivered() {
		return numMsgDelivered;
	}
	public void setNumMsgDelivered(long numMsgDelivered) {
		this.numMsgDelivered = numMsgDelivered;
	}
	public List<Integer> getFailedIndex() {
		return failedIndex;
	}
	public void setFailedIndex(List<Integer> failedIndex) {
		this.failedIndex = failedIndex;
	}

}

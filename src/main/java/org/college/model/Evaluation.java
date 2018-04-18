package org.college.model;

import org.college.enums.Message;

public class Evaluation {
	private Message message;
	private String reason;
	
	public Evaluation(Message message, String reason){
		this.message = message;
		this.reason = reason;

	}

	public Message getMessage() {
		return message;
	}


	public void setMessage(Message message) {
		this.message = message;
	}


	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Override
	public String toString() {
		return "Message : " + this.message + " Reason : " + this.reason;
	}
}


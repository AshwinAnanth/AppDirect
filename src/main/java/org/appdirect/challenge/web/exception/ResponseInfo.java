package org.appdirect.challenge.web.exception;

import com.fasterxml.jackson.annotation.JsonView;

public class ResponseInfo {
	
	public interface SuccessResponse {
	};
	
	public interface SubscriptionSuccessResponse  extends SuccessResponse{
	};
	
	@JsonView(SuccessResponse.class)
	private Boolean success;
	
	private String errorCode;
	
	private String message;
	
	@JsonView(SubscriptionSuccessResponse.class)
	private String accountIdentifier;

	public ResponseInfo() {
		this.success = null;
		this.errorCode = null;
		this.message = null;
	}
	
	public ResponseInfo(Boolean success, String errorCode, String message) {
		this.success = success;
		this.errorCode = errorCode;
		this.message = message;
	}

	public ResponseInfo(Boolean success){
		this.success = success;
	}
	
	public ResponseInfo(Boolean success, String accountIdentifier){
		this.success = success;
		this.accountIdentifier = accountIdentifier;
	}
	
	public Boolean getSuccess() {
		return success;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getMessage() {
		return message;
	}
	
	public String getAccountIdentifier() {
		return accountIdentifier;
	}

}

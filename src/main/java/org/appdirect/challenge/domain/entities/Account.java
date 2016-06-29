package org.appdirect.challenge.domain.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.appdirect.challenge.enums.AccountStatusEnum;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Account {

	@Id
	@GeneratedValue(generator = "accountId")
	@GenericGenerator(name = "accountId", strategy = "uuid")
	private String accountIdentifier;
	
	@Enumerated(EnumType.STRING)
	private AccountStatusEnum status;
	
	public Account(AccountStatusEnum status){
		this.status = status;
	}
	
	public Account(){
	}
	
	public String getAccountIdentifier() {
		return accountIdentifier;
	}
	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}
	public AccountStatusEnum getStatus() {
		return status;
	}
	public void setStatus(AccountStatusEnum status) {
		this.status = status;
	}
}

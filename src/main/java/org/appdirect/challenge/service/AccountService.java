package org.appdirect.challenge.service;

import org.appdirect.challenge.domain.entities.Account;
import org.appdirect.challenge.domain.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  @Autowired
  private AccountRepository accountRepository;

  /**
   * Save account.
   *
   * @param account the account
   * @return the account
   */
  public Account saveAccount(Account account){
    try{
      return accountRepository.save(account);
    }catch(Exception ex){
      throw new DataIntegrityViolationException("Error while creating account", ex);
    }
  }
}

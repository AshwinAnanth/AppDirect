package org.appdirect.challenge.domain.repository;

import org.appdirect.challenge.domain.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository  extends JpaRepository<Account, String>{

  /**
   * Find account by account identifier.
   *
   * @param accountIdentifier the account identifier
   * @return the account
   */
  Account findByAccountIdentifier(String accountIdentifier);

}

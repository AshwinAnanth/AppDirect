package org.appdirect.challenge.service;

import org.appdirect.challenge.domain.entities.Account;
import org.appdirect.challenge.domain.entities.Subscription;
import org.appdirect.challenge.domain.repository.AccountRepository;
import org.appdirect.challenge.domain.repository.OrderRepository;
import org.appdirect.challenge.domain.repository.SubscriptionRepository;
import org.appdirect.challenge.enums.AccountStatusEnum;
import org.appdirect.challenge.utils.JsonUtil;
import org.appdirect.challenge.web.exception.ResponseInfo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

  @Autowired
  private SubscriptionRepository subscriptionRepository;

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private AccountService accountService;

  /**
   * Creates the subscription.
   *
   * @param jsonObj the json obj
   * @return the response info
   */
  public ResponseInfo createSubscription(JSONObject jsonObj){
    Subscription subscription = (Subscription) JsonUtil.deserializeJson(jsonObj.toString(), Subscription.class);
    Account account = accountService.saveAccount(new Account(AccountStatusEnum.ACTIVE));
    subscription.getPayload().setAccount(account);

    try{
      subscriptionRepository.save(subscription);
    }catch(Exception ex){
      throw new DataIntegrityViolationException("Error while creating subscription", ex);
    }
    return new ResponseInfo(true, account.getAccountIdentifier());
  }

  /**
   * Update subscription.
   *
   * @param jsonObj the json obj
   * @return the response info
   */
  public ResponseInfo changeSubscription(JSONObject jsonObj){
    Subscription subscription = (Subscription) JsonUtil.deserializeJson(jsonObj.toString(), Subscription.class);

    String accountId = subscription.getPayload().getAccount().getAccountIdentifier();
    Subscription retrievedSubscription;
    try{
      retrievedSubscription = subscriptionRepository.findByPayloadAccountAccountIdentifier(accountId);
    }catch(Exception ex){
      throw new DataRetrievalFailureException("Error while retrieving subscription with account identifier "+accountId, ex);
    }

    try{
      subscription.setId(retrievedSubscription.getId());
      subscriptionRepository.save(subscription);
    }catch(Exception ex){
      throw new DataIntegrityViolationException("Error while updating subscription with account identifier "+accountId, ex);
    }
    return new ResponseInfo(true);
  }

  /**
   * Cancel subscription.
   *
   * @param jsonObj the json obj
   * @return the response info
   */
  public ResponseInfo cancelSubscription(JSONObject jsonObj){
    Subscription subscription = (Subscription) JsonUtil.deserializeJson(jsonObj.toString(), Subscription.class);

    String accountId = subscription.getPayload().getAccount().getAccountIdentifier();

    try{
      Account account = accountRepository.findByAccountIdentifier(accountId);
      account.setStatus(AccountStatusEnum.INACTIVE);
      accountService.saveAccount(account);
    }catch(Exception ex){
      throw new DataRetrievalFailureException("Account with account identifier "+accountId+" not found.", ex);
    }
    return new ResponseInfo(true);
  }


}

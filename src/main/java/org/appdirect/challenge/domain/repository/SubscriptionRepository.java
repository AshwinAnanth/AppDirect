package org.appdirect.challenge.domain.repository;

import org.appdirect.challenge.domain.entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long>{

  /**
   * Find subscription by account identifier.
   *
   * @param id the id
   * @return the subscription
   */
  Subscription findByPayloadAccountAccountIdentifier(String id);
}

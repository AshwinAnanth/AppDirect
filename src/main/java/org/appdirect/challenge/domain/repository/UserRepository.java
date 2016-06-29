package org.appdirect.challenge.domain.repository;

import org.appdirect.challenge.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

  /**
   * Find user by uuid.
   *
   * @param uuid the uuid
   * @return the user
   */
  User findByUuid(String uuid);
}

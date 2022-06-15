package com.jango.accounts.repository.internal;

import com.jango.accounts.model.internal.Account;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

  Optional<Account> findByAccountId(String accountId);
  List<Account> findByCustomerId(String customerId);

}

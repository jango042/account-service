package com.jango.accounts.model.internal;

import com.jango.accounts.model.Base;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "account")
public class Account extends Base {

  private String customerId;
  private String accountId;
  private Double balance;

}

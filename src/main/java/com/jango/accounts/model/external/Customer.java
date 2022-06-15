package com.jango.accounts.model.external;

import com.jango.accounts.model.Base;
import javax.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Customer extends Base {

  private String customerId;
  @Length(max = 50)
  @Email
  private String email;
  private String phoneNumber;
  private String firstname;
  private String surname;

}

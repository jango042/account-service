package com.capgemini.accounts.config.transaction;

import com.capgemini.accounts.config.customer.CustomerFeignRequestInterceptor;
import com.capgemini.accounts.helpers.FeignErrorDecoder;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class TransactionFeignClientConfig {

  @Value("${capgemini.transacton.secret.key}")
  private String secretKey;

  @Bean
  public RequestInterceptor requestInterceptor() {
    return new CustomerFeignRequestInterceptor(secretKey);
  }

  @Bean
  public ErrorDecoder errorDecoder() {
    return new FeignErrorDecoder();
  }

}

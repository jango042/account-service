package com.jango.accounts.config.customer;

import com.jango.accounts.helpers.FeignErrorDecoder;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class CustomerFeignClientConfig {

  @Value("${capgemini.customer.secret.key}")
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

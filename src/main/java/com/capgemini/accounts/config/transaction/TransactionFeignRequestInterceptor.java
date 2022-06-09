package com.capgemini.accounts.config.transaction;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class TransactionFeignRequestInterceptor implements RequestInterceptor {

  private final String SECRET_KEY;

  public TransactionFeignRequestInterceptor(String apiKey) {
    this.SECRET_KEY = apiKey;
  }

  @Override
  public void apply(RequestTemplate requestTemplate) {
    final String secret = "secret_key";
    requestTemplate.header(secret, SECRET_KEY);
  }


}

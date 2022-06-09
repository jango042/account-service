//package com.capgemini.accounts.security.service;
//
//import com.capgemini.accounts.model.external.Customer;
//import com.capgemini.accounts.repository.external.CustomerRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@AllArgsConstructor
//public class CustomerDetailsServiceImpl  implements UserDetailsService {
//
//  @Autowired
//  private CustomerRepository customerRepository;
//
//  @Override
//  @Transactional
//  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//    Customer customer = customerRepository.findByEmail(email)
//        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));
//
//    return CustomerDetailsImpl.build(customer);
//  }
//}

package org.example.billingservice.feign;


import org.example.billingservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient
public interface CustomerRestClient {
    @GetMapping()
    public Customer getCustomerById(String id);
}

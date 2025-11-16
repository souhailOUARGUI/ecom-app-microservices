package org.example.billingservice.feign;


import org.example.billingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient
public interface ProductRestClient {
    @GetMapping("/products")
//  public String getAllProducts();
    public Product getProductById(String id);

}

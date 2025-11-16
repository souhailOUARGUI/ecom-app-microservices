package org.example.billingservice.entities;


import jakarta.persistence.*;
import lombok.*;
import org.example.billingservice.model.Product;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class ProductItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productId;

    @ManyToOne
//    @JoinColumn(name = "bill_id")
    private Bill bill;
    private int quantity;
    private double unitPrice;

    @Transient
    private Product product;
}

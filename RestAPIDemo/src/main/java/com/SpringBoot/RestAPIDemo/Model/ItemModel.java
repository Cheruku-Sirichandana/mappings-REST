package com.SpringBoot.RestAPIDemo.Model;

import com.SpringBoot.RestAPIDemo.Entity.Admin;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ItemModel {
    private int itemId;
    private String itemName;
    private double itemPrice;
    private int itemQuantity;

    private Admin admin;
}

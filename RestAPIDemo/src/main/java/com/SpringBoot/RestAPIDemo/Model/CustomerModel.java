package com.SpringBoot.RestAPIDemo.Model;

import com.SpringBoot.RestAPIDemo.Entity.Item;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToMany;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class CustomerModel {
    private int customerId;
    private String customerName;
    private String customerMail;
    private String customerPassword;
    private long customerPhone;

    private List<Item> itemList=new ArrayList<>();
}

package com.SpringBoot.RestAPIDemo.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {
    @Id
    private int customerId;
    private String customerName;
    private String customerMail;
    private String customerPassword;
    private long customerPhone;
    @ManyToMany(mappedBy = "customerList",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Item> itemList=new ArrayList<>();

}

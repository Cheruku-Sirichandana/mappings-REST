package com.SpringBoot.RestAPIDemo.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Item {
    @Id
    private int itemId;
    private String itemName;
    private double itemPrice;
    private int itemQuantity;
    @ManyToOne
  @JsonBackReference
    private Admin admin;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Customer> customerList=new ArrayList<>();


}

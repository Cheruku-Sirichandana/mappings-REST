package com.SpringBoot.RestAPIDemo.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    @Id
    private int adminId;
    private String adminName;
    private String adminEmail;
    private String adminUsername;
    private String adminPassword;
    @OneToMany(mappedBy = "admin",cascade = CascadeType.ALL)
   @JsonManagedReference
    private List<Item> itemList=new ArrayList<>();

}

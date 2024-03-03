package com.SpringBoot.RestAPIDemo.Repository;

import com.SpringBoot.RestAPIDemo.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}

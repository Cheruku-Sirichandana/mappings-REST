package com.SpringBoot.RestAPIDemo.Controller;

import com.SpringBoot.RestAPIDemo.Model.CustomerModel;
import com.SpringBoot.RestAPIDemo.Model.ItemModel;
import com.SpringBoot.RestAPIDemo.Service.CustomerServices;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerServices customerServices;
    @PostMapping("/addCustomer")
    public String addCustomer(@RequestBody CustomerModel customerModel){
        customerServices.addCustomer(customerModel);
        return "customer added";
    }
    @DeleteMapping("deleteCustomer/{customerId}")
    public String deleteCustomer(@PathVariable int customerId){
        customerServices.deleteCustomer(customerId);
        return "deleted customer";
    }

    @PutMapping("/updateCustomer/{customerId}")
    public String updateCustomer(@RequestBody CustomerModel customerModel,@PathVariable int customerId){
        customerServices.updateCustomer(customerModel,customerId);
        return "updated customer";

    }
    @GetMapping("/viewCustomers")
    public List<CustomerModel> viewCustomers(){
        return customerServices.viewCustomers();
    }

    @PostMapping("/addItemsToCustomer/{customerId}/{itemId}")
    public String addItemsToCustomer(@PathVariable int itemId,@PathVariable int customerId){
        customerServices.addItemsToCustomer(itemId,customerId);
        return "item added to customer !!!";
    }
    @DeleteMapping("/deleteItemsOfCustomer/{customerId}/{itemId}")
    public String deleteItemsOfCustomer(@PathVariable int customerId,@PathVariable int itemId){
        customerServices.deleteItemsOfCustomer(customerId,itemId);
        return "deleted items of customer";
    }

    @PutMapping("/updateItemsOfCustomer/{customerId}/{currentItemId}/{newItemId}")
    public String updateItemsOfCustomer(@PathVariable int customerId, @PathVariable int currentItemId, @PathVariable int newItemId) {
        return customerServices.updateItemsOfCustomer(customerId, currentItemId, newItemId);
    }


    @GetMapping("/viewItemsOfCustomer/{customerId}")
    public List<ItemModel> viewItemsOfCustomer(@PathVariable int customerId){
       return customerServices.getAllItemsOfCustomer(customerId);

    }
    }
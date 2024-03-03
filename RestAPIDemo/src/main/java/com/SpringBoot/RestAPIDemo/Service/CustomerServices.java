package com.SpringBoot.RestAPIDemo.Service;

import com.SpringBoot.RestAPIDemo.Entity.Admin;
import com.SpringBoot.RestAPIDemo.Entity.Customer;
import com.SpringBoot.RestAPIDemo.Entity.Item;
import com.SpringBoot.RestAPIDemo.Model.CustomerModel;
import com.SpringBoot.RestAPIDemo.Model.ItemModel;
import com.SpringBoot.RestAPIDemo.Repository.AdminRepository;
import com.SpringBoot.RestAPIDemo.Repository.CustomerRepository;
import com.SpringBoot.RestAPIDemo.Repository.ItemRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CustomerServices {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private AdminRepository adminRepository;

    public CustomerModel addCustomer(CustomerModel customerModel) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerModel, customer);
        customerRepository.save(customer);
        return customerModel;
    }

    public String deleteCustomer(int customerId) {
        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
            return "deleted";
        }
        return "customer id not found";
    }

    public CustomerModel updateCustomer(CustomerModel customerModel, int customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);

        if (customer != null) {
            Customer customer1 = new Customer();
            customer1.setCustomerId(customerModel.getCustomerId());
            customer1.setCustomerName(customerModel.getCustomerName());
            customer1.setCustomerMail(customerModel.getCustomerMail());
            customer1.setCustomerPassword(customerModel.getCustomerPassword());
            customer1.setCustomerPhone(customerModel.getCustomerPhone());
            customerRepository.save(customer1);
            BeanUtils.copyProperties(customer, customerModel);
            return customerModel;
        }
        return null;
    }

    public List<CustomerModel> viewCustomers() {
        List<CustomerModel> customers = new ArrayList();

        List<Customer> customerList = customerRepository.findAll();
        customerList.forEach(s -> {
            CustomerModel customerModel = new CustomerModel();
            BeanUtils.copyProperties(s, customerModel);
            customers.add(customerModel);
        });
        //BeanUtils.copyProperties(customerList,customers);
        return customers;

    }

    public String addItemsToCustomer(int itemId, int customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        Item item = itemRepository.findById(itemId).orElse(null);
        List<Item> itemList = customer.getItemList();
        List<Customer> customerList = item.getCustomerList();
        if (customer != null && item != null) {

            itemList.add(item);
            customerList.add(customer);
            customer.setItemList(itemList);
            item.setCustomerList(customerList);
            itemRepository.save(item);
            customerRepository.save(customer);
            return "item succesfully added to a customer";

        }
        return "customer id is not present";

    }

    public String deleteItemsOfCustomer(int customerId, int itemId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        Item item = itemRepository.findById(itemId).orElse(null);

        if (customer != null && item != null) {
            List<Customer> customerList = item.getCustomerList();
            List<Item> itemList = customer.getItemList();
            customerList.remove(customer);
            itemList.remove(item);
            item.setCustomerList(customerList);
            customer.setItemList(itemList);
            itemRepository.save(item);
            customerRepository.save(customer);
            return "deleted";
        }
        return "cannot find customer id";
    }


    public String updateItemsOfCustomer(int customerId, int currentItemId, int newItemId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        Item currentItem = itemRepository.findById(currentItemId).orElse(null);
        Item newItem = itemRepository.findById(newItemId).orElse(null);

        if (customer != null && currentItem != null && newItem != null) {
            List<Item> customerItems = customer.getItemList();
            List<Customer> currentItemCustomers = currentItem.getCustomerList();
            List<Customer> newItemCustomers = newItem.getCustomerList();

            if (customerItems.contains(currentItem) && currentItemCustomers.contains(customer)) {

                currentItemCustomers.remove(customer);
                currentItem.setCustomerList(currentItemCustomers);


                newItemCustomers.add(customer);
                newItem.setCustomerList(newItemCustomers);


                customerItems.remove(currentItem);
                customerItems.add(newItem);
                customer.setItemList(customerItems);


                itemRepository.save(currentItem);
                itemRepository.save(newItem);
                customerRepository.save(customer);

                return "Customer's item association updated successfully";
            }
            else {
                return "Customer is not associated with the current item";
            }

        } else {
            return "Customer, current item, or new item not found";
        }
    }
    public List<ItemModel> getAllItemsOfCustomer(int customerId){
        Customer customer=customerRepository.findById(customerId).orElse(null);
        List<Item> itemList=customer.getItemList();
        List<ItemModel> itemsList=new ArrayList<>();
    itemList.forEach(s->
    {
        ItemModel itemModel=new ItemModel();
        Item item=itemRepository.findById(s.getItemId()).orElse(null);

        BeanUtils.copyProperties(item,itemModel);
        itemsList.add(itemModel);
       // itemRepository.save(item);
    });
    return itemsList;

    }



}



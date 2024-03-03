package com.SpringBoot.RestAPIDemo.Controller;

import com.SpringBoot.RestAPIDemo.Entity.Admin;
import com.SpringBoot.RestAPIDemo.Entity.Item;
import com.SpringBoot.RestAPIDemo.Model.AdminModel;
import com.SpringBoot.RestAPIDemo.Model.ItemModel;
import com.SpringBoot.RestAPIDemo.Service.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private AdminServices adminServices;

    @PostMapping("/addAdmin")
    public String addAdmin(@RequestBody AdminModel adminModel){
         adminServices.saveAdmin(adminModel);
         return "added";

    }
    @DeleteMapping("/deleteAdmin/{adminId}")
    public String deleteAdmin(@PathVariable int adminId){
        adminServices.deleteAdmin(adminId);
        return "deleted";

    }

    @GetMapping("/viewAdmins")
    public List<AdminModel> viewAdmins(){
       return adminServices.getAllAdmins();

    }

    @PutMapping("/updateAdmins/{adminId}")
    public AdminModel updateAdmin(@RequestBody AdminModel adminModel,@PathVariable int adminId){
       return adminServices.updateAdminDetails(adminModel,adminId);

    }
    @PostMapping("/addItemsToAdmin/{adminId}")
    public String addItemsToAdmin(@RequestBody ItemModel itemModel, @PathVariable int adminId){


        return adminServices.addItemsToAdmin(itemModel,adminId);
    }

    @DeleteMapping("/deleteItemsByAdmin/{adminId}")
    public String deleteItemsByAdmin(@RequestBody ItemModel itemModel,@PathVariable int adminId){
        adminServices.adminDeleteItem(itemModel,adminId);
        return "item deleted by admin";
    }

    @PutMapping("/updateItemsByAdmin/{adminId}")
    public String updateItemsByAdmin(@RequestBody ItemModel itemModel,@PathVariable int adminId){
       adminServices.updateItemDetails(itemModel,adminId);
        return "updated";
    }

}

package com.SpringBoot.RestAPIDemo.Service;

import com.SpringBoot.RestAPIDemo.Entity.Admin;
import com.SpringBoot.RestAPIDemo.Entity.Item;
import com.SpringBoot.RestAPIDemo.Model.AdminModel;
import com.SpringBoot.RestAPIDemo.Model.ItemModel;
import com.SpringBoot.RestAPIDemo.Repository.AdminRepository;
import com.SpringBoot.RestAPIDemo.Repository.ItemRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.BeanProperty;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServices {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    ItemRepository itemRepo;

    public AdminModel saveAdmin(AdminModel adminModel) {
        Admin admin = new Admin();
        BeanUtils.copyProperties(adminModel, admin);
        adminRepository.save(admin);
        return adminModel;

    }

    public String deleteAdmin(int adminId) {
        if (adminRepository.existsById(adminId)) {
            adminRepository.deleteById(adminId);
            return "deleted" + adminId;
        } else {
            return "id is not present";
        }
    }

    public List<AdminModel> getAllAdmins() {
        List<Admin> adList = adminRepository.findAll();
        List<AdminModel> adminList = new ArrayList<>();
        adList.forEach(ad -> {
            AdminModel adminModel = new AdminModel();

            BeanUtils.copyProperties(ad, adminModel);
            adminList.add(adminModel);

        });
        return adminList;
    }

    public AdminModel updateAdminDetails(AdminModel adminModel, int adminId) {
        if (adminId == adminModel.getAdminId()) {
            Admin admin = new Admin();
            admin.setAdminId(adminModel.getAdminId());
            admin.setAdminName(adminModel.getAdminName());
            admin.setAdminEmail(adminModel.getAdminEmail());
            admin.setAdminUsername(adminModel.getAdminUsername());
            admin.setAdminPassword(adminModel.getAdminPassword());
            adminRepository.save(admin);
            BeanUtils.copyProperties(admin, adminModel);
            //  adminRepository.save(admin);
            return adminModel;


        }
        return null;

    }

    public String addItemsToAdmin(ItemModel itemModel, int adminId) {
        Admin admin = adminRepository.findById(adminId).orElse(null);
        if (admin != null) {
            itemModel.setAdmin(admin);
            List<Item> itemList = admin.getItemList();
            if (itemList == null) {
                List<Item> itemList1 = new ArrayList<>();
            }
            Item item = new Item();
            BeanUtils.copyProperties(itemModel, item);
            itemList.add(item);
            admin.setItemList(itemList);
            adminRepository.save(admin);
            return "Item added";
        } else {
            return "Admin not found";
        }
    }

    public String adminDeleteItem(ItemModel itemModel, int adminId) {
        Admin admin = adminRepository.findById(adminId).orElse(null);

        if (admin != null) {
            //System.out.println(itemModel.getItemId());
            List<Item> itemList = admin.getItemList();
            Item item = new Item();
            BeanUtils.copyProperties(itemModel, item);
            itemList.removeIf(item1 -> item1.getItemId() == item.getItemId());

            admin.setItemList(itemList);
            adminRepository.save(admin);
            itemRepo.deleteById(item.getItemId());

            return "item deleted";
        } else {
            return "admin is not found";
        }
    }

    public String updateItemDetails(ItemModel itemModel, int adminId) {
        Admin admin = adminRepository.findById(adminId).orElse(null);
        if (admin != null) {
            //List<Item> ItemList2=new ArrayList<>();
            List<Item> itemList = admin.getItemList();
            for (Item item2 : itemList) {
                if (item2.getItemId() == (itemModel.getItemId())) {
                   Item item=new Item();
                   item.setItemId(itemModel.getItemId());
                   item.setItemName(itemModel.getItemName());
                   item.setItemPrice(itemModel.getItemPrice());
                   item.setItemQuantity(itemModel.getItemQuantity());
                    itemRepo.save(item);
                    BeanUtils.copyProperties(item,itemModel);
                    itemList.add(item);

                    admin.setItemList(itemList);
                    adminRepository.save(admin);
                }
            }


            return "updated item";
        } else {
            return "admin not found";
        }
    }
}





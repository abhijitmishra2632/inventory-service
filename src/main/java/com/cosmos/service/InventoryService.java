package com.cosmos.service;

import com.cosmos.model.Inventory;
import com.cosmos.pojo.InventoryGist;
import com.cosmos.pojo.InventoryPojo;
import com.cosmos.pojo.ProductPojo;
import com.cosmos.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService {

    @Autowired
    RestTemplate restTemplate;

    private String productUrl="http://PRODUCTS-SERVICE/product/";

    @Autowired
    private InventoryRepository inventoryRepository;
    public InventoryGist getInventoryGist() {
        List<Inventory> inventoryList = inventoryRepository.findAll();
        List<InventoryPojo> inventoryPojos = new ArrayList<>();
        inventoryList.stream().forEach(i->{

            ProductPojo productGist=restTemplate.getForObject(productUrl+i.getProductId(), ProductPojo.class);

            InventoryPojo inventoryPojo= new InventoryPojo();
            inventoryPojo.setInventory(i);
            inventoryPojo.setProduct(productGist);
            inventoryPojos.add(inventoryPojo);
        });
        return new InventoryGist(inventoryPojos);
    }

    public String saveInventory(Inventory inventory) {
        String str ="Success";
        try{
            inventoryRepository.save(inventory);
        }catch (Exception ex){
            str ="Fail to add";
        }
        return str;
    }
}

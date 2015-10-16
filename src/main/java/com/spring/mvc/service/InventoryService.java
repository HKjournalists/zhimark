package com.spring.mvc.service;

import com.spring.mvc.model.InventoryModel;
import com.spring.mvc.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by liluoqi on 15/9/6.
 */
@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;


    public void updateInventory(int productId, int haiWaiGouAmount, int fisherAmount) {
    }

    public void batchUpdateInventories(List<InventoryModel> inventories) {

    }
}

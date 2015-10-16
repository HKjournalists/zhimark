package com.spring.mvc.repository;

import com.spring.mvc.model.InventoryModel;
import com.spring.mvc.utils.MapUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liluoqi on 15/9/6.
 */
@Repository
public class InventoryRepository extends BaseRepository<InventoryModel> {
    private static final String INSERT_INVENTORY = "insertInventory";
    private static final String GET_INVENTORY_BY_INNER_PRODUCT_ID = "getInventoryByInnerProductId";
    private static final String UPDATE_INVENTORIES = "updateInventories";

    public int insertInventory(InventoryModel inventory) {
        return insert(INSERT_INVENTORY, inventory);
    }

    public List<InventoryModel> getInventoryByProductId(int innerProductId) {
        return queryList(GET_INVENTORY_BY_INNER_PRODUCT_ID, MapUtils.convertToMap("innerProductId", innerProductId));
    }

    //todo
    public int updateInventories(List<InventoryModel> inventories) {
        return update(UPDATE_INVENTORIES, null);
    }
}

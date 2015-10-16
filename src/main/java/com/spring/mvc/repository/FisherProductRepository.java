package com.spring.mvc.repository;

import com.spring.mvc.model.ThreePartyProduct.FisherProductModel;
import com.spring.mvc.utils.MapUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liluoqi on 15/9/10.
 */
@Repository
public class FisherProductRepository extends BaseRepository<FisherProductModel> {
    private static final String BATCH_INSERT_FISHER_PRODUCT = "batchInsertFisherProduct";
    private static final String GET_FISHER_PRODUCT_BY_PRODUCT_ID = "getFisherProductByProductId";
    private static final String GET_FISHER_PRODUCTS_BY_PRODUCT_IDS = "getFisherProductsByProductIds";
    private static final String GET_ALL_FISHER_PRODUCTS = "getAllFisherProducts";

    public FisherProductModel getFisherProductByProductId(int productId) {
        return querySingle(GET_FISHER_PRODUCT_BY_PRODUCT_ID, MapUtils.convertToMap("productId", productId));
    }

    public int batchInsertFisherProduct(List<FisherProductModel> fisherProductList) {
        return batchInsert(BATCH_INSERT_FISHER_PRODUCT, fisherProductList);
    }

    public List<FisherProductModel> getFisherProductsByProductIds(List<Integer> productIds) {
        return queryList(GET_FISHER_PRODUCTS_BY_PRODUCT_IDS, productIds);
    }

    public List<FisherProductModel> getAllFisherProducts() {
        return queryList(GET_ALL_FISHER_PRODUCTS);
    }
}

package com.spring.mvc.repository;

import com.spring.mvc.model.PriceSheetModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liluoqi on 15/9/21.
 */
@Repository
public class PriceSheetRepository extends BaseRepository<PriceSheetModel> {
    private static final String GET_ALL_VALID_PRICE_SHEETS = "getAllValidPriceSheets";

    public List<PriceSheetModel> getAllValidPriceSheets() {
        return queryList(GET_ALL_VALID_PRICE_SHEETS);
    }
}

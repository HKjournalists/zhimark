package com.spring.mvc.repository;

import com.spring.mvc.model.TaxCodeModel;
import org.springframework.stereotype.Repository;

/**
 * Created by liluoqi on 15/5/9.
 */
@Repository
public class TaxCodeRepository extends BaseRepository<TaxCodeModel> {
    private static final String GET_TAX_CODE_BY_ID = "getTaxCodeById";

    public TaxCodeModel getTaxCodeById(int id) {
        return querySingleById(GET_TAX_CODE_BY_ID, id);
    }
}

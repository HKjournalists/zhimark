package com.spring.mvc.repository;

import com.spring.mvc.model.CurrencyModel;
import com.spring.mvc.utils.MapUtils;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by liluoqi on 15/5/7.
 */
@Repository
public class CurrencyRepository extends BaseRepository<CurrencyModel> {
    private static final String GET_BY_ID = "getCurrencyById";
    private static final String GET_BY_CODE = "getCurrencyByCode";
    private static final String GET_BY_NAME = "getCurrencyByName";

    public CurrencyModel getCurrencyById(int id) {
        return querySingleById(GET_BY_ID, id);
    }

    public CurrencyModel getCurrencyByCode(String code) {
        return querySingle(GET_BY_CODE, MapUtils.convertToMap("code", code));
    }

    public CurrencyModel getCurrencyByName(String name) {
        return querySingle(GET_BY_NAME, MapUtils.convertToMap("name", name));
    }
}

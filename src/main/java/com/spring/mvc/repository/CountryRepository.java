package com.spring.mvc.repository;

import com.spring.mvc.model.CountryModel;
import com.spring.mvc.utils.MapUtils;
import org.springframework.stereotype.Repository;

/**
 * Created by liluoqi on 15/5/12.
 */
@Repository
public class CountryRepository extends BaseRepository<CountryModel> {
    private static final String GET_COUNTRY_BY_NAME = "getCountryByName";
    private static final String GET_COUNTRY_BY_ID = "getCountryById";

    public CountryModel getCountryByName(String name) {
        return querySingle(GET_COUNTRY_BY_NAME, MapUtils.convertToMap("name", name));
    }

    public CountryModel getCountryById(int id) {
        return querySingleById(GET_COUNTRY_BY_ID, id);
    }
}

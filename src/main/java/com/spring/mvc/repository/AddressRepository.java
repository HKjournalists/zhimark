package com.spring.mvc.repository;

import com.spring.mvc.model.AddressModel;
import org.springframework.stereotype.Repository;

/**
 * Created by liluoqi on 15/5/7.
 */
@Repository
public class AddressRepository extends BaseRepository<AddressModel> {
    private static final String GET_ADDRESS_BY_ID = "getAddressById";

    public AddressModel getAddressById(int id) {
        return querySingleById(GET_ADDRESS_BY_ID, id);
    }
}

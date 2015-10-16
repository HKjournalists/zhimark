package com.spring.mvc.repository;

import com.spring.mvc.model.GoodModel;
import com.spring.mvc.utils.MapUtils;
import org.springframework.stereotype.Repository;

/**
 * Created by liluoqi on 15/5/7.
 */
@Repository
public class GoodRepository extends BaseRepository<GoodModel> {
    private static final String GET_GOOD_BY_ID = "getGoodByGoodId";

    public GoodModel getGoodByGoodId(int id) {
        return querySingleById(GET_GOOD_BY_ID, id);
    }
}

package com.spring.mvc.repository;

import com.spring.mvc.model.GoodUnitModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 获取数据库中商品单位字典表中的数据
 * Created by liluoqi on 15/5/3.
 */
@Repository
public class GoodUnitRepository extends BaseRepository<GoodUnitModel> {

    private static final String GET_GOOD_UNIT_BY_ID="getGoodUnitById";

    public GoodUnitModel getGoodUnitByUnitId(int id){
        return querySingleById(GET_GOOD_UNIT_BY_ID, id);
    }
}

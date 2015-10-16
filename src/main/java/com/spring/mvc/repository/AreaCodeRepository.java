package com.spring.mvc.repository;

import com.spring.mvc.model.AreaCodeModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liluoqi on 15/8/1.
 */
@Repository
public class AreaCodeRepository extends BaseRepository<AreaCodeModel> {
    private static final String GET_ALL_AREA_CODE = "getAllAreaCode";

    public List<AreaCodeModel> getAllAreaCode() {
        return queryList(GET_ALL_AREA_CODE);
    }
}

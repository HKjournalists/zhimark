package com.spring.mvc.repository;

import com.spring.mvc.model.LogisModel;
import org.springframework.stereotype.Repository;

/**
 * Created by liluoqi on 15/5/8.
 */
@Repository
public class LogisRepository extends BaseRepository<LogisModel> {
    private static final String GET_LOGIS_BY_ID = "getLogisById";

    public LogisModel getLogisById(int id) {
        return querySingleById(GET_LOGIS_BY_ID, id);
    }
}

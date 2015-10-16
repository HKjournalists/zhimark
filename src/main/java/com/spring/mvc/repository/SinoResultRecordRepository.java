package com.spring.mvc.repository;

import com.spring.mvc.model.SinoResultRecordModel;
import org.springframework.stereotype.Repository;

/**
 * Created by liluoqi on 15/5/21.
 */
@Repository
public class SinoResultRecordRepository extends BaseRepository<SinoResultRecordModel>{
    private static final String INSERT_NEW_RECORD="insertNewRecord";

    public int insertSinoResultRecord(SinoResultRecordModel sinoResultRecordModel){
        return super.insert(INSERT_NEW_RECORD,sinoResultRecordModel);
    }
}

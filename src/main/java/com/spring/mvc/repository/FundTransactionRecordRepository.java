package com.spring.mvc.repository;

import com.spring.mvc.model.FundTransactionRecordModel;
import org.springframework.stereotype.Repository;

/**
 * Created by liluoqi on 15/8/11.
 */
@Repository
public class FundTransactionRecordRepository extends BaseRepository<FundTransactionRecordModel> {

    private static final String INSERT_FUND_TRANSACTION_RECORD = "insertFundTransactionRecord";

    public int insertFundTransactionRecord(FundTransactionRecordModel fundTransactionRecord) {
        return insert(INSERT_FUND_TRANSACTION_RECORD, fundTransactionRecord);
    }
}

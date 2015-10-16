package com.spring.mvc.repository;

import com.spring.mvc.model.CompanyModel;
import org.springframework.stereotype.Repository;

/**
 * Created by liluoqi on 15/5/7.
 */
@Repository
public class CompanyRepository extends BaseRepository<CompanyModel>{

    private static final String GET_COMPANY_INFO="getCompanyInfo";

    public CompanyModel getCompany(){
        return querySingle(GET_COMPANY_INFO);
    }
}

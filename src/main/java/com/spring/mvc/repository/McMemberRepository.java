package com.spring.mvc.repository;

import com.spring.mvc.model.McMemberModel;
import com.spring.mvc.model.Model;
import com.spring.mvc.utils.MapUtils;
import org.springframework.stereotype.Repository;

/**
 * Created by liluoqi on 15/5/7.
 */
@Repository
public class McMemberRepository extends BaseRepository<McMemberModel> {
    private static final String GET_MC_MEMBER_BY_UID = "getMcMemberByUid";

    public McMemberModel getMcMemberByUid(int uid) {
       return querySingle(GET_MC_MEMBER_BY_UID, MapUtils.convertToMap("uid", uid));
    }
}

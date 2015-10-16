package com.spring.mvc.repository;

import com.spring.mvc.model.GroupGoodModel;
import org.springframework.stereotype.Repository;

/**
 * Created by liluoqi on 15/6/16.
 */
@Repository
public class GroupGoodRepository extends BaseRepository<GroupGoodModel> {
    private static final String GET_GROUP_GOOD_BY_ID = "getGroupGoodById";

    public GroupGoodModel getGroupGoodById(int id) {
        return querySingleById(GET_GROUP_GOOD_BY_ID, id);
    }
}

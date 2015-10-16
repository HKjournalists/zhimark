package com.spring.mvc.repository;

import com.spring.mvc.model.ZipModel;
import com.spring.mvc.utils.MapUtils;
import com.spring.mvc.utils.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liluoqi on 15/5/30.
 */
@Repository
public class ZipRepository extends BaseRepository<ZipModel> {
    private static final String GET_MOST_LIKELY_ZIP_BY_CITY = "getMostLikelyZipByCity";
    private static final String GET_MOST_LIKELY_ZIP_BY_PROVINCE = "getMostLikelyZipByProvince";

    public ZipModel getMostLikelyZipByCity(String city, String province) {
        List<String> directCities = new ArrayList<String>();
        directCities.add("上海市");
        directCities.add("北京市");
        directCities.add("天津市");
        directCities.add("重庆市");
        if (directCities.contains(province)) {
            return querySingle(GET_MOST_LIKELY_ZIP_BY_PROVINCE,MapUtils.convertToMap("city",province));
        }
        List<ZipModel> zipModelList = queryList(GET_MOST_LIKELY_ZIP_BY_CITY,
                MapUtils.convertToMap("city", city));
        return zipModelList != null && zipModelList.size() > 0 ? zipModelList.get(0) : null;
    }
}

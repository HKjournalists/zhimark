package com.spring.mvc.cache;

import com.spring.mvc.utils.DateUtils;
import org.apache.commons.collections.map.LRUMap;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * Created by liluoqi on 15/9/11.
 */
public class FisherAccessTokenCache {
    //expireDate是时间戳
    private static LinkedHashMap<String, String> eBaoAccessTokenMap = new LinkedHashMap<String, String>(1);

    public static String getValidAccessToken() {
        if(eBaoAccessTokenMap.size()!=0){
            for (String key : eBaoAccessTokenMap.keySet()) {
                if (!isExpired(eBaoAccessTokenMap.get(key))) {
                    return key;
                }
            }
        }
        return null;
    }

    public static void updateAccessToken(String accessToken, String expireDuration) {
        if (eBaoAccessTokenMap.size() != 0) {
            eBaoAccessTokenMap.clear();
        }
        eBaoAccessTokenMap.put(accessToken, expireDuration);
    }

    private static boolean isExpired(String expiredTimeUnixStamp) {
        return new Date().after(DateUtils.formatUnixTimestampToDate(expiredTimeUnixStamp));
    }
}

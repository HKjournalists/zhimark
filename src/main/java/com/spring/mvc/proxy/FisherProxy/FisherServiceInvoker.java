package com.spring.mvc.proxy.FisherProxy;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spring.mvc.model.gson.fisher.FisherAccessTokenGson;
import com.spring.mvc.model.gson.fisher.FisherProductInfosGson;
import com.spring.mvc.model.gson.fisher.base.FisherResultGson;
import com.spring.mvc.utils.*;

/**
 * Created by liluoqi on 15/9/10.
 */
public class FisherServiceInvoker {

    private static Logger logger = Logger.getLogger(FisherServiceInvoker.class);
    private static final String SUCCESS_CODE = "00";

    /**
     * 利用登录接口获取token
     *
     * @param url 易宝的接口地址
     * @return token
     */
    public static FisherAccessTokenGson getAccessTokenUsingLoginInterface(String appKey, String appSecret, String username, String password, String url) {
        try {
            logger.info("获取易宝的访问access_token信息");
            String data = new Gson().toJson(MapUtils.convertStringToLinkedMap("member_code", username, "password", password));
            FisherResultGson<FisherAccessTokenGson> fisherResult = HttpClientUtils.postForm(url, MapUtils.convertStringToLinkedMap("appkey", appKey, "code", "Member.Account.Login",
                    "data", data, "sign", MD5Utils.string2MD5(String.format("%s%s", data, appSecret))), new TypeToken<FisherResultGson<FisherAccessTokenGson>>() {
            });
            if (fisherResult != null) {
                logger.info(String.format("调用易宝接口返回的结果是:%s", new Gson().toJson(fisherResult)));
                if (SUCCESS_CODE.equals(fisherResult.getResultCode())) {
                    return fisherResult.getResultData();
                } else {
                    logger.warn(String.format("请求获取token失败编码:%s,失败原因:%s", fisherResult.getResultCode(), fisherResult.getResultMsg()));
                }
            }
        } catch (Exception e) {
            logger.error(String.format("获取易宝的access_token异常:%s", e.getMessage()));
        }
        return null;
    }

    /**
     * 获取易宝可圈商品信息,需要需要授权的access_token
     *
     * @param requestPage 请求的页
     * @return 返回的是
     */
    public static FisherProductInfosGson getProductInfosWithPagination(String appKey, String appSecret, String url, String accessToken, int requestPage) {
        try {
            logger.info(String.format("查询费舍尔第:%s页的可圈商品数量", requestPage));
            String data = new Gson().toJson(MapUtils.convertStringToLinkedMap("page_index", String.valueOf(requestPage)));
            FisherResultGson<FisherProductInfosGson> fisherResultGson = HttpClientUtils.postForm(url, MapUtils.convertStringToLinkedMap("access_token", accessToken, "appkey", appKey, "code", "Product.Item.GetEnablePurchase",
                    "data", data, "sign", MD5Utils.string2MD5(String.format("%s%s", data, appSecret))), new TypeToken<FisherResultGson<FisherProductInfosGson>>(){});
            if(fisherResultGson!=null){
                return fisherResultGson.getResultData();
            }
        } catch (Exception e) {
            logger.error(String.format("查询费舍尔第:%s页的可圈商品数量出错:%s", requestPage, e.getMessage()), e);
        }
        return null;
    }

    public static void main(String[] args) {
        String appKey = "openclient";
        String appSecret = "e5b6hi88";
//        String appKey="eb56shop";
//        String appSecret="1q2wazsx231dfsaa";
        FisherAccessTokenGson accessTokenUsingLoginInterface = getAccessTokenUsingLoginInterface(appKey, appSecret, "api@eb56.com", "123456", "http://api.appoo.cn/CoreServlet/Handle.aspx");
        if (accessTokenUsingLoginInterface != null) {
            System.out.println(accessTokenUsingLoginInterface.getToken());
            System.out.println(accessTokenUsingLoginInterface.getExpire());
            System.out.println(DateUtils.formatUnixTimestampToDate("1473831607"));
        } else {
            System.out.printf("token获取失败");
        }
    }
}

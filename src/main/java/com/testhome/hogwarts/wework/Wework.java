package com.testhome.hogwarts.wework;

import io.restassured.RestAssured;

/**
 * 基础类
 *
 * @author ywsmart
 * @date 2019-08-12
 */
public class Wework {
    private static String token;

    /**
     * 获取access_token，请求接口获得token
     */
    public static String getWeworkToken(String secret) {
        return RestAssured.given().log().all()
                .queryParam("corpid", WeworkConfig.getInstance().corpid)
                .queryParam("corpsecret", secret)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then().log().all().statusCode(200)
                .extract().path("access_token");
    }

    public static String getWeworkTokenForContact() {
        //todo:
        return null;
    }

    /**
     * 获取access_token，没有token再请求
     * 也可以增加整个模块的父类，来初始化此模块的引擎
     *
     * @return token
     */
    public static String getToken() {
        // todo: 支持两种类型的token
        if (token == null) {
            token = getWeworkToken(WeworkConfig.getInstance().contactSecret);
        }
        return token;
    }
}

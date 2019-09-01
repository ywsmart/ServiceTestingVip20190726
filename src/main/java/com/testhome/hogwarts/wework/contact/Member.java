package com.testhome.hogwarts.wework.contact;

import io.restassured.response.Response;

import java.util.HashMap;

/**
 * 通讯录-成员管理类
 *
 * @author ywsmart
 * @date 2019-08-14
 */
public class Member extends Contact {
    /**
     * 创建成员
     *
     * @param map
     * @return
     */
    public Response creat(HashMap<String, Object> map) {
        String body = template("/data/member.json", map);
        reset();
        return requestSpecification.body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/user/create")
                .then().log().all().extract().response();
    }
}

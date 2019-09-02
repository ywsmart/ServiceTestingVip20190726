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
        String body = template("/com/testhome/hogwarts/wework/contact/member/member.json", map);
        reset();
        return requestSpecification.body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/user/create")
                .then().log().all().extract().response();
    }

    /**
     * 读取成员
     *
     * @param userid
     * @return
     */
    public Response get(String userid) {
        reset();
        return requestSpecification.queryParam("userid", userid)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/user/get")
                .then().log().all().extract().response();
    }

    /**
     * 更新成员
     *
     * @param map
     * @return
     */
    public Response update(HashMap<String, Object> map) {
        String body = template("/com/testhome/hogwarts/wework/contact/member/updateMember.json", map);
        reset();
        return requestSpecification.body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/user/update")
                .then().log().all().extract().response();
    }

    /**
     * 删除成员
     *
     * @param userid
     * @return
     */
    public Response delete(String userid) {
        reset();
        return requestSpecification.queryParam("userid", userid)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/user/delete")
                .then().log().all().extract().response();
    }

    /**
     * 批量删除成员
     *
     * @param map 修改模板的键值对
     * @return
     */
    public Response batchdelete(HashMap<String, Object> map) {
        String body = template("/com/testhome/hogwarts/wework/contact/member/batchdelete.json", map);
        reset();
        return requestSpecification.body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/user/batchdelete")
                .then().log().all().extract().response();
    }

    /**
     * 获取部门成员
     *
     * @param department_id 获取部门的id
     * @return
     */
    public Response simplelist(String department_id) {
        reset();
        return requestSpecification.queryParam("department_id", department_id)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/user/simplelist")
                .then().log().all().extract().response();
    }

    /**
     * 获取部门成员详情
     *
     * @param department_id 获取的部门id
     * @return
     */
    public Response list(String department_id) {
        reset();
        return requestSpecification.queryParam("department_id", department_id)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/user/list")
                .then().log().all().extract().response();
    }

    /**
     * userid转openid
     *
     * @param map
     * @return
     */
    public Response convert_to_openid(HashMap<String, Object> map) {
        String body = template("/com/testhome/hogwarts/wework/contact/member/convert_to_openid.json", map);
        reset();
        return requestSpecification.body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/user/convert_to_openid")
                .then().log().all().extract().response();
    }

}

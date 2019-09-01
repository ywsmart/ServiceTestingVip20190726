package com.testhome.hogwarts.wework.contact;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;

/**
 * 通讯录-部门管理类
 *
 * @author ywsmart
 * @date 2019-08-14
 */
public class Department extends Contact {
    /**
     * 获取部门列表，以后需要把Response先保存到当前对象，再掉方法来读，要不然每个请求list都要导入Response的包
     */
    public Response list(String id) {
        reset();
        Response response = requestSpecification
                .param("id", id)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then().log().all().extract().response();
        return response;
    }

    /**
     * 创建部门，利用数据模板和第三方json库而不是用hashmap
     * 测试用例最好模型化（对象）或者模板化
     *
     * @return
     */
    public Response creat(String name, String parentid) {
        reset();
        String body = JsonPath.parse(this.getClass()
                .getResourceAsStream("/Data/creat.json"))
                .set("$.name", name)
                .set("$.parentid", parentid).jsonString();
        return requestSpecification
                .body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().log().all().extract().response();
    }

    /**
     * 复杂json用map来换参数
     *
     * @param map
     * @return
     */
    public Response creat(HashMap<String, Object> map) {
        reset();
        DocumentContext documentContext = JsonPath.parse(this.getClass()
                .getResourceAsStream("/Data/creat.json"));
        map.entrySet().forEach(entry -> {
            documentContext.set(entry.getKey(), entry.getValue());
        });
        return requestSpecification
                .body(documentContext.jsonString())
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().log().all().extract().response();
    }

    public Response delete(String id) {
        reset();
        return requestSpecification
                .queryParam("id", id)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then().log().all().extract().response();
    }

    public Response update(String id, String name) {
        reset();
        String body = JsonPath.parse(this.getClass()
                .getResourceAsStream("/Data/creat.json"))
                .set("$.name", name)
                .set("$.id", id).jsonString();
        return requestSpecification
                .body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/update")
                .then().log().all().extract().response();
    }

    /**
     * 读取har文件，未调试
     *
     * @param map
     * @return
     */
    public Response update(HashMap<String, Object> map) {
        return templateFromHar(
                "demo.har.json",
                "https://work.weixin.qq.com/wework_admin/party?action=addparty",
                map
        );
    }

    /**
     * 清数据方法，还需要考虑部门里有人时，先删人再删部门
     *
     * @return
     */
    public void deleteAll() {
        reset();
        List<Integer> idList = list("").then().extract().path("department.id");
        System.out.println(idList);
        idList.forEach(id -> delete(id.toString()));
    }

    /**
     * 伪代码，动态调用相关代码
     * @param map
     * @return
     */
    public  Response updateAll(HashMap<String, Object> map){
        // Todo:
        return api("api.json",map);
    }
}

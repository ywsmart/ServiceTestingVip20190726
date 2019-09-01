package com.testhome.hogwarts.wework;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;

/**
 * 接口发送引擎
 *
 * @author ywsmart
 * @date 2019-08-20
 */
public class Restful {
    HashMap<String, Object> query = new HashMap<String, Object>();
    public RequestSpecification requestSpecification = given();

    /**
     * 发送引擎
     *
     * @return
     */
    public Response send() {
        requestSpecification = given().log().all();
        query.entrySet().forEach(entry -> {
            requestSpecification.queryParam(entry.getKey(), entry.getValue());
        });
        return requestSpecification.when().request("get", "baidu.com");
    }

    /**
     * 模板引擎
     *
     * @param path
     * @param map
     * @return
     */
    public static String template(String path, HashMap<String, Object> map) {
        DocumentContext documentContext = JsonPath.parse(Restful.class
                .getResourceAsStream(path));
        map.entrySet().forEach(entry -> {
            documentContext.set(entry.getKey(), entry.getValue());
        });
        return documentContext.jsonString();
    }


    /**
     * 从har文件读取必要信息
     *
     * @param path
     * @param pattern
     * @param map
     * @return
     */
    public static Response templateFromHar(String path, String pattern, HashMap<String, Object> map) {
        // todo：支持从har自动生成接口定义并发送
        // 从har中读取请求，进行更新
        DocumentContext documentContext = JsonPath.parse(Restful.class
                .getResourceAsStream(path));
        map.entrySet().forEach(entry -> {
            documentContext.set(entry.getKey(), entry.getValue());
        });

        String method = documentContext.read("method");
        String url = documentContext.read("url");

        return responseSpecification.when().request(method, url);
    }

    public static Response templateFromSwagger(String path, String pattern, HashMap<String, Object> map) {
        // todo：支持从swagger自动生成接口定义并发送
        // 从swagger中读取请求，进行更新
        DocumentContext documentContext = JsonPath.parse(Restful.class
                .getResourceAsStream(path));
        map.entrySet().forEach(entry -> {
            documentContext.set(entry.getKey(), entry.getValue());
        });

        String method = documentContext.read("method");
        String url = documentContext.read("url");

        return responseSpecification.when().request(method, url);
    }

    public Response templateFromYaml(String path, HashMap<String, Object> map) {
        // todo: 根据yaml生成接口定义并发送
        return null;
    }
    // todo：支持wsdl soap

    /**
     * 动态调用不建议，这样每一个调用方法都一样，不利于区分维护
     *
     * @return
     * @param path
     * @param map
     */
    public Response api(String path, HashMap<String, Object> map) {
        // TODO 动态调用，新增接口可以通过新的json文件来调用
        return null;
    }
}

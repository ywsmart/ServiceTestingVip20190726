package com.testhome.hogwarts.wework.contact;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;

/**
 * @author ywsmart
 * @date 2019-08-15
 */
class DepartmentTest {

    Department department;
    // 用时间戳注意脏数据
    String random = String.valueOf(System.currentTimeMillis());

    @BeforeEach
    void setUp() {
        if (department == null) {
            department = new Department();
            // 清部门数据
            department.deleteAll();
        }
    }

    @Test
    void list() {
        department.list("").then().statusCode(200).body("department.name[0]", equalTo("Yvan"));
        department.list("2").then().statusCode(200).body("department.name[0]", equalTo("demo3"));
    }

    @Test
    void creat() {
        department.creat("20190820department", "1").then().body("errcode", equalTo(0));
        department.creat("20190820department", "1").then().body("errcode", equalTo(60008));
    }

    /**
     * 传参数包含中文，报60009，系传传中文时，请求默认charset=ISO-8859-1，需要主动更改编码
     */
    @Test
    void creatWithChinese() {
        department.creat("测试", "1").then().body("errcode", equalTo(0));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/Data/createWithDop.csv")
    void creatWithDup(String name, Integer expectCode){
        department.creat(name+random,"1").then().body("errcode",equalTo(0));
        department.creat(name+random,"1").then().body("errcode",equalTo(expectCode));
    }

    @Test
    void delete() {
        String nameOld = "cesi";
        department.creat(nameOld, "1");
        Integer idInt = department.list("").path("department.find{it.name=='" + nameOld + "'}.id");
        // 获取新建部门ID
        String id = String.valueOf(idInt);
        department.delete(id).then().body("errcode",equalTo(0));
    }

    @Test
    void update() {
        String nameOld = "cesi"+random;
        department.creat(nameOld, "1");
        Integer idInt = department.list("").path("department.find{it.name=='" + nameOld + "'}.id");
        // 获取新建部门ID
        String id = String.valueOf(idInt);
        department.update(id, "cesi100"+random).then().body("errcode",equalTo(0));
    }

    @Test
    void creatByMap() {
        HashMap<String,Object> map= new HashMap<String, Object>(){
            {
                put("name","测试"+random);
                put("parentid","1");
            }
        };
        department.creat(map).then().log().all().body("errcode",equalTo(0));
    }

    /**
     * 简单测试，实际放在初始化里
     */
    @Test
    void deleteAll() {
        department.deleteAll();
    }
    /**
     * 临时代码,不建议
     */
    @Test
    void updateAll(){
        HashMap<String, Object> map = new HashMap<>();
        department.api("api.json",map).then().statusCode(200);
    }
}
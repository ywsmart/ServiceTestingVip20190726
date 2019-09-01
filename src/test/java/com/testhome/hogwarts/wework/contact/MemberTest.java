package com.testhome.hogwarts.wework.contact;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;

/**
 * @author ywsmart
 * @date 2019-08-20
 */
class MemberTest {
    static Member member;
    String random = String.valueOf(System.currentTimeMillis());

    @BeforeAll
    static void setUp() {
        member = new Member();
    }

    /**
     * 可以数据驱动更多测试
     *
     * @param name
     */
    @ParameterizedTest
    @ValueSource(strings = {"racecar", "radar", "able"})
    void creat(String name) {
        String nameNew = name +random;
        String random =String.valueOf(System.currentTimeMillis()).substring(5, 13);
        HashMap<String, Object> map = new HashMap<>();
        map.put("userid", nameNew);
        map.put("name", nameNew);
        map.put("department", Arrays.asList(1, 2));
        map.put("mobile", "151" + random);
        map.put("email", random + "@qq.com");
        member.creat(map).then().statusCode(200).body("errcode", equalTo(0));
    }

    /**
     * csv数据驱动
     * Todo:
     *
     * @param name
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/com/testhome/hogwarts/wework/contact/member/members.csv")
    void creat1(String name,String alias) {
        String nameNew = name +random;
        String random =String.valueOf(System.currentTimeMillis()).substring(5, 13);
        HashMap<String, Object> map = new HashMap<>();
        map.put("userid", nameNew);
        map.put("name", nameNew);
        map.put("alias", alias);
        map.put("department", Arrays.asList(1, 2));
        map.put("mobile", "151" + random);
        map.put("email", random + "@qq.com");
        member.creat(map).then().statusCode(200).body("errcode", equalTo(0));
    }

    @Test
    void get() {
        String name = "TestName" +random;
        String random =String.valueOf(System.currentTimeMillis()).substring(5, 13);
        HashMap<String, Object> map = new HashMap<>();
        map.put("userid", name);
        map.put("name", name);
        map.put("department", Arrays.asList(1, 2));
        map.put("mobile", "151" + random);
        map.put("email", random + "@qq.com");
        member.creat(map);
        member.get(name).then().statusCode(200).body("userid", equalTo(name));
    }

    @Test
    void update() {
        String name = "TestName" +random;
        String random =String.valueOf(System.currentTimeMillis()).substring(5, 13);
        HashMap<String, Object> map = new HashMap<>();
        map.put("userid", name);
        map.put("name", name);
        map.put("mobile", "151" + random);
        map.put("email", random + "@qq.com");
        member.creat(map);
        String nameNew = "TestName" +random;
        String randomNew =String.valueOf(System.currentTimeMillis()).substring(5, 13);
        HashMap<String, Object> mapNew = new HashMap<>();
        mapNew.put("userid", name);
        mapNew.put("name", nameNew);
        mapNew.put("mobile", "151" + randomNew);
        mapNew.put("email", randomNew + "@qq.com");
        member.update(mapNew).then().statusCode(200).body("errcode",equalTo(0));
    }

    @Test
    void delete() {
        String name = "TestName" +random;
        String random =String.valueOf(System.currentTimeMillis()).substring(5, 13);
        HashMap<String, Object> map = new HashMap<>();
        map.put("userid", name);
        map.put("name", name);
        map.put("department", Arrays.asList(1, 2));
        map.put("mobile", "151" + random);
        map.put("email", random + "@qq.com");
        member.creat(map);
        member.delete(name).then().statusCode(200).body("errcode",equalTo(0));
    }

    @Test
    void batchdelete() {
        String name = "TestName" +random;
        String random =String.valueOf(System.currentTimeMillis()).substring(5, 13);
        HashMap<String, Object> map = new HashMap<>();
        map.put("userid", name);
        map.put("name", name);
        map.put("department", Arrays.asList(1, 2));
        map.put("mobile", "151" + random);
        map.put("email", random + "@qq.com");
        member.creat(map);
        String random2 =String.valueOf(System.currentTimeMillis()).substring(5, 13);
        String name2 = "TestName" +random;
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("userid", name2);
        map2.put("name", name2);
        map2.put("department", Arrays.asList(1, 2));
        map2.put("mobile", "151" + random2);
        map2.put("email", random2 + "@qq.com");
        member.creat(map2);
        HashMap<String, Object> mapNew = new HashMap<>();
        mapNew.put("useridlist",Arrays.asList(name, name2));
        member.batchdelete(mapNew).then().statusCode(200).body("errcode",equalTo(0));
    }
}
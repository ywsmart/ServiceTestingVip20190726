package com.testhome.hogwarts.wework.contact;

import org.junit.jupiter.api.BeforeAll;
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
    @CsvFileSource(resources = "/Data/members.csv")
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
}
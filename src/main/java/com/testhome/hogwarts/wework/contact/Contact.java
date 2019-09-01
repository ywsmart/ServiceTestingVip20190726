package com.testhome.hogwarts.wework.contact;

import com.testhome.hogwarts.wework.Restful;
import com.testhome.hogwarts.wework.Wework;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

/**
 * 通讯录基类，初始化发送引擎
 *
 * @author ywsmart
 * @date 2019-08-20
 */
public class Contact extends Restful {

    public Contact() {
        reset();
    }

    /**
     * 重置requestSpecification
     */
    public void reset() {
        requestSpecification = given().log().all()
                .contentType(ContentType.JSON)
                .queryParam("access_token", Wework.getToken())
        ;

        requestSpecification.filter((req,res,ctx)->{
            // todo:对请、求响应做封装
            return ctx.next(req,res);
        });
    }
}

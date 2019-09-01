import com.testhome.hogwarts.wework.Wework;
import com.testhome.hogwarts.wework.WeworkConfig;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

/**
 * 测试类
 *
 * @author ywsmart
 * @date 2019-07-26
 */
public class TestGetToken {
    @Test
    void testToken() {
        Wework wework = new Wework();
        String token = wework.getWeworkToken(WeworkConfig.getInstance().secret);
        System.out.println(token);
        assertThat(token, not(equalTo(null)));
    }

    @Test
    void creatDepartment(){
        given().log().all().queryParam("access_token", Wework.getToken())
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "   \"name\": \"广州研发中心\",\n" +
                        "   \"parentid\": 1,\n" +
                        "   \"order\": 1,\n" +
                        "}")
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().log().all().statusCode(200).extract().response();
    }
}
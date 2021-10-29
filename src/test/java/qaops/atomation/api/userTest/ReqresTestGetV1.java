package qaops.atomation.api.userTest;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import qaops.atomation.api.BaseTest;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.core.Is.is;


public class ReqresTestGetV1 extends BaseTest {

    private static final String USERS_LIST_ENDPOINT = "/users";
    private static final String USER_2_ENDPOINT = "/users/2";
    private static final String USER_NOT_FOUND_ENDPOINT = "/users/23";

    @Test public void
    getListUsersSuccess() {
        given().
                params("page", "2").
        when().
                get(USERS_LIST_ENDPOINT).
        then().
                statusCode(HttpStatus.SC_OK).
                body("page", is(2)).
                body("data[0]", hasKey("email"));
    }

    @Test public void
    getSpecificUserSuccess(){
        when().
                get(USER_2_ENDPOINT).
        then().
                statusCode(HttpStatus.SC_OK).
                body("data.email", is("janet.weaver@reqres.in")).
                body("data.first_name", is("Janet"));
    }

    @Test public void
    getSpecificUserNotFound(){
        when().
                get(USER_NOT_FOUND_ENDPOINT).
        then().
                statusCode(HttpStatus.SC_NOT_FOUND);
    }

}

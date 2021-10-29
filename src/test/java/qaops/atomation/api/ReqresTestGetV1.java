package qaops.atomation.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.core.Is.is;


public class ReqresTestGetV1 {

    @BeforeAll
    public static void setup(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        baseURI = "https://reqres.in";
        basePath = "/api";
    }

    @Test public void
    getListUsersSuccess() {
        given().
                params("page", "2").
        when().
                get("/users").
        then().
                contentType(ContentType.JSON).
                statusCode(HttpStatus.SC_OK).
                body("page", is(2)).
                body("data[0]", hasKey("email"));
    }

    @Test public void
    getSpecificUserSuccess(){
        when().
                get("/users/2").
        then().
                contentType(ContentType.JSON).
                statusCode(HttpStatus.SC_OK).
                body("data.email", is("janet.weaver@reqres.in")).
                body("data.first_name", is("Janet"));
    }

    @Test public void
    getSpecificUserNotFound(){
        when().
                get("/users/23").
        then().
                statusCode(HttpStatus.SC_NOT_FOUND);
    }

}

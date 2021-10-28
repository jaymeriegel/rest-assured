package qaops.atomation.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

public class AppTest1Basic {

    @BeforeClass
    public static void setup(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void testGetUsersStatic() {
        when().
                get("https://reqres.in/api/users?page=2").
        then().
                statusCode(200)
                .body("page", is(2))
                .body("data[0].email", is("michael.lawson@reqres.in"));
    }

    @Test
    public void testGetUsersCorrect(){
        when().
                get("https://reqres.in/api/users?page=2").
        then().
                statusCode(HttpStatus.SC_OK).
                body("page", is(2));
    }

    @Test
    public void testPostCreateUser(){
        given().
                contentType(ContentType.JSON).
                body("{\n" +
                        "    \"name\": \"jayme\",\n" +
                        "    \"job\": \"trainee\"\n" +
                        "}").
                when().
                    post("https://reqres.in/api/users").
                then().
                    statusCode(HttpStatus.SC_CREATED).
                    body("name", is("jayme")).
                    body("job", is("trainee"));
    }

}

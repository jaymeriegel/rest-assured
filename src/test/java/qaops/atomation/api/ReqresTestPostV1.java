package qaops.atomation.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import qaops.atomation.api.domain.User;

import static io.restassured.RestAssured.*;
import static org.hamcrest.core.Is.is;

public class ReqresTestPostV1 {


    @BeforeAll
    public static void setup(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        baseURI = "https://reqres.in";
        basePath = "/api";
    }

    @Test
    public void postUserSuccess(){

        User user = new User("Jayme", "Trainee");

        given().
                contentType(ContentType.JSON).
                body(user).
        when().
                post("/users").
        then().
                statusCode(HttpStatus.SC_CREATED).
                body("name", is("Jayme")).
                body("job", is("Trainee"));
    }
    
}

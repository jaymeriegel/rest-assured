package qaops.atomation.api;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

public class AppTest {

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


}

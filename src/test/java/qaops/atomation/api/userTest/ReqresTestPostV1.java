package qaops.atomation.api.userTest;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import qaops.atomation.api.BaseTest;
import qaops.atomation.api.domain.User;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

public class ReqresTestPostV1 extends BaseTest {

    private static final String CREATE_USER_ENDPOINT = "/users";

    @Test
    public void postUserSuccess(){

        User user = new User("Jayme", "Trainee");

        given().
                body(user).
        when().
                post(CREATE_USER_ENDPOINT).
        then().
                statusCode(HttpStatus.SC_CREATED).
                body("name", is("Jayme")).
                body("job", is("Trainee"));
    }
    
}

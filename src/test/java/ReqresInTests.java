import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ReqresInTests {

    Faker faker = new Faker(new Locale("ru"));

    String name = faker.name().fullName();
    String job = faker.job().title();
    String email = faker.internet().emailAddress();

    @Test
    void createUser() {
        String user = "{\"name\": \"" + name + "\",\"job\": \"" + job + "\"}";

        given()
                .log().uri()
                .contentType(JSON)
                .body(user)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is(name))
                .body("job", is(job));
    }

    @Test
    void loginUnsuccessful() {
        String data = "{\"email\": \"" + email + "\"}";

        given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void registerUnsuccessful() {
        String data = "{\"email\": \"" + email + "\"}";

        given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void updateUserPutUnsuccessful() {
        String user = "{\"name\": \"" + name + "\",\"job\": \"" + job + "\"}";

        given()
                .log().uri()
                .contentType(JSON)
                .body(user)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is(name))
                .body("job", is(job));
    }

    @Test
    void updateUserPatchUnsuccessful() {
        String user = "{\"name\": \"" + name + "\",\"job\": \"" + job + "\"}";

        given()
                .log().uri()
                .contentType(JSON)
                .body(user)
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is(name))
                .body("job", is(job));
    }
}
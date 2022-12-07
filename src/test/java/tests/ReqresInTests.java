package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import com.github.javafaker.Faker;
import models.lombok.LoginBodyModel;
import models.lombok.LoginResponseModel;
import models.pojo.UserBodyModel;
import models.pojo.UserResponseModel;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;

public class ReqresInTests {

    Faker faker = new Faker(new Locale("ru"));

    String name = faker.name().fullName();
    String job = faker.job().title();
    String email = faker.internet().emailAddress();

    @Test
    void createUser() {
        UserBodyModel body = new UserBodyModel();
        body.setName(name);
        body.setJob(job);

        UserResponseModel responseModel = given()
                .filter(withCustomTemplates())
                .log().all()
                .contentType(JSON)
                .body(body)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .extract().as(UserResponseModel.class);

        assertThat(responseModel.getName()).isEqualTo(name);
        assertThat(responseModel.getJob()).isEqualTo(job);
    }

    @Test
    void loginUnsuccessful() {
        LoginBodyModel body = new LoginBodyModel();
        body.setEmail(email);

        LoginResponseModel responseModel = given()
                .filter(withCustomTemplates())
                .log().uri()
                .contentType(JSON)
                .body(body)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .extract().as(LoginResponseModel.class);

        assertThat(responseModel.getError()).isEqualTo("Missing password");
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
    void updateUserPutSuccessful() {
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
    void updateUserPatchSuccessful() {
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
package tests;

import com.github.javafaker.Faker;
import models.lombok.LoginBodyModel;
import models.lombok.LoginResponseModel;
import models.pojo.UserBodyModel;
import models.pojo.UserResponseModel;
import models.pojo.UserUpdateResponseModel;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static specs.EmailSpec.*;
import static specs.UserSpec.*;

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
                .spec(userRequestSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(userResponseSpec)
                .extract().as(UserResponseModel.class);

        assertThat(responseModel.getName()).isEqualTo(name);
        assertThat(responseModel.getJob()).isEqualTo(job);
    }

    @Test
    void loginUnsuccessful() {
        LoginBodyModel body = new LoginBodyModel();
        body.setEmail(email);

        LoginResponseModel responseModel = given()
                .spec(emailLoginRequestSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(emailResponseSpec)
                .extract().as(LoginResponseModel.class);

        assertThat(responseModel.getError()).isEqualTo("Missing password");
    }

    @Test
    void registerUnsuccessful() {
        LoginBodyModel body = new LoginBodyModel();
        body.setEmail(email);

        LoginResponseModel responseModel = given()
                .spec(emailRegisterRequestSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(emailResponseSpec)
                .extract().as(LoginResponseModel.class);

        assertThat(responseModel.getError()).isEqualTo("Missing password");
    }

    @Test
    void updateUserPutSuccessful() {
        UserBodyModel body = new UserBodyModel();
        body.setName(name);
        body.setJob(job);

        UserUpdateResponseModel responseModel = given()
                .spec(userUpdateRequestSpec)
                .body(body)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .spec(userUpdateResponseSpec)
                .extract().as(UserUpdateResponseModel.class);

        assertThat(responseModel.getName()).isEqualTo(name);
        assertThat(responseModel.getJob()).isEqualTo(job);
    }

    @Test
    void updateUserPatchSuccessful() {
        UserBodyModel body = new UserBodyModel();
        body.setName(name);
        body.setJob(job);

        UserUpdateResponseModel responseModel = given()
                .spec(userUpdateRequestSpec)
                .body(body)
                .when()
                .patch()
                .then()
                .spec(userUpdateResponseSpec)
                .extract().as(UserUpdateResponseModel.class);

        assertThat(responseModel.getName()).isEqualTo(name);
        assertThat(responseModel.getJob()).isEqualTo(job);;
    }
}
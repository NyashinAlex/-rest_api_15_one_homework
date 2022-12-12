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
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.EmailSpec.*;
import static specs.UserSpec.*;

public class ReqresInWithGroovyTests {

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

        assertEquals(name, responseModel.getName());
        assertEquals(job, responseModel.getJob());
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

//        assertThat(responseModel.getError()).isEqualTo("Missing password");

        assertEquals("Missing password", responseModel.getError());
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

//        assertThat(responseModel.getError()).isEqualTo("Missing password");

        assertEquals("Missing password", responseModel.getError());
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

//        assertThat(responseModel.getName()).isEqualTo(name);
//        assertThat(responseModel.getJob()).isEqualTo(job);

        assertEquals(name, responseModel.getName());
        assertEquals(job, responseModel.getJob());
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

//        assertThat(responseModel.getName()).isEqualTo(name);
//        assertThat(responseModel.getJob()).isEqualTo(job);

        assertEquals(name, responseModel.getName());
        assertEquals(job, responseModel.getJob());
    }
}
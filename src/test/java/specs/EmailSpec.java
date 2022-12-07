package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

public class EmailSpec {
    public static RequestSpecification emailLoginRequestSpec = with()
            .filter(withCustomTemplates())
            .baseUri("https://reqres.in")
            .basePath("/api/login")
            .log().all()
            .contentType(JSON);

    public static RequestSpecification emailRegisterRequestSpec = with()
            .filter(withCustomTemplates())
            .baseUri("https://reqres.in")
            .basePath("/api/register")
            .log().all()
            .contentType(JSON);

    public static ResponseSpecification emailResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(400)
            .build();
}

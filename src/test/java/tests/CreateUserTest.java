package tests;

import com.github.javafaker.Faker;
import dto.ValidUserRequest;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CreateUserTest extends BaseTest {
    String endpoint = "/users";

    @Test
    public void createUserSuccessful() {
        String emailAddress = faker.internet().emailAddress();
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email(emailAddress)
                .full_name(faker.internet().uuid())
                .password(faker.internet().password())
                .generate_magic_link(false)
                .build();

        //Response response = postRequest(endpoint, 201, requestBody);
        Response response = postRequest(endpoint, requestBody);
        //response.getStatusCode();
        assertEquals(201, response.getStatusCode());
//        response.then().assertThat().statusCode(201);
        response = deleteRequest(endpoint + "/" + emailAddress);
        assertEquals(200, response.getStatusCode());
}

    @Test
    public void createUserWithoutEmailDomain() {
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email("hkkds@")
                .full_name(faker.internet().uuid())
                .password(faker.internet().password())
                .generate_magic_link(false)
                .build();
        Response response = postRequest(endpoint, requestBody);
        assertEquals(201, response.getStatusCode());
        response = deleteRequest(endpoint + "/" + "hkkds@");
        assertEquals(200, response.getStatusCode());
    }
    @Test
    public void createUserWithWrongEmail() {
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email(".@gmail.com")
                .full_name(faker.internet().uuid())
                .password(faker.internet().password())
                .generate_magic_link(false)
                .build();

        Response response = postRequest(endpoint, requestBody);
        assertEquals(201, response.getStatusCode());
        response = deleteRequest(endpoint + "/" + ".@gmail.com");
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void createUserWithWrongEmailDomain() {
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email("hkkdsddffdsdasjh@gmil.com")
                .full_name(faker.internet().uuid())
                .password(faker.internet().password())
                .generate_magic_link(false)
                .build();

        Response response = postRequest(endpoint, requestBody);
        assertEquals(201, response.getStatusCode());
        response = deleteRequest(endpoint + "/" + "hkkdsddffdsdasjh@gmil.com");
        assertEquals(200, response.getStatusCode());

    }

    @Test
    public void createUserPasswordLessThanRequired() {
        String emailAddress = faker.internet().emailAddress();
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email(emailAddress)
                .full_name(faker.internet().uuid())
                .password("1")
                .generate_magic_link(false)
                .build();

        Response response = postRequest(endpoint, requestBody);
        assertEquals(201, response.getStatusCode());
        response = deleteRequest(endpoint + "/" + emailAddress);
        assertEquals(200, response.getStatusCode());


    }

    @Test
    public void createUserMagicLinkValueTrue() {
        String emailAddress = faker.internet().emailAddress();
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email(emailAddress)
                .full_name(faker.internet().uuid())
                .password(faker.internet().password())
                .generate_magic_link(true)
                .build();

        Response response = postRequest(endpoint, requestBody);
        assertEquals(201, response.getStatusCode());
        response = deleteRequest(endpoint + "/" + emailAddress);
        assertEquals(200, response.getStatusCode());

    }

    @Test
    public void createUserWithEmptyPassword() {
        String emailAddress = faker.internet().emailAddress();
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email(emailAddress)
                .full_name(faker.internet().uuid())
                .password("")
                .generate_magic_link(false)
                .build();

        Response response = postRequest(endpoint, requestBody);
        assertEquals(201, response.getStatusCode());
        response = deleteRequest(endpoint + "/" + emailAddress);
        assertEquals(200, response.getStatusCode());

    }

    @Test
    public void createUserWithEmptyFullName() {
        String emailAddress = faker.internet().emailAddress();
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email(emailAddress)
                .full_name("")
                .password(faker.internet().password())
                .generate_magic_link(false)
                .build();

        Response response = postRequest(endpoint, requestBody);
        assertEquals(201, response.getStatusCode());
        response = deleteRequest(endpoint + "/" + emailAddress);
        assertEquals(200, response.getStatusCode());

    }

    @Test
    public void createUserWithEmptyEmailField() {
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email("")
                .full_name(faker.internet().uuid())
                .password(faker.internet().password())
                .generate_magic_link(false)
                .build();

        Response response = postRequest(endpoint, requestBody);
        assertEquals(400, response.getStatusCode());
        //response = deleteRequest(endpoint + "/" + "");
        //assertEquals(400, response.getStatusCode());


    }

    @Test
    public void createUserWithMethodGet(){
        String emailAddress = faker.internet().emailAddress();
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email(emailAddress)
                .full_name(faker.internet().uuid())
                .password(faker.internet().password())
                .generate_magic_link(false)
                .build();

        Response response = postRequest(endpoint, requestBody);
        assertEquals(201, response.getStatusCode());
        response = deleteRequest(endpoint + "/" + emailAddress);
        assertEquals(200, response.getStatusCode());

    }}
    /*
    @AfterEach
    public void after(){

        Response response = deleteRequest(endpoint + "/" + emailAddress);
//        assertEquals(200, response.getStatusCode());

    }
}*/


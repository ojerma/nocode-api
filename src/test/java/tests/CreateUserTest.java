package tests;

import com.github.javafaker.Faker;
import dto.ValidUserRequest;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


public class CreateUserTest extends BaseTest {
    String endpoint = "/users";
    Faker faker = new Faker();


    @Test
    public void createUserSuccessful() {
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email(faker.internet().emailAddress())
                .full_name(faker.internet().uuid())
                .password(faker.internet().password())
                .generate_magic_link(false)
                .build();

        Response response = postRequest(endpoint, 201, requestBody);

}

    @Test
    public void createUserWithoutEmailDomain() {
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email("hkkdsddffdsdasjh@")
                .full_name(faker.internet().uuid())
                .password(faker.internet().password())
                .generate_magic_link(false)
                .build();

        Response response = postRequest(endpoint, 400, requestBody);
    }

    @Test
    public void createUserWithWrongEmail() {
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email(".@gmail.com")
                .full_name(faker.internet().uuid())
                .password(faker.internet().password())
                .generate_magic_link(false)
                .build();

        Response response = postRequest(endpoint, 400, requestBody);
    }

    @Test
    public void createUserWithWrongEmailDomain() {
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email("hkkdsddffdsdasjh@gmil.com")
                .full_name(faker.internet().uuid())
                .password(faker.internet().password())
                .generate_magic_link(false)
                .build();

        Response response = postRequest(endpoint, 400, requestBody);

    }

    @Test
    public void createUserPasswordLessThanRequired() {
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email(faker.internet().emailAddress())
                .full_name(faker.internet().uuid())
                .password("1")
                .generate_magic_link(false)
                .build();

        Response response = postRequest(endpoint, 400, requestBody);

    }

    @Test
    public void createUserMagicLinkValueTrue() {
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email(faker.internet().emailAddress())
                .full_name(faker.internet().uuid())
                .password(faker.internet().password())
                .generate_magic_link(true)
                .build();

        Response response = postRequest(endpoint, 400, requestBody);

    }

    @Test
    public void createUserWithEmptyPassword() {
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email(faker.internet().emailAddress())
                .full_name(faker.internet().uuid())
                .password("")
                .generate_magic_link(false)
                .build();

        Response response = postRequest(endpoint, 400, requestBody);

    }

    @Test
    public void createUserWithEmptyFullName() {
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email(faker.internet().emailAddress())
                .full_name("")
                .password(faker.internet().password())
                .generate_magic_link(false)
                .build();

        Response response = postRequest(endpoint, 400, requestBody);

    }

    @Test
    public void createUserWithEmptyEmailField() {

        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email("")
                .full_name(faker.internet().uuid())
                .password(faker.internet().password())
                .generate_magic_link(false)
                .build();

        Response response = postRequest(endpoint, 400, requestBody);

    }

    @Test
    public void createUserWithMethodGet(){
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email(faker.internet().emailAddress())
                .full_name(faker.internet().uuid())
                .password(faker.internet().password())
                .generate_magic_link(false)
                .build();

        Response response = postRequestWithMethodGet(endpoint, 400, requestBody);
    }

}
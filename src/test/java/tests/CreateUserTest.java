package tests;

import com.github.javafaker.Faker;
import dto.ValidUserRequest;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CreateUserTest extends BaseTest {
    String endpoint = "/users";
    private String userEmail;


    @Test
    public void createUserSuccessful() {
        String emailAddress = faker.internet().emailAddress();
        userEmail = emailAddress;
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email(emailAddress)
                .full_name(faker.internet().uuid())
                .password(faker.internet().password())
                .generate_magic_link(false)
                .build();
        postRequest(endpoint, 201, requestBody);
    }

    @Test
    public void createUserWithoutEmailDomain() {
        String emailAddress = "hkhgds@";
        userEmail = emailAddress;
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email(emailAddress)
                .full_name(faker.internet().uuid())
                .password(faker.internet().password())
                .generate_magic_link(false)
                .build();
        postRequest(endpoint, 404, requestBody);
    }
        @Test
        public void createUserWithWrongEmail () {
            String emailAddress = ".@gmail.com";
            userEmail = emailAddress;
            ValidUserRequest requestBody = ValidUserRequest.builder()
                    .email(emailAddress)
                    .full_name(faker.internet().uuid())
                    .password(faker.internet().password())
                    .generate_magic_link(false)
                    .build();
            postRequest(endpoint, 404, requestBody);

        }
        @Test
        public void createUserPasswordLessThanRequired () {
            String emailAddress = faker.internet().emailAddress();
            userEmail = emailAddress;
            ValidUserRequest requestBody = ValidUserRequest.builder()
                    .email(emailAddress)
                    .full_name(faker.internet().uuid())
                    .password("1")
                    .generate_magic_link(false)
                    .build();
            postRequest(endpoint, 404, requestBody);

        }

        @Test
        public void createUserMagicLinkValueTrue () {
            String emailAddress = faker.internet().emailAddress();
            userEmail = emailAddress;
            ValidUserRequest requestBody = ValidUserRequest.builder()
                    .email(emailAddress)
                    .full_name(faker.internet().uuid())
                    .password(faker.internet().password())
                    .generate_magic_link(true)
                    .build();
            postRequest(endpoint, 404, requestBody);
        }

        @Test
        public void createUserWithEmptyPassword () {
            String emailAddress = faker.internet().emailAddress();
            userEmail = emailAddress;
            ValidUserRequest requestBody = ValidUserRequest.builder()
                    .email(emailAddress)
                    .full_name(faker.internet().uuid())
                    .password("")
                    .generate_magic_link(false)
                    .build();
            postRequest(endpoint, 404, requestBody);
        }

        @Test
        public void createUserWithEmptyFullName () {
            String emailAddress = faker.internet().emailAddress();
            userEmail = emailAddress;
            ValidUserRequest requestBody = ValidUserRequest.builder()
                    .email(emailAddress)
                    .full_name("")
                    .password(faker.internet().password())
                    .generate_magic_link(false)
                    .build();
            postRequest(endpoint, 404, requestBody);
        }

        @Test
        public void createUserWithEmptyEmailField () {
            ValidUserRequest requestBody = ValidUserRequest.builder()
                    .email("")
                    .full_name(faker.internet().uuid())
                    .password(faker.internet().password())
                    .generate_magic_link(false)
                    .build();
            postRequest(endpoint, 404, requestBody);
        }

        @Test
        public void createUserWithMethodGet() {
            String emailAddress = faker.internet().emailAddress();
            userEmail = emailAddress;
            ValidUserRequest requestBody = ValidUserRequest.builder()
                    .email(emailAddress)
                    .full_name(faker.internet().uuid())
                    .password(faker.internet().password())
                    .generate_magic_link(false)
                    .build();
            postRequestWithMethodGet(endpoint, 404, requestBody);
        }


    @AfterEach
    public void deleteUserAfterEach() {
        if (userEmail != null) {
            deleteRequest(endpoint + "/" + userEmail, 200);
            userEmail = null;
        }
    }
}
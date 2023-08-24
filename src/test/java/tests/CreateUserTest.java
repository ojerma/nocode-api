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
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email(emailAddress)
                .full_name(faker.internet().uuid())
                .password(faker.internet().password())
                .generate_magic_link(false)
                .build();

        Response response = postRequest(endpoint, 201, requestBody);
        assertEquals(201, response.getStatusCode());
        userEmail = emailAddress;
    }

    @Test
    public void createUserWithoutEmailDomain() {
        String emailAddress = "hkhgds@";
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email(emailAddress)
                .full_name(faker.internet().uuid())
                .password(faker.internet().password())
                .generate_magic_link(false)
                .build();
        Response response = postRequest(endpoint, 400, requestBody);
        //assertEquals(400, response.getStatusCode());
        userEmail = emailAddress;
    }
        @Test
        public void createUserWithWrongEmail () {
            String emailAddress = ".@gmail.com";
            ValidUserRequest requestBody = ValidUserRequest.builder()
                    .email(emailAddress)
                    .full_name(faker.internet().uuid())
                    .password(faker.internet().password())
                    .generate_magic_link(false)
                    .build();

            Response response = postRequest(endpoint, 400, requestBody);
            assertEquals(400, response.getStatusCode());
            userEmail = emailAddress;
        }
        @Test
        public void createUserPasswordLessThanRequired () {
            String emailAddress = faker.internet().emailAddress();
            ValidUserRequest requestBody = ValidUserRequest.builder()
                    .email(emailAddress)
                    .full_name(faker.internet().uuid())
                    .password("1")
                    .generate_magic_link(false)
                    .build();

            Response response = postRequest(endpoint, 400, requestBody);
            assertEquals(400, response.getStatusCode());
            response = deleteRequest(endpoint + "/" + emailAddress);
            assertEquals(200, response.getStatusCode());

        }

        @Test
        public void createUserMagicLinkValueTrue () {
            String emailAddress = faker.internet().emailAddress();
            ValidUserRequest requestBody = ValidUserRequest.builder()
                    .email(emailAddress)
                    .full_name(faker.internet().uuid())
                    .password(faker.internet().password())
                    .generate_magic_link(true)
                    .build();

            Response response = postRequest(endpoint, 400, requestBody);
            assertEquals(400, response.getStatusCode());
            response = deleteRequest(endpoint + "/" + emailAddress);
            assertEquals(200, response.getStatusCode());
        }

        @Test
        public void createUserWithEmptyPassword () {
            String emailAddress = faker.internet().emailAddress();
            ValidUserRequest requestBody = ValidUserRequest.builder()
                    .email(emailAddress)
                    .full_name(faker.internet().uuid())
                    .password("")
                    .generate_magic_link(false)
                    .build();

            Response response = postRequest(endpoint, 400, requestBody);
            assertEquals(400, response.getStatusCode());
            response = deleteRequest(endpoint + "/" + emailAddress);
            assertEquals(200, response.getStatusCode());
        }

        @Test
        public void createUserWithEmptyFullName () {
            String emailAddress = faker.internet().emailAddress();
            ValidUserRequest requestBody = ValidUserRequest.builder()
                    .email(emailAddress)
                    .full_name("")
                    .password(faker.internet().password())
                    .generate_magic_link(false)
                    .build();

            Response response = postRequest(endpoint, 400, requestBody);
            assertEquals(400, response.getStatusCode());
            response = deleteRequest(endpoint + "/" + emailAddress);
            assertEquals(200, response.getStatusCode());

        }

        @Test
        public void createUserWithEmptyEmailField () {
            ValidUserRequest requestBody = ValidUserRequest.builder()
                    .email("")
                    .full_name(faker.internet().uuid())
                    .password(faker.internet().password())
                    .generate_magic_link(false)
                    .build();

            Response response = postRequest(endpoint, 400, requestBody);
            assertEquals(400, response.getStatusCode());
        }

        @Test
        public void createUserWithMethodGet() {
            String emailAddress = faker.internet().emailAddress();
            ValidUserRequest requestBody = ValidUserRequest.builder()
                    .email(emailAddress)
                    .full_name(faker.internet().uuid())
                    .password(faker.internet().password())
                    .generate_magic_link(false)
                    .build();

            Response response = postRequestWithMethodGet(endpoint, 404, requestBody);
            userEmail = emailAddress;
            //assertEquals(404, response.getStatusCode());
            //response = deleteRequest(endpoint + "/" + emailAddress);
            //assertEquals(200, response.getStatusCode());

        }
    @AfterEach
    public void deleteUserAfterEach() {
        if (userEmail != null) {
            Response deleteResponse = deleteRequest(endpoint + "/" + userEmail);
            assertEquals(200, deleteResponse.getStatusCode());
            userEmail = null;
        }

    }}
    /*

    @AfterEach
    public void deleteUserAfterEach() {
        if (userEmail != null) {
            Response deleteResponse = deleteRequest(endpoint + "/" + userEmail);
            int statusCode = deleteResponse.getStatusCode();
            if (statusCode == 201 || statusCode == 400) {
                assertEquals(statusCode, deleteResponse.getStatusCode());
                userEmail = null;
            }
        }
    }

*/


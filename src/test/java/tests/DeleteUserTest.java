package tests;

import dto.ValidUserRequest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeleteUserTest extends BaseTest{


    String endpoint = "/users/";


    @Test
    public void successDelete(){
        String email = faker.internet().emailAddress();
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email(email)
                .full_name(faker.internet().uuid())
                .password(faker.internet().password())
                .generate_magic_link(false)
                .build();

        postRequest(endpoint, 201,requestBody);
        deleteRequest(endpoint+email, 200);
    }

    @Test
    public void deleteNotExistedUser(){
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email("i@gmail.com")
                .full_name(faker.internet().uuid())
                .password(faker.internet().password())
                .generate_magic_link(false)
                .build();

        Response response = deleteRequest(endpoint+"i@gmail.com", 404);
        assertEquals(404, response.getStatusCode());
    }
}

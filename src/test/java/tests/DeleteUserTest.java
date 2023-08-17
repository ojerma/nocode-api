package tests;

import dto.ValidUserRequest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeleteUserTest extends BaseTest{


    String endpoint = "/users/";
    //String emailAddress = faker.internet().emailAddress();
    /*@Test
    public void successDelete(){
        Response response = deleteRequest(endpoint+email ,200);
    }*/

    @Test
    public void successDelete(){
        String email = faker.internet().emailAddress();
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email(email)
                .full_name(faker.internet().uuid())
                .password(faker.internet().password())
                .generate_magic_link(false)
                .build();

        Response response = postRequest(endpoint, requestBody);
        assertEquals(201, response.getStatusCode());
        response = deleteRequest(endpoint + email);
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void deleteNotExistedUser(){
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email("b@gmail.com")
                .full_name(faker.internet().uuid())
                .password(faker.internet().password())
                .generate_magic_link(false)
                .build();

        Response response = deleteRequest(endpoint + "b@gmail.com");
        assertEquals(404, response.getStatusCode());
    }
    @Test
    public void deleteUserWithWrongEmailDomain(){
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email("betty@gail.com")
                .full_name(faker.internet().uuid())
                .password(faker.internet().password())
                .generate_magic_link(false)
                .build();

        Response response = postRequest(endpoint, requestBody);
        assertEquals(201, response.getStatusCode());
        response = deleteRequest(endpoint + "betty@gail.c");
        assertEquals(404, response.getStatusCode());
    }

    @Test
    public void deleteUserWithMethodGet(){
        String email = faker.internet().emailAddress();;
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email(email)
                .full_name(faker.internet().uuid())
                .password(faker.internet().password())
                .generate_magic_link(false)
                .build();

        Response response = getRequest(endpoint, requestBody);
        assertEquals(200, response.getStatusCode());
        response = deleteRequest(endpoint + email);
        assertEquals(404, response.getStatusCode());
    }
}

package tests;

import com.github.javafaker.Faker;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteUserTest extends BaseTest{
    Faker faker = new Faker();

    String endpoint = "/users/";
    String email = "myrtle.schmeler@yahoo.com";
    @Test
    public void successDelete(){
        Response response = deleteRequest(endpoint+email ,200);
    }
    @Test
    public void deleteNotExistedUser(){
        Response response = deleteRequest(endpoint+email ,404);
    }
    @Test
    public void deleteUserWithWrongEmailDomain(){
        Response response = deleteRequest(endpoint+email ,404);
    }
    @Test
    public void deleteCreatedUserWithMethodGet(){
        Response response = deleteRequest(endpoint+email ,200);
    }



}
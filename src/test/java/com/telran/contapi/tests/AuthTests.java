package com.telran.contapi.tests;

import com.jayway.restassured.RestAssured;
import com.telran.contapi.dto.AuthRequestDto;
import com.telran.contapi.dto.AuthResponseDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class AuthTests {

    @BeforeMethod
    public void ensurePreconditions() {
        RestAssured.baseURI = "https://contacts-telran.herokuapp.com";
        RestAssured.basePath = "api";

    }

    @Test
    public void loginRestAssuredTest() {
        // создаем экземпляр запроса на авторизацию, инициализируя поля с помощью builder()
        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email("mmm@mail.ru")
                .password("Mm$123456")
                .build();
        // создаем экземпляр ответа --"--"--
        AuthResponseDto responseDto = given()
                .contentType("application/Json")
                .body(requestDto)
                .post("login")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(AuthResponseDto.class);
        System.out.println(responseDto.getToken());

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im1tbUBtYWlsLnJ1In0.QIqAfln3hQCb2whO1P-YJFyUaFr1vV_scidKYDTlGSo";
        AuthResponseDto responseTokenDto = given()
                .contentType("application/Json")
                .body(requestDto)
                .post("login")
                .then()
                .assertThat().statusCode(200)
                .body("token", equalTo(token))
                .body(containsString("token"))
//                .extract().path("token");
                .extract().response().as(AuthResponseDto.class);
        System.out.println(responseDto.getToken());

    }






}

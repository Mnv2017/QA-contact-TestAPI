package com.telran.contapi.tests;

import com.jayway.restassured.RestAssured;
import com.telran.contapi.dto.ContactDto;
import com.telran.contapi.dto.ContactRequestDto;
import com.telran.contapi.dto.GetAllContactDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class ContactTests {
static  String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im1tbUBtYWlsLnJ1In0.QIqAfln3hQCb2whO1P-YJFyUaFr1vV_scidKYDTlGSo";

    @BeforeMethod
    public void ensurePreconditions() {
        RestAssured.baseURI = "https://contacts-telran.herokuapp.com";
        RestAssured.basePath = "api";

    }
    @Test
    public void getAllContactDto() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im1tbUBtYWlsLnJ1In0.QIqAfln3hQCb2whO1P-YJFyUaFr1vV_scidKYDTlGSo";

        GetAllContactDto responseDto = given()
                .header("Authorization", token)
                .get("contact")
                .then()
                .assertThat().statusCode(200)
                .extract().body().as(GetAllContactDto.class);

        for (ContactDto contactDto : responseDto.getContactDtoList()
        ) {
            System.out.println(contactDto.getId() + ",  " + contactDto.getName());
            System.out.println("**************************");
        }
/*
        String responseDto = given()
                .header("Authorization", token)
                .get("contact")
                .then()
                .assertThat().statusCode(500)
                .extract().path("message");
        System.out.println(responseDto);
*/
    }
    @Test
    public void addContactPositiveTest(){
        ContactRequestDto contactRequestDto = ContactRequestDto.builder()
                .address("Dortmund")
                .description("description1")
                .email("email987@gmx.de")
                .id(0)
                .lastName("Detochkin")
                .name("Yurij")
                .phone("9911882277")
                .build();

    }

    @Test
    public void deleteContactTest() {
        String status = given()
                .header("Authorization", token)
                .delete("contact/18194")
                .then()
                .assertThat().statusCode(200)
                .extract().path("status");
        System.out.println(status);

    }

}

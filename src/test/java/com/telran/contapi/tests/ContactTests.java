package com.telran.contapi.tests;

import com.jayway.restassured.RestAssured;
import com.telran.contapi.dto.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class ContactTests {
    static String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im1tbUBtYWlsLnJ1In0.QIqAfln3hQCb2whO1P-YJFyUaFr1vV_scidKYDTlGSo";
    static int contactID;

    @BeforeMethod
    public void ensurePreconditions() {
        RestAssured.baseURI = "https://contacts-telran.herokuapp.com";
        RestAssured.basePath = "api";

    }

    @Test(enabled = true)
    public void getAllContactDto() {
//        String token1 = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Imtyb29zQGdtLmNvbSJ9.BbVSHfbAvgsFMmWD-6KDLIvdwxyw07MrYPMkdZW2Tmc";

        GetAllContactDto responseDto = given()
                .header("Authorization", token)
                .get("contact")
                .then()
                .assertThat().statusCode(200)
                .extract().response().body().as(GetAllContactDto.class);

        if (responseDto.getContacts() != null) {
            for (ContactDto contact : responseDto.getContacts()) {
                System.out.println(contact.getId() + ",  " + contact.getName());
                System.out.println("**************************");
            }
        } else if (responseDto.getContacts() == null)
            System.out.println("  Список контрактов = null! ");
        else if (responseDto.getContacts().size() == 0)
            System.out.println(" У данного user нет контактов!");
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

   /* @Test(enabled = false)
    public void getAllContactDto() {
//        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im1tbUBtYWlsLnJ1In0.QIqAfln3hQCb2whO1P-YJFyUaFr1vV_scidKYDTlGSo";

        GetAllContactDto responseDto = given()
                .header("Authorization", token)
                .get("contact")
                .then()
                .assertThat().statusCode(200)
                .extract().response().body().as(GetAllContactDto.class);
        if (responseDto.getContactDtoList() != null) {
            for (ContactResponseDto contactDto : responseDto.getContactDtoList()
            ) {
                System.out.println(contactDto.getId() + ",  " + contactDto.getName());
                System.out.println("**************************");
            }
        } else if (responseDto.getContactDtoList() == null)
            System.out.println("  Список контрактов = null! ");
        else if (responseDto.getContactDtoList().size() == 0)
            System.out.println(" У данного user нет контактов!");

*/
    @Test(priority = 1)
    public void addContactPositiveTest() {
        ContactRequestDto contactRequestDto = ContactRequestDto.builder()
                .address("Dortmund")
                .description("description1")
                .email("email987@gmx.de")
                .id(0)
                .lastName("Detochkin")
                .name("Yurij")
                .phone("9911882277")
                .build();

        ContactResponseIdDto contactResponseDto = given()
                .contentType("application/json")
                .header("Authorization", token)
                .body(contactRequestDto)
                .when()
                .post("contact")
                .then()
                .assertThat().statusCode(200)
                .extract().body().as(ContactResponseIdDto.class);
        contactID = contactResponseDto.getId();
//        System.out.println("----------- Ошибка! "+contactResponseDto.getMessage());
        System.out.println("+++++++ Контакт создан! ID = " + contactID);
    }

    @Test(priority = 2)
    public void editContactPositiveWithDto() {
        ContactRequestDto contactRequestDto = ContactRequestDto.builder()
                .address("Dortmund")
                .description("description1")
                .email("email900@gmx.de")
                .id(contactID)
                .lastName("Detochkin2")
                .name("Yurij2")
                .phone("9911880000")
                .build();
        ContactDto contactResponseDto = given()
                .contentType("application/json")
                .header("Authorization", token)
                .body(contactRequestDto)
                .when()
                .put("contact")
                .then()
                .assertThat().statusCode(200)
                .extract().body().as(ContactDto.class);
        System.out.println(" #########  Контакт изменен! "+ contactResponseDto.getName()+",  "+contactResponseDto.getLastName());
    }

    @Test(priority = 3)
    public void deleteContactTest() {
        String status = given()
                .header("Authorization", token)
                .delete("contact/" + contactID)
                .then()
                .assertThat().statusCode(200)
                .extract().path("status");
        System.out.println(status);

    }

}

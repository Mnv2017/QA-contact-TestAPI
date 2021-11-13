package com.telran.contapi.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ContactRequestDto {

    String address;
    String description;
    String email;
    int id = 0;
    String lastName;
    String name;
    String phone;

}

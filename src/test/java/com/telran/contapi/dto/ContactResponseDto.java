package com.telran.contapi.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ContactResponseDto {
    String address;
    String description;
    String email;
    int id = 0;
    String lastName;
    String name;
    String phone;
}

package com.telran.contapi.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class AuthRequestDto {
    String email;
    String password;

}

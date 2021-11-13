package com.telran.contapi.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ContactErrorDto {
    int code;
    String details;
    String message;
}

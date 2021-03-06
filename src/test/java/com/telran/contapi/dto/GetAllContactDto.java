package com.telran.contapi.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class GetAllContactDto {
    List<ContactDto> contacts;
}

package com.example.oca42.model;

import com.example.oca42.entity.Contact;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private Long id;
    private String username;
    private Integer age;
    private List<AddressResponseDto> addresses;
    private ContactResponseDto contact;
}

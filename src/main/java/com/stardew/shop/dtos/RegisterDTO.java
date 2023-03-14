package com.stardew.shop.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String country;
}

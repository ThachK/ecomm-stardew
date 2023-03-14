package com.stardew.shop.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    private String email;
    private String password;
}

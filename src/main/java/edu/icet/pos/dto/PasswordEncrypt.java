package edu.icet.pos.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class PasswordEncrypt {
    private String salt;
    private String encryptedPassword;
}

package edu.icet.pos.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String userName;
    private String userPassword;
    private String empID;
    private String role;
}

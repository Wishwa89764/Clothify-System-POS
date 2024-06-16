package edu.icet.pos.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long userID;
    private String userName;
    private String userPassword;
    private String empID;
    private String role;
}

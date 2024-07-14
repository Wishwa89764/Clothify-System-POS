package edu.icet.pos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="user")
public class UserEntity {
    @Id
    private String id;
    private String userName;
    private String salt;
    private String securePassword;
    private String empID;
    private String role;
}

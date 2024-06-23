package edu.icet.pos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="supplier")
public class SupplierEntity {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String sex;
    private String nicNO;
    private String mobileNo;
    private String address1;
    private String address2;
    private String address3;
    private String  companyName;
    private String regNo;
    private String telephoneNo;
    private String eMailAddress;
    private String companyAddress1;
    private String companyAddress2;
    private String companyAddress3;
    private Date recruitmentDate;
    private String bankHolder;
    private String bank;
    private String branch;
    private String accountNo;
    private String userID;
}

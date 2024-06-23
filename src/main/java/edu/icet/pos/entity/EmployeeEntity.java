package edu.icet.pos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "employee")
public class EmployeeEntity {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private Date dob;
    private String sex;
    private String nicNO;
    private String mobileNo;
    private String address1;
    private String address2;
    private String address3;
    private String educationLevel;
    private String  department;
    private String designation;
    private Date recruitmentDate;
    private double salary;
    private String emgContactName;
    private String relationShip;
    private String emgContactMobileNo;
    private String emgContactAddress1;
    private String emgContactAddress2;
    private String emgContactAddress3;
    private String imgUrl;
    private String adminId;

}

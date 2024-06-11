package edu.icet.pos.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {
    private String employeeId;
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

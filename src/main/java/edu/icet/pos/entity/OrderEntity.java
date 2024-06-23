package edu.icet.pos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="orders")
public class OrderEntity {
    @Id
    private String id;
    private String itemCode;
    private String itemName;
    private int qty;
    private double unitPrice;
    private double grossPrice;
    private double discount;
    private double netPrice;
    private String date;
    private String time;
    private String userID;
}

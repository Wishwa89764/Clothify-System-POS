package edu.icet.pos.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String orderID;
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

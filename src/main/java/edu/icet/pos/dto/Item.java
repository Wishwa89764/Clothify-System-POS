package edu.icet.pos.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private String id;
    private String itemCategory;
    private String itemName;
    private String description;
    private String supplierID;
    private double itemCost;
    private double sellingPrice;
    private String imgUrl;

}

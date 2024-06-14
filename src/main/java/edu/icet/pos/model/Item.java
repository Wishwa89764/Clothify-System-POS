package edu.icet.pos.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private String itemCode;
    private String itemCategory;
    private String itemName;
    private String description;
    private String imgUrl;

}

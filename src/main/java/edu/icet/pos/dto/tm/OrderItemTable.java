package edu.icet.pos.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderItemTable {
    private String itemCode;
    private String itemName;
    private String qty;
    private String unitPrice;
    private String grossPrice;
    private String discount;
    private String netPrice;
}

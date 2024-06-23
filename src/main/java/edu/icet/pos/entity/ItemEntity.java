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
@Entity(name = "item")
public class ItemEntity {
    @Id
    private String id;
    private String itemCategory;
    private String itemName;
    private String description;
    private String imgUrl;

}

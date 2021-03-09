package com.cosmos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "InventoryProducts003")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long inventoryId;
    private Long productId;
    private Long quantityOfProduct;
    private Long bestBeforeDays;
    private String comments;
    private LocalDate addedDate;
}

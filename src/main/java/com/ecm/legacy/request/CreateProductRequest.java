package com.ecm.legacy.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {

    private String title;
    private String description;
    private int mrpPrice;              // Maximum Retail Price
    private int sellingPrice;
    private String color;
    private List<String> images;       // Multiple product images
    private String category;
    private String category2;          // Sub-category
    private String category3;          // Sub-sub-category
    private String sizes;              // Size variants (comma-separated ya JSON format)
}

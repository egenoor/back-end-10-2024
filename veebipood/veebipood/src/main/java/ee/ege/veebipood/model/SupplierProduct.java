package ee.ege.veebipood.model;

import lombok.Data;

@Data
public class SupplierProduct {
    private double rate;
    private int count;
    private int id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
    private Rating rating;
}

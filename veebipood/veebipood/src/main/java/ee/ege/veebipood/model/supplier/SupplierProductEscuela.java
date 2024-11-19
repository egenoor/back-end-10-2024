package ee.ege.veebipood.model.supplier;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class SupplierProductEscuela {
    private int id;
    private String title;
    private int price;
    private String description;
    private ArrayList<String> images;
    private Date creationAt;
    private Date updatedAt;
    private EscuelaCategory category;
}

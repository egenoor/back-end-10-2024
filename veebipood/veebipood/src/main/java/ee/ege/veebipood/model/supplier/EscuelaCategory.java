package ee.ege.veebipood.model.supplier;

import lombok.Data;

import java.util.Date;

@Data
public class EscuelaCategory {
    private int id;
    private String name;
    private String image;
    private Date creationAt;
    private Date updatedAt;
}

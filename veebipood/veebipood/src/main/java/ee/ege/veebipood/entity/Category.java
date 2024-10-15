package ee.ege.veebipood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

//    @OneToMany
//    List<Product> products;
}

// strategy = GenerationType.IDENTITY 1,2,3,6,7,8,9
// strategy = GenerationType.UUID adsd1-313-21-dasd-31
// strategy = GenerationType.SEQUENCE saan alustada numbrist 10000 ja minna 10 võrra edasi
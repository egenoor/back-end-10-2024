package ee.ege.veebipood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private String image;
    private boolean active;
    private String description;

    @ManyToOne
// @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne
    private Nutrients nutrients;

    public Product(String name) {
        this.name = name;
    }
}

// @OneToOne on siis kui isik + tema kontaktandmed
//Piimatooted
// Piim > category piimatooted
// või > category piimatooted
// kui on @onetoone siis seda ei lubata
// @OneToOne > üksühele seos (kontaktandmed, toidu toiduained)
// @ManyToOne > üks, aga võib korrata
// @OneToMany > mitu (list) aga iga sealolev toode tohib olla ühe korra
// @ManyToMany mitu (list), aga iga sealolev toode võib olla mõnel teisel kategoorial
package ee.ege.veebipood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

// PostgreSQL ei saa kasutada tabelite nimetustena Order, User

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@SequenceGenerator(name = "seq", initialValue = 123000, allocationSize = 1)
@Table(name="orders") //vahetame tabeli nime
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;

    @ManyToMany
    //@Column(name="products")
    //@JoinColumn(name = "product_id")
    private List<Product> product;
}

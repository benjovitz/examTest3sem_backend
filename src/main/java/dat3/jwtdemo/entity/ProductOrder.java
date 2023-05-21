package dat3.jwtdemo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    int quantity;

    @ManyToOne
    Product product;
    @ManyToOne
    Delivery delivery;
}

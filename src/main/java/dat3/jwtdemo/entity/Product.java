package dat3.jwtdemo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @NonNull
    String name;
    @NonNull
    double price;
    @NonNull
    int weight;

    @OneToMany(mappedBy = "product")
    List<ProductOrder> productOrders;

    public void addProductOrder(ProductOrder p){
        if(productOrders ==null){
            productOrders = new ArrayList<>();
        }
        productOrders.add(p);
        p.setProduct(this);
    }
}

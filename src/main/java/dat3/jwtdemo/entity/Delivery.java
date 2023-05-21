package dat3.jwtdemo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate deliveryDate;
    String fromWarehouse;
    String destination;

    @OneToMany(mappedBy = "delivery")
    List<ProductOrder> productOrders;

    public void addProductOrder(ProductOrder p){
        if(productOrders ==null){
            productOrders = new ArrayList<>();
        }
        productOrders.add(p);
        p.setDelivery(this);
    }


}

package dat3.jwtdemo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Van {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String brand;
    String model;
    int capacity;

    @OneToMany(mappedBy = "van")
    List<Delivery> deliveryList;

    public void addDelivery(Delivery d){
        if(deliveryList ==null){
            deliveryList = new ArrayList<>();
        }
        deliveryList.add(d);
        d.setVan(this);
    }
}

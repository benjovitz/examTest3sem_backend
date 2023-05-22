package dat3.jwtdemo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.jwtdemo.entity.Delivery;
import dat3.jwtdemo.entity.Van;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VanRepsponse {

    Integer id;
    String brand;
    String model;
    int capacity;
    List<Integer> deliveryList;

    public VanRepsponse(Van v){
        this.id=v.getId();
        this.brand=v.getBrand();
        this.model=v.getModel();
        this.capacity=v.getCapacity();
        if(v.getDeliveryList()!=null){
            this.deliveryList=v.getDeliveryList().stream().map(d->d.getId()).toList();
        }
    }
}

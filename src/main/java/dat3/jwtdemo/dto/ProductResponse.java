package dat3.jwtdemo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.jwtdemo.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {

    Integer id;
    String name;
    double price;
    int weight;

    public ProductResponse(Product p){
        this.id=p.getId();
        this.name=p.getName();
        this.price=p.getPrice();
        this.weight=p.getWeight();
    }
}

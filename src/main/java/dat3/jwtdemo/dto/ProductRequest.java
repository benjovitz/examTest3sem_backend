package dat3.jwtdemo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.jwtdemo.entity.Product;
import dat3.jwtdemo.entity.ProductOrder;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductRequest {

    String name;
    double price;
    int weight;

    public static Product getProductEntity(ProductRequest pr){
        Product product = Product.builder().weight(pr.getWeight()).name(pr.getName()).price(pr.getPrice()).build();
        return product;
    }
}

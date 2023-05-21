package dat3.jwtdemo.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.jwtdemo.entity.Delivery;
import dat3.jwtdemo.entity.Product;
import dat3.jwtdemo.entity.ProductOrder;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ProductOrderRequest {

    int quantity;
    Product product;
    Delivery delivery;
    int productId;
    int deliveryId;

    public static ProductOrder getPOEntity(ProductOrderRequest POR){
       ProductOrder productOrder = ProductOrder.builder().product(POR.getProduct()).delivery(POR.getDelivery()).quantity(POR.getQuantity()).build();
       return productOrder;
    }
}

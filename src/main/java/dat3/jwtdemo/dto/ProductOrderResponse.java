package dat3.jwtdemo.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.jwtdemo.entity.Delivery;
import dat3.jwtdemo.entity.Product;
import dat3.jwtdemo.entity.ProductOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductOrderResponse {

    int quantity;
    ProductResponse product;
    int delivery;

    public ProductOrderResponse(ProductOrder productOrder,ProductResponse productResponse){
        this.quantity=productOrder.getQuantity();
        this.delivery=productOrder.getDelivery().getId();
        this.product=productResponse;
    }
}

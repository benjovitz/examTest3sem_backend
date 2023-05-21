package dat3.jwtdemo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.jwtdemo.entity.Delivery;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryResponse {


    Integer id;
    @JsonFormat(pattern = "MM-dd-yyyy")
    LocalDate deliveryDate;
    String fromWarehouse;
    String destination;
    List<Integer> productOrders;

    public DeliveryResponse(Delivery d){
        this.id=d.getId();
        this.deliveryDate=d.getDeliveryDate();
        this.fromWarehouse=d.getFromWarehouse();
        this.destination=d.getDestination();
        if (d.getProductOrders()!=null){
            this.productOrders=d.getProductOrders().stream().map(po-> po.getId()).toList();
        }

    }
}

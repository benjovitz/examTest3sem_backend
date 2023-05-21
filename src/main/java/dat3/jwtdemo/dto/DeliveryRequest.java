package dat3.jwtdemo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.jwtdemo.entity.Delivery;
import dat3.jwtdemo.entity.ProductOrder;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryRequest {

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate deliveryDate;
    String fromWarehouse;
    String destination;


    public static Delivery getDeliveryEntity(DeliveryRequest dr){
        return Delivery.builder().deliveryDate(dr.getDeliveryDate()).destination(dr.getDestination()).fromWarehouse(dr.getFromWarehouse()).build();
    }

}

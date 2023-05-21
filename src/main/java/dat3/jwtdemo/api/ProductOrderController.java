package dat3.jwtdemo.api;

import dat3.jwtdemo.dto.ProductOrderRequest;
import dat3.jwtdemo.dto.ProductOrderResponse;
import dat3.jwtdemo.dto.ProductResponse;
import dat3.jwtdemo.service.ProductOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/po/")
public class ProductOrderController {

    ProductOrderService productOrderService;

    public ProductOrderController(ProductOrderService productOrderService) {
        this.productOrderService = productOrderService;
    }

    @PostMapping
    void create(@RequestBody List<ProductOrderRequest> requests){
        productOrderService.create(requests);
    }
    @GetMapping("{deliveryID}")
    List<ProductOrderResponse> getByDelivery(@PathVariable Integer deliveryID){
        return productOrderService.findAllByDelivery(deliveryID);
    }
}

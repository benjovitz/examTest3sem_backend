package dat3.jwtdemo.api;

import dat3.jwtdemo.dto.DeliveryRequest;
import dat3.jwtdemo.dto.DeliveryResponse;
import dat3.jwtdemo.service.DeliveryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/delivery/")
public class DeliveryController {

    DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping
    List<DeliveryResponse> getAll(){
        return deliveryService.getAll();
    }
    @PostMapping
    DeliveryResponse create(@RequestBody DeliveryRequest request){
        return deliveryService.create(request);
    }
    @GetMapping("{id}")
    DeliveryResponse findById(@PathVariable Integer id){
        return deliveryService.findById(id);
    }
    @GetMapping("van/{vanId}")
    List<DeliveryResponse> findByVan(@PathVariable Integer vanId){
        return deliveryService.findByVan(vanId);
    }
}

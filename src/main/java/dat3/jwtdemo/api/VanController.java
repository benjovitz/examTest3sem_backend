package dat3.jwtdemo.api;

import dat3.jwtdemo.dto.VanRepsponse;
import dat3.jwtdemo.service.VanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/van/")
public class VanController {

    VanService vanService;

    public VanController(VanService vanService){
        this.vanService=vanService;
    }

    @GetMapping
    List<VanRepsponse> getAll(){
        return vanService.findAll();
    }
    @PutMapping("delivery/{vanId}/{deliveryId}")
    ResponseEntity<Boolean> addDelivery(@PathVariable Integer deliveryId,@PathVariable Integer vanId){
        return vanService.addDelivery(deliveryId,vanId);
    }
}

package dat3.jwtdemo.service;

import dat3.jwtdemo.dto.VanRepsponse;
import dat3.jwtdemo.entity.Delivery;
import dat3.jwtdemo.entity.ProductOrder;
import dat3.jwtdemo.entity.Van;
import dat3.jwtdemo.repository.DeliveryRepo;
import dat3.jwtdemo.repository.VanRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VanService {

    VanRepo vanRepo;
    DeliveryRepo deliveryRepo;

    public VanService(VanRepo vanRepo,DeliveryRepo deliveryRepo){
        this.vanRepo=vanRepo;
        this.deliveryRepo=deliveryRepo;
    }

    public List<VanRepsponse> findAll() {
        List<VanRepsponse> list = vanRepo.findAll().stream().map(van -> new VanRepsponse(van)).toList();
        return list;
    }

    public ResponseEntity<Boolean> addDelivery(Integer deliveryId, Integer vanId) {
        Delivery d = deliveryRepo.findById(deliveryId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Delivery with this id doesnt exist"));
        Van van = vanRepo.findById(vanId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Van with this id doesnt exist"));
        List<ProductOrder> productOrders = d.getProductOrders();
        int totalWeight=0;
        for (ProductOrder po:productOrders) {
            int weight = po.getProduct().getWeight();
            weight = weight*po.getQuantity();
            weight=weight/1000;
            totalWeight+=weight;
        }
        if(totalWeight>van.getCapacity()){
            van.addDelivery(d);
            vanRepo.save(van);
            deliveryRepo.save(d);
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);

    }

}

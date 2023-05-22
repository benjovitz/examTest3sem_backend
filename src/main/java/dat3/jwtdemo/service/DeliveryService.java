package dat3.jwtdemo.service;

import dat3.jwtdemo.dto.DeliveryRequest;
import dat3.jwtdemo.dto.DeliveryResponse;
import dat3.jwtdemo.entity.Delivery;
import dat3.jwtdemo.entity.Product;
import dat3.jwtdemo.entity.Van;
import dat3.jwtdemo.repository.DeliveryRepo;
import dat3.jwtdemo.repository.VanRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeliveryService {
    DeliveryRepo deliveryRepo;
    VanRepo vanRepo;

    public DeliveryService(DeliveryRepo deliveryRepo,VanRepo vanRepo){
        this.deliveryRepo=deliveryRepo;
        this.vanRepo=vanRepo;
    }

    public List<DeliveryResponse> getAll() {
        List<DeliveryResponse> list = deliveryRepo.findAll().stream().map(d->new DeliveryResponse(d)).toList();
        return list;
    }

    public DeliveryResponse create(DeliveryRequest request) {
        Delivery delivery = DeliveryRequest.getDeliveryEntity(request);
        if(request.getDeliveryDate().isBefore(LocalDate.now())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Cannot set date in past");
        }
        deliveryRepo.save(delivery);
        return new DeliveryResponse(delivery);
    }

    public DeliveryResponse findById(Integer id) {
        return new DeliveryResponse(deliveryRepo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Delivery with this id doesnt exist")));
    }

    public List<DeliveryResponse> findByVan(Integer vanId) {
        Van van = vanRepo.findById(vanId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Van with this id doesnt exist"));
        List<DeliveryResponse> list = deliveryRepo.findAllByVan(van).stream().map(d->new DeliveryResponse(d)).toList();
        return list;
    }
}

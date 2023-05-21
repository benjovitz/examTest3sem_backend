package dat3.jwtdemo.service;

import dat3.jwtdemo.dto.DeliveryRequest;
import dat3.jwtdemo.dto.DeliveryResponse;
import dat3.jwtdemo.entity.Delivery;
import dat3.jwtdemo.repository.DeliveryRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeliveryService {
    DeliveryRepo deliveryRepo;

    public DeliveryService(DeliveryRepo deliveryRepo){
        this.deliveryRepo=deliveryRepo;
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
}

package dat3.jwtdemo.service;

import dat3.jwtdemo.dto.ProductOrderRequest;
import dat3.jwtdemo.dto.ProductOrderResponse;
import dat3.jwtdemo.dto.ProductResponse;
import dat3.jwtdemo.entity.Delivery;
import dat3.jwtdemo.entity.ProductOrder;
import dat3.jwtdemo.repository.DeliveryRepo;
import dat3.jwtdemo.repository.ProductOrderRepo;
import dat3.jwtdemo.repository.ProductRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductOrderService {

    ProductOrderRepo productOrderRepo;

    DeliveryRepo deliveryRepo;

    ProductRepo productRepo;

    public ProductOrderService(ProductOrderRepo productOrderRepo, DeliveryRepo deliveryRepo, ProductRepo productRepo) {
        this.productOrderRepo = productOrderRepo;
        this.deliveryRepo = deliveryRepo;
        this.productRepo = productRepo;
    }

    public void create(List<ProductOrderRequest> requests) {
        /*
        requests.stream().map(r->{
            r.setProduct(productRepo.findById(r.getProductId()).orElseThrow());
            r.setDelivery(deliveryRepo.findById(r.getDeliveryId()).orElseThrow());
            ProductOrder po = ProductOrderRequest.getPOEntity(r);
            productOrderRepo.save(po);
            return r;
        }).map();
           */
        for (ProductOrderRequest r:requests) {
            r.setProduct(productRepo.findById(r.getProductId()).orElseThrow());
            r.setDelivery(deliveryRepo.findById(r.getDeliveryId()).orElseThrow());
            if(!productOrderRepo.existsByDeliveryAndProduct(r.getDelivery(),r.getProduct())){
                ProductOrder po = ProductOrderRequest.getPOEntity(r);
                r.getProduct().addProductOrder(po);
                productRepo.save(r.getProduct());
                r.getDelivery().addProductOrder(po);
                deliveryRepo.save(r.getDelivery());
                productOrderRepo.save(po);
            }
        }
    }

    public List<ProductOrderResponse> findAllByDelivery(Integer deliveryID) {
        Delivery d = deliveryRepo.findById(deliveryID).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Delivery with this id doesnt exist"));
        List<ProductOrderResponse> list = productOrderRepo.findAllByDelivery(d).stream().map(productOrder -> {
            ProductResponse pr = new ProductResponse(productOrder.getProduct());
            return new ProductOrderResponse(productOrder,pr);

        }).toList();
        return list;
    }
}

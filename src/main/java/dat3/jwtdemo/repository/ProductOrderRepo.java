package dat3.jwtdemo.repository;

import dat3.jwtdemo.entity.Delivery;
import dat3.jwtdemo.entity.Product;
import dat3.jwtdemo.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOrderRepo extends JpaRepository<ProductOrder, Integer> {

    boolean existsByDeliveryAndProduct( Delivery delivery,Product product);
    List<ProductOrder> findAllByDelivery(Delivery delivery);
}

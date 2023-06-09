package dat3.jwtdemo.repository;

import dat3.jwtdemo.entity.Delivery;
import dat3.jwtdemo.entity.Van;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepo extends JpaRepository<Delivery, Integer> {

    List<Delivery> findAllByVan(Van van);
}

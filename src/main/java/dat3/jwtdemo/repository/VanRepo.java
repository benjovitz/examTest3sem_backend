package dat3.jwtdemo.repository;

import dat3.jwtdemo.entity.Delivery;
import dat3.jwtdemo.entity.Van;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VanRepo extends JpaRepository<Van, Integer> {


}

package dat3.jwtdemo.repository;

import dat3.jwtdemo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    Product findByName(String name);
}

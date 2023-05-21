package dat3.jwtdemo.service;

import dat3.jwtdemo.dto.ProductRequest;
import dat3.jwtdemo.dto.ProductResponse;
import dat3.jwtdemo.entity.Product;
import dat3.jwtdemo.repository.ProductRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    ProductRepo repo;

    public ProductService(ProductRepo repo) {
        this.repo = repo;
    }

    public ProductResponse createProduct(ProductRequest request) {
        Product product = ProductRequest.getProductEntity(request);
        repo.save(product);
        return new ProductResponse(product);
    }

    public List<ProductResponse> getAll() {
        List<ProductResponse> list = repo.findAll().stream().map(product -> new ProductResponse(product)).toList();
        return list;
    }

    public ProductResponse findById(Integer id) {
        return new ProductResponse(repo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Product with this id doesnt exist")));
    }

    public ResponseEntity<Boolean> editProduct(ProductRequest request,Integer id) {
        Product product = repo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Product with this id doesnt exist"));
        Optional.ofNullable(request.getName()).ifPresent(product::setName);
        Optional.ofNullable(request.getPrice()).ifPresent(product::setPrice);
        Optional.ofNullable(request.getWeight()).ifPresent(product::setWeight);
        repo.save(product);
        return ResponseEntity.ok(true);
    }

    public ResponseEntity<Boolean> deleteProduct(Integer id) {
        Product product = repo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Product with this id doesnt exist"));
        repo.delete(product);
        return ResponseEntity.ok(true);
    }

    public ProductResponse findByName(String name) {
        return new ProductResponse(repo.findByName(name));
    }
}

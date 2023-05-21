package dat3.jwtdemo.api;

import dat3.jwtdemo.dto.ProductRequest;
import dat3.jwtdemo.dto.ProductResponse;
import dat3.jwtdemo.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/product/")
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @GetMapping
    List<ProductResponse> getAllProducts(){
        return productService.getAll();
    }

    @GetMapping("{id}")
    ProductResponse findById(@PathVariable Integer id){
        return productService.findById(id);
    }
    @GetMapping("/name/{name}")
    ProductResponse findByName(@PathVariable String name){
        return productService.findByName(name);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ProductResponse createProduct(@RequestBody ProductRequest request){
        return productService.createProduct(request);
    }
    @PutMapping("{id}")
    ResponseEntity<Boolean> editProduct(@RequestBody ProductRequest request,@PathVariable Integer id){
        return productService.editProduct(request,id);
    }
    @DeleteMapping("{id}")
    ResponseEntity<Boolean> deleteProduct(@PathVariable Integer id){
        return productService.deleteProduct(id);
    }
}

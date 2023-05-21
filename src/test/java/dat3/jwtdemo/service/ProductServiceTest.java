package dat3.jwtdemo.service;

import com.sun.tools.jconsole.JConsoleContext;
import dat3.jwtdemo.dto.ProductRequest;
import dat3.jwtdemo.entity.Product;
import dat3.jwtdemo.repository.ProductRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ProductServiceTest {

    @Autowired
    ProductRepo productRepo;

    ProductService productService;

    boolean dataIsReady;
    @BeforeEach
    void setUp() {
        if(!dataIsReady){
            Product product1 = Product.builder().id(1).price(1).weight(1).name("aa").build();
            Product product2 = Product.builder().id(2).price(1).weight(1).name("bb").build();
           productRepo.save(product1);
           productRepo.save(product2);
           this.productService = new ProductService(productRepo);
           dataIsReady=true;
        }
    }
    @Test
    void findById() {
        System.out.println(productRepo.findByName("aa").getId());
        assertEquals("aa",productService.findById(1).getName());
    }

    @Test
    void createProduct() {
        ProductRequest productRequest = new ProductRequest("cc",1,1);
        productService.createProduct(productRequest);
        System.out.println(productRepo.findByName("aa").getId());
        assertEquals(3,productRepo.findAll().size());
    }

    @Test
    void getAll() {
        System.out.println(productRepo.findByName("aa").getId());

        assertEquals(2,productService.getAll().size());
    }



    @Test
    void editProduct() {
        ProductRequest productRequest = new ProductRequest("cc",2,2);
        productService.editProduct(productRequest,1);
        assertEquals("cc",productRepo.findById(1).get().getName());
    }

    @Test
    void deleteProduct() {
        productService.deleteProduct(1);

        assertEquals(1,productRepo.findAll().size());
    }

    @Test
    void findByName() {
        System.out.println(productRepo.findByName("aa").getId());
        assertEquals("aa",productService.findByName("aa").getName());
    }
}
package dat3.jwtdemo.configuration;

import dat3.jwtdemo.entity.Delivery;
import dat3.jwtdemo.entity.Product;
import dat3.jwtdemo.entity.ProductOrder;
import dat3.jwtdemo.entity.Van;
import dat3.jwtdemo.repository.DeliveryRepo;
import dat3.jwtdemo.repository.ProductOrderRepo;
import dat3.jwtdemo.repository.ProductRepo;
import dat3.jwtdemo.repository.VanRepo;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;
import dat3.security.repository.UserWithRolesRepository;

import java.time.LocalDate;

@Controller
public class SetupDevUsers implements ApplicationRunner {

    UserWithRolesRepository userWithRolesRepository;
    String passwordUsedByAll;

    ProductRepo productRepo;
    ProductOrderRepo productOrderRepo;

    DeliveryRepo deliveryRepo;
    VanRepo vanRepo;

    public SetupDevUsers(UserWithRolesRepository userWithRolesRepository,ProductRepo productRepo,ProductOrderRepo productOrderRepo,DeliveryRepo deliveryRepo,VanRepo vanRepo) {
        this.userWithRolesRepository = userWithRolesRepository;
        passwordUsedByAll = "test12";
        this.productRepo = productRepo;
        this.productOrderRepo=productOrderRepo;
        this.deliveryRepo = deliveryRepo;
        this.vanRepo=vanRepo;
    }

    @Override
    public void run(ApplicationArguments args) {
        //setupUserWithRoleUsers();

        // Product 1
        Product product1 = Product.builder()
                .name("Bread")
                .price(2.5)
                .weight(500) // 500 grams
                .build();
        productRepo.save(product1);

// Product 2
        Product product2 = Product.builder()
                .name("Eggs")
                .price(3.0)
                .weight(300) // 300 grams
                .build();
        productRepo.save(product2);

// Product 3
        Product product3 = Product.builder()
                .name("Apples")
                .price(1.0)
                .weight(200) // 200 grams
                .build();
        productRepo.save(product3);

// Product 4
        Product product4 = Product.builder()
                .name("Cheese")
                .price(5.0)
                .weight(800) // 800 grams
                .build();
        productRepo.save(product4);

// Product 5
        Product product5 = Product.builder()
                .name("Chicken")
                .price(12.0)
                .weight(1500) // 1500 grams
                .build();
        productRepo.save(product5);

// Product 6
        Product product6 = Product.builder()
                .name("Rice")
                .price(3.5)
                .weight(900) // 900 grams
                .build();
        productRepo.save(product6);

// Product 7
        Product product7 = Product.builder()
                .name("Tomatoes")
                .price(2.0)
                .weight(400) // 400 grams
                .build();
        productRepo.save(product7);

// Product 8
        Product product8 = Product.builder()
                .name("Cereal")
                .price(4.5)
                .weight(600) // 600 grams
                .build();
        productRepo.save(product8);

// Product 9
        Product product9 = Product.builder()
                .name("Yogurt")
                .price(1.5)
                .weight(300) // 300 grams
                .build();
        productRepo.save(product9);

// Product 10
        Product product10 = Product.builder()
                .name("Coffee")
                .price(6.0)
                .weight(200) // 200 grams
                .build();
        productRepo.save(product10);

        Product product = Product.builder().name("MILK").price(10.0).weight(1000).build();
        productRepo.save(product);
        Delivery delivery = Delivery.builder().deliveryDate(LocalDate.now()).destination("København").fromWarehouse("Odense").build();
        deliveryRepo.save(delivery);

        Delivery delivery2 = Delivery.builder().deliveryDate(LocalDate.now()).destination("månen").fromWarehouse("Mars").build();
        deliveryRepo.save(delivery2);
        ProductOrder productOrder = ProductOrder.builder().product(product).delivery(delivery).quantity(5).build();
        productOrderRepo.save(productOrder);

        ProductOrder productOrder2 = ProductOrder.builder().product(product).delivery(delivery2).quantity(999999999).build();
        productOrderRepo.save(productOrder2);

        // Small Van
        Van smallVan = Van.builder()
                .model("Small Van")
                .capacity(500)
                .brand("Bob Vance")
                .build();
        smallVan.addDelivery(delivery);
        vanRepo.save(smallVan);
        deliveryRepo.save(delivery);


// Medium Van
        Van mediumVan = Van.builder()
                .model("Medium Van")
                .capacity(1000)
                .brand("Bob Vance")
                .build();
        vanRepo.save(mediumVan);

// Large Van
        Van largeVan = Van.builder()
                .model("Large Van")
                .capacity(1500)
                .brand("Bob Vance")
                .build();
        vanRepo.save(largeVan);

    }

    /*****************************************************************************************
     NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
     iT'S ONE OF THE TOP SECURITY FLAWS YOU CAN DO
     *****************************************************************************************/
    private void setupUserWithRoleUsers() {
        System.out.println("******************************************************************************");
        System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
        System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
        System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
        System.out.println("******************************************************************************");
        UserWithRoles user1 = new UserWithRoles("user1", passwordUsedByAll, "user1@a.dk");
        UserWithRoles user2 = new UserWithRoles("user2", passwordUsedByAll, "user2@a.dk");
        UserWithRoles user3 = new UserWithRoles("user3", passwordUsedByAll, "user3@a.dk");
        UserWithRoles user4 = new UserWithRoles("user4", passwordUsedByAll, "user4@a.dk");
        user1.addRole(Role.USER);
        user1.addRole(Role.ADMIN);
        user2.addRole(Role.USER);
        user3.addRole(Role.ADMIN);
        //No Role assigned to user4
        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
        userWithRolesRepository.save(user4);
    }
}

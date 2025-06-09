package cm.sji.goodies.Controller;

import cm.sji.goodies.Model.Entities.CartProduct;
import cm.sji.goodies.Model.Entities.Client;
import cm.sji.goodies.Model.Entities.Order;
import cm.sji.goodies.Model.Entities.Product;
import cm.sji.goodies.Model.ServiceImpl.CartProductService;
import cm.sji.goodies.Model.ServiceImpl.CategoryServiceImpl;
import cm.sji.goodies.Model.Services.CategoryService;
import cm.sji.goodies.Model.Services.OrderService;
import cm.sji.goodies.Model.Services.PersonService;
import cm.sji.goodies.Model.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    private final PersonService personService;
    private final CartProductService cartProductService;
    private final OrderService orderService;
    private CategoryService categoryService;
    private ProductService productService;


    @Autowired
    public ProductController(CategoryService categoryService, ProductService productService, PersonService personService, CartProductService cartProductService, OrderService orderService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.personService = personService;
        this.cartProductService = cartProductService;
        this.orderService = orderService;
    }

    @GetMapping("/products/{type}/{catId}")
    public String showProducts(
            @PathVariable Long catId,
            @PathVariable String type,
            Model model) {

        List<Product> products = productService.getProductsByTypeCategory(type, catId);
        model.addAttribute("products", products);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "user/category";
    }

    @PostMapping("/productSearch")
    public String search(Model model, @RequestParam("keyword") String keyword) {
        model.addAttribute("products", productService.searchProducts(keyword.toLowerCase()));
        model.addAttribute("categories", categoryService.getAllCategories());
        return "user/category";
    }

    @PostMapping("/product/price/range")
    public String productInRange(Model model, @RequestParam("minPrice") Double minPrice, @RequestParam("maxPrice") Double maxPrice) {
        model.addAttribute("products", productService.getProductsByPriceRange(minPrice, maxPrice));
        model.addAttribute("categories", categoryService.getAllCategories());
        return "user/category";
    }

    @GetMapping("/addtoCart")
    public String addToCart(Model model, @RequestParam("prodId") Long prodId, @RequestParam("clientId") Long clientId) {
        Order order = new Order();
        CartProduct cartProduct = new CartProduct();
//        var p = Long.parseLong(prodId);
        Product product = productService.findById(prodId);
        Client client = (Client) personService.getPersonById(clientId);
        cartProduct.setProduct(product);
        cartProduct.setSelectedQty(0);
        order.setStatus("DRAFT");
        order.setClient(client);
        cartProductService.saveCartProduct(cartProduct);
        orderService.saveOrder(order);
        return "redirect:/user/category";

    }
}

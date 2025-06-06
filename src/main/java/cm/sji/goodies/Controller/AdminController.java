package cm.sji.goodies.Controller;

import cm.sji.goodies.Model.DTO.ProductDTO;
import cm.sji.goodies.Model.Entities.Product;
import cm.sji.goodies.Model.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    private final ProductService productService;

    @Autowired
    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/home")
    public String home() {
        return "admin/index-admin";
    }

    @GetMapping("/products")
    public String products(Model model) {
        List<ProductDTO> products = productService.getAllProducts();
        return "admin/products";
    }

    @GetMapping("/productlist")
    public String productlistpage() {
        return "admin/productlist";
    }

    @GetMapping("/addproduct")
    public String addproduct() {
        return "admin/editproduct";
    }

    @GetMapping("/categorylist")
    public String categorylist() {
        return "admin/categorylist";
    }

    @GetMapping("/addcategory")
    public String addCategory() {
        return "admin/addcategory";
    }

    @GetMapping("/subcategory")
    public String subCategory() {
        return "admin/subcategorylist";
    }

    @GetMapping("/saleslist")
    public String saleslistpage() {
        return "admin/saleslist";
    }

    @GetMapping("/purchaselist")
    public String purchaselist() {
        return "admin/purchaselist";
    }

    @GetMapping("/addpurchase")
    public String addpurchase() {
        return "admin/addpurchase";
    }


}

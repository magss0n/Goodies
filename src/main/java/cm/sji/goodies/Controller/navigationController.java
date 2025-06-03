package cm.sji.goodies.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class navigationController {

    @GetMapping("/category")
    public String category() {
        return "category";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/user/home";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/cart")
    public String cartpage() {
        return "cart";
    }

    @GetMapping("/checkout")
    public String checkoutpage() {
        return "checkout";
    }

    @GetMapping("/product-details")
    public String productdetailsPage() {
        return "product-details";
    }

    @GetMapping("/contact")
    public String contactpage() {
        return "contact";
    }

    @GetMapping("/search-result")
    public String searchpage() {
        return "search-result";
    }

    @GetMapping("/shipping-info")
    public String shippingpage() {
        return "shipping-info";
    }

    @GetMapping("/about")
    public String aboutpage() {
        return "about";
    }

    @GetMapping("/account")
    public String accountpage() {
        return "account";
    }

    @GetMapping("/payment-methods")
    public String paymentMethodpage() {
        return "payment-methods";
    }

    @GetMapping("/order-confirmation")
    public String orderpage() {
        return "order-confirmation";
    }

    @GetMapping("/login-register")
    public String loginRegister() {
        return "login-register";
    }

    @GetMapping("/return-policy")
    public String returnpage() {
        return "return-policy";
    }


    @GetMapping("/index-admin")
    public String indexadminpage() {
        return "index-admin";
    }


    @GetMapping("/activities")
    public String activitiespage() {
        return "activities";
    }


    @GetMapping("/addpurchase")
    public String addpurchasepage() {
        return "addpurchase";
    }


    @GetMapping("/addsupplier")
    public String addsupplierpage() {
        return "addsupplier";
    }


    @GetMapping("/adduser")
    public String adduserpage() {
        return "adduser";
    }


    @GetMapping("/categorylist")
    public String categorylistpage() {
        return "categorylist";
    }


    @GetMapping("/customerlist")
    public String customerlistpage() {
        return "customerlist";
    }


    @GetMapping("/edit-sales")
    public String editsalespage() {
        return "editsales";
    }


    @GetMapping("/editcustomer")
    public String editcustomerpage() {
        return "editcustomer";
    }


    @GetMapping("/editproduct")
    public String editproductpage() {
        return "editproduct";
    }


    @GetMapping("/editpurchase")
    public String editpurchasepage() {
        return "editpurchase";
    }

    @GetMapping("/editsubcategory")
    public String editsubcategorypage() {
        return "editsubcategory";
    }

    @GetMapping("/editsupplier")
    public String editsupplierpage() {
        return "editsupplier";
    }

    @GetMapping("/edituser")
    public String edituserpage() {
        return "edituser";
    }

    @GetMapping("/purchaselist")
    public String purchaselistpage() {
        return "purchaselist";
    }

    @GetMapping("/productlist")
    public String productlistpage() {
        return "productlist";
    }

    @GetMapping("/saleslist")
    public String saleslistpage() {
        return "saleslist";
    }

    @GetMapping("/storelist")
    public String storelistpage() {
        return "storelist";
    }

    @GetMapping("/subcategorylist")
    public String subcategorylistpage() {
        return "subcategorylist";
    }

    @GetMapping("/supplierlist")
    public String supplierlistpage() {
        return "supplierlist";
    }

    @GetMapping("/userlist")
    public String userlistpage() {
        return "userlist";
    }

    @GetMapping("/profile")
    public String profilepage() {
        return "profile";
    }
}


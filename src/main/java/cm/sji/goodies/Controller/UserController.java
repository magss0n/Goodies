package cm.sji.goodies.Controller;

import cm.sji.goodies.Model.Entities.Client;
import cm.sji.goodies.Model.Entities.Product;
import cm.sji.goodies.Model.Services.CategoryService;
import cm.sji.goodies.Model.Services.PersonService;
import cm.sji.goodies.Model.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final CategoryService categoryService;
    private final ProductService productService;
    private PersonService personService;

    @Autowired
    public UserController(PersonService personService, CategoryService categoryService, ProductService productService) {
        this.personService = personService;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @PostMapping("/signup")
    public String login(@Validated @ModelAttribute Client client,
                        @RequestParam("confirmPass") String confirmPass,
                        Model model,
                        BindingResult result) {

        System.out.println(confirmPass);
        if (!confirmPass.equals(client.getPassword())) {
            result.rejectValue("password", "error.password.nomatch", "Passwords do not match");
            model.addAttribute("passNoMatch", "Passwords do not match");
        }


        if (personService.getClientByName(client.getName()) != null) {
            result.rejectValue("firstName", client.getName(), "First name already exists");
            model.addAttribute("nameExist", "An account with this name already exists");
        }

        if (personService.getClientByPhone(client.getPhone()) != null) {
            result.rejectValue("phone", client.getName(), "This phone number already exists");
            model.addAttribute("phoneExist", "An account with this phone number already exists");
        }
        if (personService.getClientByEmail(client.getEmail()) != null) {
            result.rejectValue("email", client.getEmail(), "Email already exists");
            model.addAttribute("emailExist", "An account with this email already exists");
        }
        if (result.hasErrors()){
            model.addAttribute("client", client);
            return "signup";
        }

        personService.addPerson(client);
        model.addAttribute("message", "Thank you for registering! Please log in to verify it's really you.");
        return "user/login";
    }

    @GetMapping("/home")
    public String home(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Client client = personService.getClientByEmail(userDetails.getUsername());
        model.addAttribute("client", client);

        var categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        List<Product> products = productService.getProductsByCategory(categories.getFirst().getName().toLowerCase());
        List<Product> products1 = productService.getProductsByCategory(categories.getLast().getName().toLowerCase());
        products.addAll(products1);
        model.addAttribute("products", products);

        return "user/index";
    }

    @GetMapping("/category")
    public String category(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Client client = personService.getClientByEmail(userDetails.getUsername());
        model.addAttribute("client", client);

        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("products", productService.getAllProducts());
        return "user/category";
    }


}

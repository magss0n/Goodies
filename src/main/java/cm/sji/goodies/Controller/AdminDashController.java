package cm.sji.goodies.Controller;

import cm.sji.goodies.Model.DTO.AdminDTO;
import cm.sji.goodies.Model.DTO.ProductDTO;
import cm.sji.goodies.Model.Entities.*;
import cm.sji.goodies.Model.Entities.Purchase;
import cm.sji.goodies.Model.Services.*;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping("admin")
public class AdminDashController {

    private final ProductService productService;
    private final PictureService pictureService;
    private final SupplierService supplierService;
    private final CategoryService categoryService;
    private final AdminService adminService;
    private final ClientService clientService;
    private final PurchaseService purchaseService;

    @Autowired
    public AdminDashController(ProductService productService, PictureService pictureService, SupplierService supplierService, CategoryService categoryService, AdminService adminService, ClientService clientService, PurchaseService purchaseService) {
        this.productService = productService;
        this.pictureService = pictureService;
        this.supplierService = supplierService;
        this.categoryService = categoryService;
        this.adminService = adminService;
        this.clientService = clientService;
        this.purchaseService = purchaseService;
    }

    @GetMapping("/home")
    public String home(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        AdminDTO admin = (AdminDTO) adminService.getAdminByEmail(userDetails.getUsername()).orElse(null);
        model.addAttribute("admin", admin);

        return "admin/index-admin";
    }

    @GetMapping("/products")
    public String products(Model model) {
        List<ProductDTO> products = productService.getAllProducts();
        return "admin/products";
    }

   @GetMapping("/productlist")
    public String productlistpage(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        AdminDTO admin = (AdminDTO) adminService.getAdminByEmail(userDetails.getUsername()).orElse(null);
        model.addAttribute("admin", admin);

        model.addAttribute("newProduct", new ProductDTO());
        List<ProductDTO> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("suppliers", supplierService.getSuppliers());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/productlist";
    }

    @PostMapping("/addproduct")
    public String addProduct(
            ProductDTO productDTO,
            @RequestParam("brand") String brand,
            @RequestParam("photo") MultipartFile photo,
            @RequestParam("size") String size,
            @RequestParam("category") Long categoryId,
            @Nullable @RequestParam("supplierId") Long supplierId,
            @Nullable @RequestParam("supplierName") String newSupplierName,
            @Nullable @RequestParam("supplierPhone") String newSupplierPhone
            ) {
        Category category = categoryService.getCategory(categoryId);

        try {
            if (productDTO.getSize() == null || productDTO.getSize().isEmpty()) {
                productDTO.setSize(size);
            }
            Supplier supplier;

            if (newSupplierName != null && !newSupplierName.isEmpty() && newSupplierPhone != null && !newSupplierPhone.isEmpty()) {
                supplier = new Supplier();
                supplier.setName(newSupplierName);
                supplier.setPhoneNumber(newSupplierPhone);
                supplierService.addSupplier(supplier);
            } else supplier = supplierService.getSupplier(supplierId);

            productDTO.setName(productDTO.getName().toUpperCase() + " " + brand.toUpperCase());
            productDTO.setCategory(category);

            String fileName = photo.getOriginalFilename();
            String extension = fileName.substring(photo.getOriginalFilename().lastIndexOf("."));
            Path uploadDir = Paths.get(".", "src","main","resources","static","uploads");

            if (fileName.isEmpty()) {
                fileName = productDTO.getName().split(" ")[0].toUpperCase() + UUID.randomUUID() +extension;
            }

            if(!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            Path filePath = uploadDir.resolve(fileName);
            Files.copy(photo.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

//            Picture picture = new Picture();
//            picture.setDate(LocalDate.now());
//            picture.setName(fileName);
            productDTO.setImageUrl("/uploads/" + fileName);

            productService.saveProduct(productDTO); //Save product

            //Reference the product to which picture belongs
//            picture.setProduct(productService.getProductByName(productDTO.getName()));
//            pictureService.savePicture(picture);

            Set<Product> products = supplier.getProducts() == null ? new HashSet<>() : supplier.getProducts();
            if (products.add(productService.getProductByName(productDTO.getName()))){
                supplier.setProducts(products);
            }
            supplierService.addSupplier(supplier);

            Purchase purchase = new Purchase();
            purchase.setDateTime(LocalDateTime.now());
            purchase.setQty(productDTO.getQuantity());
            purchase.setReason("Initial quantity of product " + productDTO.getName());
            purchase.setProduct(productService.getProductByName(productDTO.getName()));
            purchaseService.savePurchase(purchase);


            return "redirect:/admin/productlist";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to save image and progress";
        }
    }

    @GetMapping("/categorylist")
    public String categorylist(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        AdminDTO admin = (AdminDTO) adminService.getAdminByEmail(userDetails.getUsername()).orElse(null);
        model.addAttribute("admin", admin);

        model.addAttribute("newCategory", new Category());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/categorylist";
    }

    @PostMapping("/addCategory")
    public String addCategory(Category category, @RequestParam("photo") MultipartFile photo) {
        try{
        String fileName = photo.getOriginalFilename();
        String extension = fileName.substring(photo.getOriginalFilename().lastIndexOf("."));
        Path uploadDir = Paths.get("uploads");

        if (fileName.isEmpty()) {
            fileName = category.getName().toUpperCase() + UUID.randomUUID() +extension;
        }

        if(!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        Path filePath = uploadDir.resolve(fileName);
        Files.copy(photo.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        category.setImageUrl("/uploads/" + fileName);
        category.setName(category.getName().toLowerCase());
        categoryService.createCategory(category);
        return "redirect:/admin/categorylist";

    } catch (IOException e) {
            e.printStackTrace();
            return "Failed to save image and progress";
        }
    }

    @GetMapping("/supplierlist")
    public String supplierlistpage(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        AdminDTO admin = (AdminDTO) adminService.getAdminByEmail(userDetails.getUsername()).orElse(null);
        model.addAttribute("admin", admin);

        model.addAttribute("suppliers", supplierService.getSuppliers());
        model.addAttribute("newSupplier", new Supplier());
        return "admin/supplierlist";
    }

    @PostMapping("/addSupplier")
    public String addSupplier(Supplier supplier) {
        supplierService.addSupplier(supplier);
        return "redirect:/admin/supplierlist";
    }

    @GetMapping("/saleslist")
    public String saleslistpage(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        AdminDTO admin = (AdminDTO) adminService.getAdminByEmail(userDetails.getUsername()).orElse(null);
        model.addAttribute("admin", admin);

        return "admin/saleslist";
    }

    @GetMapping("/purchaselist")
    public String purchaselist(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        AdminDTO admin = (AdminDTO) adminService.getAdminByEmail(userDetails.getUsername()).orElse(null);
        model.addAttribute("admin", admin);

        model.addAttribute("purchases", purchaseService.getPurchases());
        model.addAttribute("suppliers", supplierService.getSuppliers());
        model.addAttribute("newSupplier", new Supplier());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("newPurchase", new Purchase());
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "admin/purchaselist";
    }

    @PostMapping("/addpurchase")
    public String addpurchase(
            Purchase purchase,
            @Nullable @RequestParam("supplierId") Long supplierId,
            @Nullable @RequestParam("supplierName") String newSupplierName,
            @Nullable @RequestParam("supplierPhone") String newSupplierPhone,
            @RequestParam("productId") Long productId
        ) {

        Supplier supplier;

        if (newSupplierName != null && !newSupplierName.isBlank() && newSupplierPhone != null && !newSupplierPhone.isBlank()) {
            supplier = new Supplier();
            supplier.setName(newSupplierName);
            supplier.setPhoneNumber(newSupplierPhone);
            supplierService.addSupplier(supplier);
        } else supplier = supplierService.getSupplier(supplierId);

        supplierService.addSupplier(supplier);

        purchase.setSupplier(supplier);

        ProductDTO productDTO = productService.getProductById(productId).orElse(null);
        assert productDTO != null;
        Product product = productService.getProductByName(productDTO.getName());
        product.setQuantity(product.getQuantity() + purchase.getQty());
        productService.saveProduct(product);

        purchase.setProduct(productService.getProductByName(productDTO.getName()));
        purchase.setReason(purchase.getReason().isBlank() ? "Input of product " + productDTO.getName() : purchase.getReason());
        purchaseService.savePurchase(purchase);

        return "redirect:/admin/purchaselist";
    }

    @GetMapping("/customerlist")
    public String customerlistpage(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        AdminDTO admin = (AdminDTO) adminService.getAdminByEmail(userDetails.getUsername()).orElse(null);
        model.addAttribute("admin", admin);

        model.addAttribute("customers", clientService.getAllClients());
        return "admin/customerlist";
    }


}

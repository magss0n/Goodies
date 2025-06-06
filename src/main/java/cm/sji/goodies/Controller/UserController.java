package cm.sji.goodies.Controller;

import cm.sji.goodies.Model.Entities.Client;
import cm.sji.goodies.Model.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private PersonService personService;
    private String message;

    @Autowired
    public UserController(PersonService personService) {
        this.personService = personService;
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
        return "redirect:/user/index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        return "user/index";
    }
}

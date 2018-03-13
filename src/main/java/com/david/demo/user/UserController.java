package com.david.demo.user;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    MessageSource messageSource;

    @GetMapping(value = "/user/registration")
    public String showRegistrationForm(Model model) {
        UserDTO userDto = new UserDTO();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @GetMapping(value = "/user/test")
    public String showRegistrationTest(Model model) {
        UserDTO userDto = new UserDTO();
        model.addAttribute("user", userDto);
        ResourceBundle messages = ResourceBundle.getBundle("messages", Locale.ENGLISH);
        return messages.getString("label.form.title");
        //return messageSource.getMessage("label.form.title", null, Locale.ENGLISH);
    }

    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid UserDTO accountDto,
            BindingResult result,
            ModelMap modelMap) {

        System.out.println("INSIDE registerUserAccount POST");
        User registered = new User();
        if (!result.hasErrors()) {
            registered = createUserAccount(accountDto, result);
        }
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }
        if (result.hasErrors()) {
            System.out.println("has ERRORS");
            return new ModelAndView("registration", "user", accountDto);
        }
        else {
            System.out.println("successRegister");
            modelMap.addAttribute("user", accountDto);
            return new ModelAndView("successRegister", "user", accountDto);
        }
    }
    private User createUserAccount(UserDTO accountDto, BindingResult result) {
        User registered = null;
        try {
            registered = service.registerNewUserAccount(accountDto);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered;
    }
}
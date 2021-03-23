package insa.demo.controller;

import insa.demo.resources.users.UserInput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Controller
public class MainController implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/authentification").setViewName("authentification");
    }

    @GetMapping("/")
    public String kk() {
        return "redirect:/authentification";
    }

    @PostMapping("/")
    public String gg() {
        return "redirect:/authentification";
    }

}
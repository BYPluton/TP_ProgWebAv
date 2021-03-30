package insa.demo.controller;

import insa.demo.resources.users.UserInput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpSession;


@Controller
public class MainController implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/authentification").setViewName("authentification");
    }

    @GetMapping("/")
    public String mapGet() {
        return "redirect:/authentification";
    }

    @PostMapping("/")
    public String mapPost() {
        return "redirect:/authentification";
    }

    @GetMapping("/accueil")
    public String mapGetAccueil(HttpSession session) { return "Accueil";}

    @PostMapping("/accueil")
    public String mapPostAccueil(HttpSession session) { return "Accueil";}

    @GetMapping("/animes")
    public String mapGetAnimes(HttpSession session) { return "animes";}

    @PostMapping("/animes")
    public String mapPostAnimes(HttpSession session) { return "animes";}

    @GetMapping("/profil")
    public String mapGetProfil(HttpSession session) { return "profil";}

    @PostMapping("/profil")
    public String mapPostProfil(HttpSession session) { return "profil";}

    @GetMapping("/ajout_liste")
    public String mapGetAjoutListe(HttpSession session) { return "ajout_liste";}

    @PostMapping("/ajout_liste")
    public String mapPostAjoutListe(HttpSession session) { return "ajout_liste";}

    @GetMapping("/ajout_anime")
    public String mapGetAjoutAnime(HttpSession session) { return "ajout_anime";}

    @PostMapping("/ajout_anime")
    public String mapPostAjoutAnime(HttpSession session) { return "ajout_anime";}
}
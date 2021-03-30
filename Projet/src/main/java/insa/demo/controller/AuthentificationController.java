package insa.demo.controller;

import insa.demo.user.User;
import insa.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class AuthentificationController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/authentification")
	public String test(Model model) {
		model.addAttribute("test", new Test());
		return "authentification";
	}

	@PostMapping("/authentification")
	public String testt(@ModelAttribute Test user, Model model, HttpSession session) {
		Optional<User> pOpt = userRepository.findByPseudo(user.getPseudo());
		if(!pOpt.isPresent())
			return "authentification";
		User u = pOpt.get();
		if(!u.getPassword().equals(user.getMdp()))
			return	"authentification";
		session.setAttribute("user", u);
		return "Accueil";
	}

}

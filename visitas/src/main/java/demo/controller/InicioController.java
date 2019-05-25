package demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import demo.model.Visita;
import demo.repository.VisitasRepository;



@Controller
public class InicioController {

	@Autowired
	private VisitasRepository visitasRepository;


	@GetMapping("/")
	@Transactional(readOnly=true)
	public String home(Model model){

		model.addAttribute("visitas", visitasRepository.findAll());
		model.addAttribute("visita", new Visita());
		return "home";
	}

	

	@PostMapping("/")
    public String submit(@ModelAttribute Visita visita) {

        visitasRepository.save(visita);
        return "redirect:/";
    }
	
	
}

package demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import demo.model.Visita;
import demo.repository.VisitasRepository;



@Controller
public class InicioController {

	@Autowired
	private VisitasRepository visitasRepository;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	@Transactional(readOnly=true)
	public ModelAndView inicio(){
		List<Visita> visitas = (List<Visita>) visitasRepository.findAll();
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("visitas",visitas);
		mav.addObject("visita", new Visita());
		return mav;
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
    public String submit(@ModelAttribute Visita visita, 
    				Model model) {
        model.addAttribute("visita", visita);
        visitasRepository.save(visita);
        return "redirect:/";
    }
	
	
}

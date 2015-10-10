package demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import demo.model.Visita;
import demo.repository.VisitasRepository;

@Controller
public class AdminController {

	@Autowired
	private VisitasRepository visitasRepository;
	
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	@Transactional(readOnly=true)
	public ModelAndView inicio(){
		List<Visita> visitas = (List<Visita>) visitasRepository.findAll();
		ModelAndView mav = new ModelAndView("admin");
		mav.addObject("visitas",visitas);
		return mav;
	}
	
	
	@RequestMapping(value="/admin/{id}", method=RequestMethod.GET)
    public String submit(@PathVariable Long id) {
        Visita visita = visitasRepository.findOne(id);
        visitasRepository.delete(visita);
        return "redirect:/admin";
    }
	
	
}

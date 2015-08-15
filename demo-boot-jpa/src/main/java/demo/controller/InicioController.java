package demo.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import demo.domain.Nota;
import demo.repository.NotaRepository;

@Controller
public class InicioController {

	@Autowired
	private NotaRepository notaRepository;
	
	@RequestMapping("/")
	@Transactional(readOnly=true)
	public ModelAndView inicio(){
		List<Nota> notas = notaRepository.findAll();
		ModelAndView mav = new ModelAndView("inicio");
		mav.addObject("notas",notas);
		return mav;
	}
	
	
	
}

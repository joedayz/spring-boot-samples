package demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.service.CityService;

@Controller
public class HotelController {

	@Autowired
	private CityService cityService;
	
	@RequestMapping("/")
	@ResponseBody
	@Transactional(readOnly=true)
	public String hola(){
		return this.cityService.getCity("Bath", "UK")
				.getName();
	}
	
	
}

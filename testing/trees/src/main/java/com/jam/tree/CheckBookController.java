package com.jam.tree;

import java.io.FileNotFoundException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CheckBookController {

	 @RequestMapping(value = "/checkbook", method = RequestMethod.GET)
	 public ModelAndView getCheckBookView(ModelMap model, HttpServletRequest request) throws FileNotFoundException 
		{
		 
		 
		 
		 
		 
		 return new ModelAndView("checkbook", model);
		 
		}
	
	
}

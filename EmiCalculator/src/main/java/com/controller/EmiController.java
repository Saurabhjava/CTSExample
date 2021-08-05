package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import com.model.Product;
import com.service.EmiService;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//use appropriate annotation to configure ShoppingController as Controller
@Controller
public class EmiController {

	// use appropriate annotation
	@Autowired
	private EmiService emiService;

	@ModelAttribute("productList")
	public List<String> populateProduct() {
		List<String> li = new ArrayList<String>();
		li.add("Home Loan");
		li.add("Car Loan");
		li.add("Personal Loan");
		return li;
	}

	@RequestMapping("/showPage")
	public ModelAndView showPage() {
		Product product = new Product();
		return new ModelAndView("showPage", "product", product);
	}

	// invoke the service class - calculateEmi method.
	@RequestMapping(value="/calculateEmi", method = RequestMethod.POST)
	public String calculateEmi(@ModelAttribute("product") @Valid Product product, BindingResult result, ModelMap model) {

		// fill the code
		if (result.hasErrors()) {
			return "showPage";
		} else {
			long emi = emiService.calculateEmi(product);
			model.put("emi", emi);
			return "resultPage";
		}
	}

	// use appropriate annotation
	@ExceptionHandler(Exception.class)
	public ModelAndView exceptionHandler(Exception ex) {

		// fill the code

		return new ModelAndView("exceptionPage", "exception", ex);
	}

}

package com.cts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cts.bean.ExchangeValue;
import com.cts.dao.ExchangeRepository;

@RestController
public class CurrencyExchangeController {
	@Autowired
	private ExchangeRepository repo;
	@Autowired
	private Environment env;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retriveExchangeValue(@PathVariable String from, @PathVariable String to) {
		ExchangeValue ev=repo.findByFromAndTo(from, to);
		ev.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		return ev;
	}
}

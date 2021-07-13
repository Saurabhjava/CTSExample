package com.cts.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cts.bean.CurrencyConversionBean;
import com.cts.proxy.CurrencyExchangeServiceProxy;

@RestController
public class CurrencyConversionController {
	@Autowired
	private CurrencyExchangeServiceProxy proxy;

	/*@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{qty}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable int qty) {
		Map<String, String> uriVar=new HashMap<String,String>();
		uriVar.put("from",from);
		uriVar.put("to",to);
		ResponseEntity<CurrencyConversionBean> res=new RestTemplate().getForEntity("http://localhost:8001/currency-exchange/from/{from}/to/{to}", 
				CurrencyConversionBean.class,uriVar);
		CurrencyConversionBean ccb=res.getBody();
		BigDecimal totalAmt=ccb.getConversionRate().multiply(new BigDecimal(qty));
		return new CurrencyConversionBean(from, to, qty, ccb.getConversionRate(), totalAmt, ccb.getPort());	
	}*/
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{qty}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable int qty) {
		CurrencyConversionBean ccb=proxy.getExchangeValue(from, to);
		BigDecimal totalAmt=ccb.getConversionRate().multiply(new BigDecimal(qty));
		return new CurrencyConversionBean(from, to, qty, ccb.getConversionRate(), totalAmt, ccb.getPort());	
	}
}

package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;
import com.controller.EmiController;
import com.model.Product;
import com.service.EmiService;

//@SpringBootConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SpringTest {

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	@Mock
	private EmiService service;

	@InjectMocks
	private EmiController ctrl;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void testShowPage() {
		ResultMatcher ok = MockMvcResultMatchers.status().isOk();

		try {
			this.mockMvc.perform(get("/showPage")).andExpect(ok);
			this.mockMvc.perform(get("/showPage")).andExpect(ok).andExpect(view().name("showPage"));
		} catch (AssertionError e) {
			if (e.getMessage().contains("View")) {
				fail(e.getMessage());
			}
			fail("The request Mapping - /showPage(above showPage method in EmiController) that redirects to showPage is not correct");
		} catch (Exception e) {
			fail("The request Mapping - /showPage(above showPage method in EmiController) that redirects to showPage is not correct");
		}

	}
	@Test
	public void testValidationForShowPage() {
		ResultMatcher ok = MockMvcResultMatchers.status().isOk();

		Product obj = new Product();
		obj.setProductName("Home Loan");
		obj.setLoanAmount(5000);
		obj.setLoanTenure(6);

		try {
			this.mockMvc.perform(post("/calculateEmi").flashAttr("product", obj)).andExpect(ok);
			this.mockMvc.perform(post("/calculateEmi").flashAttr("product", obj)).andExpect(ok)
					.andExpect(view().name("showPage"))
					.andExpect(model().attributeHasFieldErrors("product", "loanAmount"))
					.andExpect(model().attributeHasFieldErrors("product", "loanTenure"));	

		} catch (AssertionError e) {
			e.printStackTrace();
			if (e.getMessage().contains("View")) {
				fail(e.getMessage());
			}
			fail("Form validation is not correct, check validation for loanAmount and loanTenure");
		} catch (Exception e) {
			fail("The model attribute-order/POST request is not as per the requirement");
		}

	}


	@Test
	public void testResultPage() {
		ResultMatcher ok = MockMvcResultMatchers.status().isOk();

		Product obj = new Product();
		obj.setProductName("Home Loan");
		obj.setLoanAmount(5000000);
		obj.setLoanTenure(240);

		try {
			this.mockMvc.perform(post("/calculateEmi").flashAttr("product", obj)).andExpect(ok);
			this.mockMvc.perform(post("/calculateEmi").flashAttr("product", obj)).andExpect(ok)
					.andExpect(view().name("resultPage"));

		} catch (AssertionError e) {
			e.printStackTrace();
			if (e.getMessage().contains("View")) {
				fail(e.getMessage());
			}
			fail("Request Mapping -/calculateEmi(above calculateEmi method in EmiController class) that redirects to resultPage is not correct");
		} catch (Exception e) {
			fail("The model attribute-order/POST request is not as per the requirement");
		}

	}

	@Test
	public void testToInvokingcalculateEmiMethodInEmiServiceClass() {

		try {
			BindingResult result = Mockito.mock(BindingResult.class);
			Product obj = new Product();
			obj.setProductName("Home Loan");
			obj.setLoanAmount(5000000);
			obj.setLoanTenure(240);

			ModelMap model = Mockito.mock(ModelMap.class);
			ctrl.calculateEmi(obj, result, model);
			verify(service).calculateEmi(obj);
		} catch (AssertionError e) {
//       e.printStackTrace();
			fail("The invoking of calculateEmi Method in EmiService class is not done as per requirement");
		} catch (Exception e) {
			fail("The model attribute name should be as per the requirement");
		}
	}

}

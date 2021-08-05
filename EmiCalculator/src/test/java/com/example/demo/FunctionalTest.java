package com.example.demo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;

import com.exception.ProductNotFoundException;
import com.model.Product;
import com.service.EmiService;


@RunWith(SpringRunner.class)
public class FunctionalTest {

	@Test
	public void testCalculateEmiForHomeLoan() {
		testCalculateTotalCost("Home Loan",5000000,240,49919);
	}
	@Test
	public void testCalculateEmiForCarLoan() {
		testCalculateTotalCost("Car Loan",500000,60,11249);
	}
	@Test
	public void testCalculateEmiForPersonalLoan() {
		testCalculateTotalCost("Personal Loan",500000,60,12561);
	}
	
	public void testCalculateTotalCost(String productName, double loanAmount,double loanTenure, long expresult) {
		Product obj=new Product();
		try {
			
			obj.setProductName(productName);
			obj.setLoanAmount(loanAmount);
			obj.setLoanTenure(loanTenure);
			EmiService service = new EmiService();
			Assertions.assertThat(service.calculateEmi(obj)).isEqualTo(expresult);
		} catch (AssertionError ae) {
			fail("Logic for calculating Total EMI for product "+obj.getProductName()+" is wrong");
			ae.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	  @Test
	 	public void testForException()
	 	{
		  Class serviceClassobj=null;
		  Product obj=new Product();
		  obj.setProductName("Xyz Loan");
		  obj.setLoanAmount(5000000);
		  obj.setLoanTenure(240);
		  EmiService service = new EmiService();
			ModelAndView model=Mockito.mock(ModelAndView.class);
	 		try {
	 			serviceClassobj = Class.forName("com.service.EmiService");

	 			
	 		} catch (ClassNotFoundException e) {
	 			fail("No class with the name EmiService as per the problem statement");
	 		}

	 		try {
	 			
	 			Method mobj2 = serviceClassobj.getDeclaredMethod("calculateEmi", Product.class);
	 			
	 				try {
	 					
	 					mobj2.invoke(service,obj);
//	 										
	 					assertFalse(true);
	 				 }catch (InvocationTargetException e) {
	 					 	System.out.println(e);
	 					Throwable b = e.getCause();
	 					System.out.println(b.getClass());
	 					if (b instanceof ProductNotFoundException)
	 						assertTrue(true);
	 					else
	 						fail("You have not thrown user defined exeception properly as per the problem statement");
	 				}catch (Error e) {
	 					fail("Check if the exception is raised correctly in calculateEmi method");
	 				}catch (Exception e) {
	 					System.out.println(e);
	 					fail("Check the logic of calculateEmi in EmiService class");
	 				}

	 			 
	 		} catch (NoSuchMethodException e) {
	 			e.printStackTrace();
	 			fail("Check the availability (or) the signature of calculateEmi method in EmiService Class");

	 		} catch (Exception e) {
	 			fail("Check the availability (or) the signature of calculateEmi method in EmiService Class");
	 		}
	 	}
	
	  

}
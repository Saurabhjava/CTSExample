package com.example.demo;

import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import org.junit.Test;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WebTest {
	private static WebDriver driver;
	private static String url = "";

	@BeforeClass
	public static void setUp() {
		
		try {
			driver = new HtmlUnitDriver();
			url = "http://localhost:9072/showPage";
			driver.get(url);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Check for the file showpage.jsp or Syntax of JavaScript / CSS / HTML");
		}
	}

	@AfterClass
	public static void tearDown() {
		driver.quit();
	}

	@Test
	public void test01ShowPageForProductHomeLoan() throws Exception {
		testShowPage("Home Loan");
	}

	@Test
	public void test02ShowPageForProductCarLoan() throws Exception {
		testShowPage("Car Loan");
	}

	@Test
	public void test03ShowPageForProductPersonalLoan() throws Exception {
		testShowPage("Personal Loan");
	}

	@Test
	public void test04ShowPageForLoanAmount() throws Exception {
		testShowPageForElements("loanAmount");
	}

	@Test
	public void test05ShowPageForLoanTenure() throws Exception {
		testShowPageForElements("loanTenure");
	}

	@Test
	public void test06SuccessFlowForProductHomeLoan() {
		testSuccessFlow("Home Loan", 5000000, 240.0, 49919);
	}
	
	public void testShowPage(String productName) {
		try {
			try {
				// String url="http://localhost:"+port+"/showPage";
				// driver.get(url);
				boolean flag = false;
				WebElement e = driver.findElement(By.id("productName"));
				Select dropdown = new Select(e);
				assertEquals(true, e.isDisplayed());
				List<WebElement> options = dropdown.getOptions();
				for (WebElement option : options) {
					if (option.getText().equals(productName))
						flag = true;
				}
				if (flag == true)
					assertTrue(true);
				else
					assertTrue(false);

			} catch (Error e) {
				fail("Correct HTML Component with the id 'productName' must be used with appropriate constraints/Check whether package "
						+ productName + " is autopopulated as per the requirement");

			} catch (Exception e) {
				fail("Correct HTML Component with the id 'productName' must be used with appropriate constraints/Check whether package "
						+ productName + " is autopopulated as per the requirement");

			}
		} catch (Exception e) {
			fail("Should have got a tag with the name - productName");
		}

	}

	public void testShowPageForElements(String element) {
		try {
			try {
				// String url="http://localhost:"+port+"/showPage";
				// driver.get(url);
				WebElement e = driver.findElement(By.id(element));
				assertEquals("input", e.getTagName());

			} catch (Error e) {
				System.out.println(e);
				fail("Correct HTML Component with the id " + element + " must be used with appropriate constraints");

			} catch (Exception e) {
				System.out.println(e);
				fail("Correct HTML Component with the id " + element + " must be used with appropriate constraints");

			}
		} catch (Exception e) {
			System.out.println(e);
			fail("Should have got a tag with the name - " + element);
		}
	}

// 	public void testSuccessFlow(String productName,int quantity,double expresult){
	public void testSuccessFlow(String productName, double lamount, double lTenure, long expresult) {
		try {
			// String url="http://localhost:"+port+"/showPage";
			// driver.get(url);
			String aWithoutSpace = "";
			WebElement mySelectElement = driver.findElement(By.id("productName"));
			Select dropdown = new Select(mySelectElement);
			dropdown.selectByVisibleText(productName);
			driver.findElement(By.id("loanAmount")).clear();
			driver.findElement(By.id("loanAmount")).sendKeys("" + lamount);
			driver.findElement(By.id("loanTenure")).clear();
			driver.findElement(By.id("loanTenure")).sendKeys("" + lTenure);
			driver.findElement(By.id("submit")).click();

			try {
				try {
					String a = driver.findElement(By.cssSelector("body")).getText();

					aWithoutSpace = a.replaceAll("\\s", "");
					System.out.println("actual" + aWithoutSpace);
					String s2 = "ProductName:" + productName + "LoanAmount:" + lamount + "Tenure:" + lTenure
							+ "Loan EMI:" + expresult;
					String s2WithoutSpace = s2.replaceAll("\\s", "");
					System.out
							.println("**************************************************************" + s2WithoutSpace);
					try {
						if (aWithoutSpace != null
								&& aWithoutSpace.toLowerCase().contains(s2WithoutSpace.toLowerCase())) {
							assertTrue(true);
						} else {
							fail("Data is not correctly fetched from the list/Check the business logic of calculating product cost");
						}

					} catch (Error e) {
						System.out.println(e);
						e.printStackTrace();
						fail("Your Business Logic does not give the required output as expected in the problem statement in the UI for product "
								+ productName);
					} catch (Exception e) {
						System.out.println(e);
						fail("Your Business Logic does not give the required output as expected in the problem statement in the UI for product "
								+ productName);
					}
				} catch (Exception e) {
					System.out.println(e);
					fail("Should have got billDeskPage.jsp");
				}
			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
				fail("Correct HTML Component with the id 'productName' must be used with appropriate constraints/Check whether productName is autopopulated as per the requirement");

			}
		} catch (Exception e) {
			fail("Should have got proper element " + e.getMessage());
		}

	}
}
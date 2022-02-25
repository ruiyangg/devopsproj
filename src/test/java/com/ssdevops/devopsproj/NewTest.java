package com.ssdevops.devopsproj;

import org.openqa.selenium.By;
//import necessary Selenium WebDriver classes
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class NewTest {
	// declare Selenium WebDriver
	private WebDriver webDriver;

	@Test(priority = 0)

	public void checkregister() { // Load website as a new page
		webDriver.navigate().to("http://localhost:8090/devopsproj/register.jsp");
		WebElement name = webDriver.findElement(By.name("userName"));
		name.sendKeys("test");
		WebElement email = webDriver.findElement(By.name("email"));
		email.sendKeys("test");
		WebElement password = webDriver.findElement(By.name("password"));
		password.sendKeys("test");
		WebElement language = webDriver.findElement(By.xpath("/html/body/form/select/option"));
		language.click();
		WebElement button = webDriver.findElement(By.xpath("/html/body/form/input[4]"));
		button.click();
	}

	@Test(priority = 1)
	public void checkedituser() { // Load website as a new page
		webDriver.navigate().to("http://localhost:8090/devopsproj/UserServlet/dashboard");
		WebElement button = webDriver.findElement(By.xpath("/html/body/div//table/tbody//tr/td[5]/a"));
		button.click();
		WebElement name = webDriver.findElement(By.name("name"));
		name.sendKeys("testing");
		WebElement password = webDriver.findElement(By.name("password"));
		password.sendKeys("testing");
		WebElement email = webDriver.findElement(By.name("email"));
		email.sendKeys("testing");
		WebElement language = webDriver.findElement(By.name("language"));
		language.sendKeys("testing");
		WebElement button2 = webDriver.findElement(By.xpath("/html/body/div/div/div/form/button"));
		button2.click();
	}

	@Test(priority = 2)
	public void checkdeleteuser() { // Load website as a new page
		webDriver.navigate().to("http://localhost:8090/devopsproj/UserServlet/dashboard");
		WebElement button3 = webDriver.findElement(By.xpath("/html/body/div/div/table/tbody/tr/td[5]/a[2]"));
		button3.click();
	}

	@Test(priority = 3)
	public void checkaddnotes() {
		// Load website as a new page
		webDriver.navigate().to("http://localhost:8090/devopsproj/note.jsp");
		WebElement name = webDriver.findElement(By.name("userName"));
		name.sendKeys("test");
		WebElement description = webDriver.findElement(By.name("description"));
		description.sendKeys("test");
		WebElement target_date = webDriver.findElement(By.name("target_date"));
		target_date.sendKeys("test");
		WebElement button4 = webDriver.findElement(By.xpath("/html/body/form/select/option"));
		button4.click();
		WebElement button5 = webDriver.findElement(By.xpath("/html/body/form/input[4]"));
		button5.click();
	}

	@Test(priority = 4)
	public void checkeditnote() {
		webDriver.navigate().to("http://localhost:8090/devopsproj/notesServlet/dashboard");
		WebElement button6 = webDriver.findElement(By.xpath("/html/body/div/div/table/tbody/tr/td/a"));
		button6.click();

		Assert.assertEquals(webDriver.getTitle(), "note Management Application");

		WebElement name = webDriver.findElement(By.name("name"));
		name.clear();
		name.sendKeys("testing");
		WebElement description = webDriver.findElement(By.name("description"));
		description.clear();
		description.sendKeys("testing");
		WebElement target_date = webDriver.findElement(By.name("target_date"));
		target_date.clear();
		target_date.sendKeys("testing");
		WebElement accomplish = webDriver.findElement(By.name("accomplish"));
		accomplish.clear();
		accomplish.sendKeys("no");
		WebElement button7 = webDriver.findElement(By.xpath("//html/body/div/div/div/form/button"));
		button7.submit();
	}

	@Test(priority = 5)
	public void checkdeletenote() {
		// Load website as a new page
		webDriver.navigate().to("http://localhost:8090/devopsproj/notesServlet/dashboard");
		WebElement button8 = webDriver.findElement(By.xpath("/html/body/div/div/table/tbody/tr/td/a[2]"));
		button8.click();

	}

	@BeforeTest
	public void beforeTest() {
		// Setting system properties of ChromeDriver
		// to amend directory path base on your local file path
		String chromeDriverDir = "C:\\Program Files (x86)\\Google\\Chrome\\chromedriver.exe";

		System.setProperty("webdriver.chrome.driver", chromeDriverDir);

		// initialize FirefoxDriver at the start of test
		webDriver = new ChromeDriver();
	}

	@AfterTest
	public void afterTest() {
		// Quit the ChromeDriver and close all associated window at the end of test
		webDriver.quit();
	}

}
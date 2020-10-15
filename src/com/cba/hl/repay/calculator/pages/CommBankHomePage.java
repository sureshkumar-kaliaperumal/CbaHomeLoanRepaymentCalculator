package com.cba.hl.repay.calculator.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommBankHomePage {
	WebDriver driver;
	By homeLoanLink = By.linkText("Home loans");
	By calculatorAndToolsLink = By.linkText("Calculators & tools");
	By calculatorAndToolsLinkXpath = By.xpath("/html/body/div[4]/div[3]/div/div[1]/nav/ul/li[2]/a/span");
	By calculateNowPath = By.xpath("//*[@id=\"calculators\"]/div/div[2]/div/div[2]/div/p[2]/a");
	By calulateNowLandingText = By.xpath("//*[@id=\"repayments-app\"]/div[1]/div/div[2]/div/p");
	
	public CommBankHomePage(WebDriver driver) {
		this.driver = driver;
	}
	
    public void goToHomeLoan(){
    	    driver.findElement(homeLoanLink).click();
    	    System.out.println("Home Loans Link clicked from CommBank Home Page.");
    }
    
    public String assertHLCalcLandingPage() {
    	return driver.findElement(By.xpath("//*[@id=\"calculators\"]/div/div[2]/div/div[2]/div/p[1]")).getText();
    }
    
    public String assertHLCalculateNowLandingPage() {
    	return driver.findElement(calulateNowLandingText).getText();
    }
    
    public void clickCalculatorAndTools(){
    	WebElement element = driver.findElement(calculatorAndToolsLinkXpath);
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].scrollIntoView(true)", element);
		driver.findElement(calculatorAndToolsLink).click();
		System.out.println("Clicked on Calculate and Tools link.");
    }
   
   public void clickCalculateNow() {
		driver.findElement(calculateNowPath).click();
		System.out.println("Clicked on Calculate Now link.");
   }
}


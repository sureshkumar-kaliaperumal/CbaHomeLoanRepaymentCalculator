package com.cba.hl.repay.calculator.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class HLRepaymentCalculatorPage {
		WebDriver driver;
		By amount = By.id("amount");
		By term = By.id("term");
		By repaymentType = By.id("interestOnly");
		By repaymentWithProduct = By.id("productId");
		By totalInterest = By.xpath("//*[@id=\"repayments-app\"]/div[3]/div[1]/div/div[3]/div[3]/div[1]/div[2]/p/span[3]");
		By totalLoanRepayments = By.xpath("//*[@id=\"repayments-app\"]/div[3]/div[1]/div/div[3]/div[3]/div[1]/div[1]/p/span[3]");
		By btnCalculate = By.id("submit");
		
		public HLRepaymentCalculatorPage(WebDriver driver) {
			this.driver = driver;
		}
		
	   public void enterRepaymentDetail(String loanAmount,String loanTerm,String repayType, String percentageInterest) {
		   driver.findElement(amount).clear();
		   driver.findElement(amount).sendKeys(loanAmount);
		   driver.findElement(term).clear();
		   driver.findElement(term).sendKeys(loanTerm);
		   Select ddRepaymentType = new Select(driver.findElement(repaymentType));
		   ddRepaymentType.selectByVisibleText(repayType);
		   new Select(driver.findElement(By.id("productId"))).selectByVisibleText(percentageInterest);
		   driver.findElement(btnCalculate).click();
		   System.out.println("Calculated Home Loan Repayment and Interest charged for given data.");
	   }
	   
	   public String validateTotalRepayments() {
		   return driver.findElement(totalLoanRepayments).getText();
	   }
	   
	   public String validateTotalInterest() {
		   return driver.findElement(totalInterest).getText();
	   }	   
}

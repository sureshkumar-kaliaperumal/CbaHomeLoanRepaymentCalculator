package com.cba.hl.repay.calculator.tests;


import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.cba.hl.repay.calculator.pages.CommBankHomePage;
import com.cba.hl.repay.calculator.pages.HLRepaymentCalculatorPage;
import com.opencsv.CSVReader;

public class TestHomeLoanRepaymentCalculator {
	WebDriver driver;
	CommBankHomePage homePage;
	HLRepaymentCalculatorPage objHLRepaymentCalculatorPage;
	String geckoDriverPath = "src/chromedriver.exe";
	String url = "https://www.commbank.com.au/";
	String csvPath = "src/HomeLoanRepaymentDetails.csv";
	String homeLoanPageTitle = "Home loans - calculators, guides and more – CommBank";
	String homeLoanCalculateLandingPage = "Work out how much your home loan repayments might be.";
	String homeLoanCalculateNowLandingPage = "Work out how much your home loan repayments might be. You can also generate a Key Facts Sheet.";
	@BeforeTest
	public void setUp() {
	}
	
	
	
	@Test(priority=0)
	
	//Go to CBA Home Loan Page
	public void testHomeLoanRepaymentCalculator()  {
		CSVReader reader;
		try {
			reader = new CSVReader(new FileReader(csvPath));
            String[] cell=reader.readNext();
            int iterationCounter = 0;
        	while ((cell = reader.readNext()) != null) {
            int i = 0;
		        String amount = cell[i];
		        String term = cell[i+1];
		        String repaymentType = cell[i+2];
				String perInterest = cell[i+3];
				String totLoanRepayment = cell[i+4];
				String totInterestCharged = cell[i+5];
				System.out.println(" ");
				System.out.println("--------------------------------------------------------");
				System.out.println("Iteration "+ ++iterationCounter);
				System.out.println(amount + " "+term+" "+repaymentType+" "+perInterest+" "+totLoanRepayment+" "+totInterestCharged);
				try {	
					System.setProperty("webdriver.chrome.driver", geckoDriverPath);
					driver = new ChromeDriver();
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.get(url);
					driver.manage().deleteAllCookies();
					Toolkit toolkit = Toolkit.getDefaultToolkit();
					Dimension screenResolution = new Dimension((int) 
					                    toolkit.getScreenSize().getWidth(), (int) 
					                    toolkit.getScreenSize().getHeight());
					driver.manage().window().setSize(screenResolution);
				} catch (Exception e) {
					e.printStackTrace();
				}
				homePage = new CommBankHomePage(driver);
				homePage.goToHomeLoan();
				Assert.assertTrue(driver.getTitle().equals(homeLoanPageTitle.trim()));
				homePage.clickCalculatorAndTools();
				Assert.assertTrue(homePage.assertHLCalcLandingPage().contains(homeLoanCalculateLandingPage));
				homePage.clickCalculateNow();
				Assert.assertTrue(homePage.assertHLCalculateNowLandingPage().contains(homeLoanCalculateNowLandingPage));
				objHLRepaymentCalculatorPage = new HLRepaymentCalculatorPage(driver);
				objHLRepaymentCalculatorPage.enterRepaymentDetail(amount,term,repaymentType,perInterest);
				Assert.assertTrue(objHLRepaymentCalculatorPage.validateTotalRepayments().contains(totLoanRepayment));
				Assert.assertTrue(objHLRepaymentCalculatorPage.validateTotalInterest().contains(totInterestCharged));
				driver.close();
		    }
        	System.out.println(" ");
        	System.out.println("All iterations completed successfully");
        	System.out.println(" ");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

package com.anis.investment;

import org.junit.Test;

import com.anis.investment.exception.NegativeInitialInvestmentException;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class InvestmentPortfolioTest {
	
	@Test
	public void checkWhetherInvestmentNotSet(){
		InvestmentPortfolio portfolio = new InvestmentPortfolio();
		assertTrue("Portfolio Investment is 0 or not set", portfolio.getInvestment() == 0);
	}

	@Test(expected=NegativeInitialInvestmentException.class)
	public void throwExceptionWhenInvestmentLessThanZero() {
		InvestmentPortfolio portfolio = new InvestmentPortfolio("Sample Portfolio", -1);
	}

	public void checkWhetherInvestmentNotZero() {
		InvestmentPortfolio portfolio = new InvestmentPortfolio("Sample Portfolio", 10000);
		assertTrue("Portfolio Investment is greater than 0", portfolio.getInvestment() > 0);
	}
	
}

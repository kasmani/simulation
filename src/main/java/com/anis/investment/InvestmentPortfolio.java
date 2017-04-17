package com.anis.investment;

import java.util.Random;

import com.anis.investment.exception.NegativeInitialInvestmentException;
import com.anis.investment.exception.NegativeAcceptableRiskException;
import com.anis.util.Helper;

/**
 * Holds sample portfolio information including investment, acceptable risk and
 * expected returns
 * 
 * @author anis
 *
 */
public class InvestmentPortfolio {

	private String portfolioId;
	private String portfolioName;
	private double investment;

	private double acceptableRiskPercent;

	private double expectedReturnPercent;

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	public double getInvestment() {
		return investment;
	}

	public void setInvestment(double investment) throws NegativeInitialInvestmentException {
		//Do not allow negative value for initial investment
		if (investment < 0) {
			throw new NegativeInitialInvestmentException();
		}
		this.investment = investment;
	}

	public double getAcceptableRiskPercent() {
		return acceptableRiskPercent;
	}

	public void setAcceptableRiskPercent(double acceptableRiskPercent) throws NegativeAcceptableRiskException {
		//Do not allow negative value for initial investment
		if (acceptableRiskPercent < 0) {
			throw new NegativeAcceptableRiskException();
		}
		this.acceptableRiskPercent = acceptableRiskPercent;
	}

	public double getExpectedReturnPercent() {
		return expectedReturnPercent;
	}

	public void setExpectedReturnPercent(double expectedReturnPercent) {
		this.expectedReturnPercent = expectedReturnPercent;
	}

	public InvestmentPortfolio() {

	}

	public InvestmentPortfolio(String portfolioName, double investment)  throws NegativeInitialInvestmentException {
		super();
		this.portfolioId = generatePortfolioId();
		this.portfolioName = portfolioName;
		setInvestment(investment);
	}

	public InvestmentPortfolio(String portfolioName, double investment, 
			double expectedReturnPercent, double acceptableRiskPercent) throws NegativeInitialInvestmentException {
		this(portfolioName, investment);
		setAcceptableRiskPercent(acceptableRiskPercent);
		this.portfolioId = generatePortfolioId();
		this.portfolioName = portfolioName;
		this.expectedReturnPercent = expectedReturnPercent;
	}

	/**
	 * Generates unique Portfolio id
	 * 
	 * @return
	 */
	private static String generatePortfolioId() {
		return Helper.generateUniqueString();
	}

}

package com.anis.investment.simulation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.anis.investment.InvestmentPortfolio;
import com.anis.investment.simulation.SimulationResult;
import com.anis.investment.simulation.strategy.MonteCarloSimulationStrategy;
import com.anis.investment.simulation.strategy.SimulationStrategy;
import com.anis.util.Helper;

/**
 * Runs simulation based on provided portfolios and strategy 
 * @author anis
 *
 */
public class InvestmentReturnSimulator {
	
	/**
	 * Perform simulation on a list of portfolio using provided simulation strategy
	 * @param investmentPortfolios
	 * @param strategy
	 * @return
	 */
	public static Map<String, SimulationResult> simulatePortfolioInvestmentReturn(List<InvestmentPortfolio> investmentPortfolios, SimulationStrategy strategy){
		Map<String, SimulationResult> results=new HashMap<String, SimulationResult>();
		for(InvestmentPortfolio investmentPortfolio: investmentPortfolios){
			results.put(investmentPortfolio.getPortfolioId(), simulatePortfolioInvestmentReturn(investmentPortfolio, strategy));
		}
		return results;
	}
	
	/**
	 * Run simulation on single portfolio based on pass strategy
	 * @param investmentPortfolio
	 * @param strategy
	 * @return
	 */
	public static SimulationResult simulatePortfolioInvestmentReturn(InvestmentPortfolio investmentPortfolio, SimulationStrategy strategy){
		return strategy.performSimulation(investmentPortfolio);
	}
	

	public static void main(String args[]){
		//Create strategy using number of simulations, annual expected inflation and number of years
		MonteCarloSimulationStrategy strategy = new MonteCarloSimulationStrategy(10000, 3.50, 20);
		
		//Create aggressive portfolio for 100K investment at expected return of 9.4324% and acceptable risk of 15.675
		InvestmentPortfolio aggressivePortfolio = new InvestmentPortfolio("A - Aggressive", 100000, 9.4324, 15.675);
		InvestmentPortfolio conservativePortfolio = new InvestmentPortfolio("I - Very Conservative", 100000, 6.189, 6.3438);
		Map<String, SimulationResult> simulationResults = InvestmentReturnSimulator.simulatePortfolioInvestmentReturn(Arrays.asList(conservativePortfolio, aggressivePortfolio), strategy);
		printSimulationResults(simulationResults, conservativePortfolio, aggressivePortfolio);
	}
	
	public static void printSimulationResults(Map<String, SimulationResult> results, InvestmentPortfolio... investmentPortfolios){
		System.out.printf("|%30s|%15s|%15s|%15s|\n","Portfolio Name", "Median ", "10% Best Case", "10% Worst Case");
		
		for(InvestmentPortfolio portfolio:investmentPortfolios){
			SimulationResult result = results.get(portfolio.getPortfolioId());
			//StringBuilder sb = new StringBuilder();
			//sb.append(portfolio.getPortfolioName()).append(result.getReturnValuePercentile(90)).append(result.getReturnValuePercentile(10));
			System.out.printf("|%30s|%,15.2f|%,15.2f|%,15.2f|\n", portfolio.getPortfolioName(), result.getMedian(), result.getReturnValuePercentile(90), result.getReturnValuePercentile(10));
		}
	}
	
}

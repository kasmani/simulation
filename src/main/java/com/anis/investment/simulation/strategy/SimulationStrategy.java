package com.anis.investment.simulation.strategy;
import com.anis.investment.InvestmentPortfolio;
import com.anis.investment.simulation.SimulationResult;

/**
 * Use interface to allow different strategies to be used
 * @author anis
 *
 */
public interface SimulationStrategy {
	/**
	 * Perform simulation on this portfoio and return SimulationResults
	 * @param investmentPortfolio
	 * @return
	 */
	public SimulationResult performSimulation(InvestmentPortfolio investmentPortfolio) ;
}

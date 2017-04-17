package com.anis.investment.simulation;
import com.anis.investment.InvestmentPortfolio;
import com.anis.investment.simulation.SimulationResult;
import com.anis.investment.simulation.strategy.MonteCarloSimulationStrategy;

import org.junit.Test;


public class InvestmentReturnSimulatorTest {

	@Test
	public void checkDefaultStrategy() {
		//Assumes 1 iteration, 1 yr and 0 inflation
		MonteCarloSimulationStrategy strategy = new MonteCarloSimulationStrategy();
		SimulationResult result = strategy
				.performSimulation(new InvestmentPortfolio("Sample", 100000, 9.0, 15));
		//SimulationResult [getMin()=116568.61592726287, getMax()=116568.61592726287, getMean()=116568.61592726287, getStandardDeviation()=0.0, getIterationCount()=1]
		
		System.out.println(result);
	}

	@Test
	public void singlePortfolioReturn() {
		//Real
		MonteCarloSimulationStrategy strategy = new MonteCarloSimulationStrategy(3, 3.50, 2);
		SimulationResult result = strategy
				.performSimulation(new InvestmentPortfolio("Aggressive", 0, 9.4324, 15.675));
		System.out.println(result);
	}

}

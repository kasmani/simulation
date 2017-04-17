package com.anis.investment.simulation.strategy;

import java.util.Arrays;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import com.anis.investment.InvestmentPortfolio;
import com.anis.investment.simulation.SimulationResult;

/**
 * Implements the SimulationStrategy based on Monte Carlo approach
 * 
 * @author anis
 *
 */
public class MonteCarloSimulationStrategy implements SimulationStrategy {

	//Inflation rate to assume for the simulation. 
	private double assumedInflationRate = 0.0;

	//Track all the simulation result for the run
	//private DescriptiveStatistics investmentReturnTracker;
	
	//Used for generating sample returns.
	//private NormalDistribution sampleReturnGenerator;

	//Number of siulations to run
	private int numOfSimulations = 1;
	
	//Number of years to simulate in run
	private int timePeriodInYrs = 1;

	public MonteCarloSimulationStrategy() {

	}
	
	private double computeMedian(double[] investmentReturns){
		Arrays.sort(investmentReturns);
		int middle = investmentReturns.length/2;
		double medianValue = 0; //declare variable 
		if (investmentReturns.length%2 == 1) 
		    medianValue = investmentReturns[middle];
		else
		   medianValue = (investmentReturns[middle-1] + investmentReturns[middle]) / 2;
		return medianValue;
	}
	
	/**
	 * Constructor for the strategy. Simulation will be run based on the values passed
	 * @param numOfSimulations number of simulations
	 * @param assumedInflationRate assumed rate of inflation
	 * @param timePeriodInYrs number of years to simulate return   
	 */
	public MonteCarloSimulationStrategy(int numOfSimulations, double assumedInflationRate, int timePeriodInYrs) {
		super();
		this.numOfSimulations = numOfSimulations;
		this.assumedInflationRate = assumedInflationRate;
		this.timePeriodInYrs = timePeriodInYrs;
	}
	
	public double getAssumedInflationRate() {
		return assumedInflationRate;
	}

	public int getNumOfSimulations() {
		return numOfSimulations;
	}

	public int getTimePeriodInYrs() {
		return timePeriodInYrs;
	}

	/**
	 * Run simulations for given number of years for given initial investments 
	 * based on acceptable risk and expected returns 
	 */
	public SimulationResult performSimulation(InvestmentPortfolio investmentPortfolio) {
		NormalDistribution sampleReturnGenerator = new NormalDistribution(investmentPortfolio.getExpectedReturnPercent(),
				investmentPortfolio.getAcceptableRiskPercent());
		DescriptiveStatistics investmentReturnTracker = new DescriptiveStatistics();
		double[] investmentReturnValues=new double[this.numOfSimulations];
		
		double initialInvestment = investmentPortfolio.getInvestment();
		double investmentValue = 0.0;
		for (int simulationCtr = 0; simulationCtr < this.numOfSimulations; simulationCtr++) {
			investmentValue = initialInvestment;
			for (int yearNum = 0; yearNum < timePeriodInYrs; yearNum++) {

				double nextYrReturn = sampleReturnGenerator.sample();

				//Year End value Of the Investment
				investmentValue += (investmentValue * nextYrReturn / 100);

				// Take care of inflation impact on Investment
				investmentValue -= (investmentValue * assumedInflationRate / 100);
				
				//Ideally you can only lose what you have invested
				if (investmentValue < 0) {
					investmentValue=0;
					
					//No use continuing as nothing to invest
					break;
				}
				
			}

			// Track investment value at end of this simulation iteration
			investmentReturnTracker.addValue(investmentValue);
			investmentReturnValues[simulationCtr]=investmentValue;
		}
		
		double medianValue=computeMedian(investmentReturnValues);
		//Return overall Simulation result
		return new SimulationResult(investmentReturnTracker, medianValue);
	}

	public void setAssumedInflationRate(double assumedInflationRate) {
		this.assumedInflationRate = assumedInflationRate;
	}

	public void setNumOfSimulations(int numOfSimulations) {
		this.numOfSimulations = numOfSimulations;
	}

	public void setTimePeriodInYrs(int timePeriodInYrs) {
		this.timePeriodInYrs = timePeriodInYrs;
	}
}

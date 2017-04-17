package com.anis.investment.simulation;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.distribution.NormalDistribution;
import java.util.Map;
import java.math.*;
/**
 * Track Simulation Results
 * @author anis
 *
 */
public class SimulationResult {
	private DescriptiveStatistics runResultStatistics;
	private double medianValue;
	
	public SimulationResult(DescriptiveStatistics runResultStatistics, double medianValue){
		this.runResultStatistics=runResultStatistics;
		this.medianValue=medianValue;
	}
	
	/**
	 * Return percentile value for the collected simulated returns
	 * @param n percentile for which value is requested 
	 * @return value of the requested percentile
	 */
	public double getReturnValuePercentile(double n){
		return this.runResultStatistics.getPercentile(n);
	}

	public double getMin() {
		return this.runResultStatistics.getMin();
	}

	public double getMax() {
		return this.runResultStatistics.getMax();
	}

	public double getMean() {
		return this.runResultStatistics.getMean();
	}

	public double getMedian() {
		return medianValue;
	}

	public double getStandardDeviation() {
		return this.runResultStatistics.getStandardDeviation();
	}

	public long getIterationCount() {
		return this.runResultStatistics.getN();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SimulationResult [getMin()=" + getMin() + ", getMax()=" + getMax() + ", getMean()=" + getMean()
				+ ", getStandardDeviation()=" + getStandardDeviation() + ", getIterationCount()=" + getIterationCount() + "]";
	}
	
	
}

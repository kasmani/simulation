package com.anis.investment.simulation;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class SimulationResultTest {
	@Test
	public void testSimulationResultIsNan() {
		DescriptiveStatistics runStats = new DescriptiveStatistics();
		SimulationResult result = new SimulationResult(runStats, 0);
		assertTrue("runStats initialized already", Double.isNaN(result.getMax()));
	}
	
	@Test
	public void testSingleValueSimulationResultComputation() {
		DescriptiveStatistics runStats = new DescriptiveStatistics();
		double dblvalue=123.5;
		runStats.addValue(dblvalue);
		SimulationResult result = new SimulationResult(runStats, dblvalue);
		assertThat(result.getMax(), is(dblvalue));
		assertThat(result.getMin(), is(dblvalue));
		assertThat(result.getMean(), is(dblvalue));
		assertThat(result.getReturnValuePercentile(10), is(dblvalue));
	}

	
}

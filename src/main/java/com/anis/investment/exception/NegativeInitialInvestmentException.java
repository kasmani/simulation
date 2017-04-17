/**
 * 
 */
package com.anis.investment.exception;

/**
 * @author anis
 *
 */
public class NegativeInitialInvestmentException extends RuntimeException {

	public NegativeInitialInvestmentException() {
		super("Inital investment value cannot be negative");
		// TODO Auto-generated constructor stub
	}

}

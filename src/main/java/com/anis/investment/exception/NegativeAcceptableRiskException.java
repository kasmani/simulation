package com.anis.investment.exception;
/**
 * @author anis
 *
 */

public class NegativeAcceptableRiskException extends RuntimeException {

	public NegativeAcceptableRiskException() {
		super("Acceptable Risk should be a positive number");
	}

}

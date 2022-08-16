package com.frugalfare.spring.converters;

public class FareCalculator {

	public double calculateLyftFare(double miles) {
		double baseFare = .45;
		double costPerMile = .88 * miles;

		// takes average speed limit of 40 miles/hour and converts it to minutes and
		// multiplies times miles.
		double costPerMinute = 40.00 / 60.00 * miles * .19;
		double serviceFee = 3.40;

		double totalFare = Math.floor((baseFare + costPerMile + costPerMinute + serviceFee) * 100) / 100.00;

		// calculates min fare
		if (totalFare <= 3.76) {
			return 3.76;
		}
		// calculate max fare
		else if (totalFare > 400.00) {
			return 400.00;
		}
		// if not between max and min return fare amount
		else {
			return totalFare;
		}

	}

	public double calculateUberFare(double miles) {
		double baseFare = .43;
		double costPerMile = .88 * miles;

		// takes average speed limit of 40 miles/hour and converts it to minutes and
		// multiplies times miles.
		double costPerMinute = 40.00 / 60.00 * miles * .19;
		double serviceFee = 2.89;

		double totalFare = Math.floor((baseFare + costPerMile + costPerMinute + serviceFee) * 100) / 100.00;

		// calculates min fare
		if (totalFare <= 6.47) {
			return 6.47;
		}
		// No max fare so returning fare amount.
		return totalFare;
	}

	public double calculateTaxiFare(double miles) {
		// BaseFare is between 2.50 to 3.50. Using random number in that range
		double baseFare = Math.random() * (3.50 - 2.50) + 2.50;

		// Cost per mile is between 2.00 to 1.50. Using random number in that range
		double costPerMile = (Math.random() * (2.00 - 1.50) + 1.50) * miles;
		double totalFare = Math.floor((baseFare + costPerMile) * 100) / 100.00;

		return totalFare;

	}

}

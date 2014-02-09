package com.hazard;

import java.util.ArrayList;

public class CalcLogic {
	ArrayList<Spot> spotArray;
	int sumOfShares;
	double markup;
	double weightedMarkup;
	int i;

	public CalcLogic(ArrayList<Spot> spotArray) {
		this.spotArray = spotArray;
	}

	public double getWeightedMarkup() throws SumOfSharesException {

		for (Spot spot : spotArray) {
			sumOfShares = sumOfShares + spot.getSpotShare();
			markup = (spot.getClientSpotDuration() + spot.getPartnersSpotDuration() * 0.5)
					/ spot.getClientSpotDuration();
			spot.setMarkup(markup);
			weightedMarkup = weightedMarkup + markup * spot.getSpotShare();

			// System.out.println("Spot #" + i);
			// System.out.println("Spot Name - " + spot.getSpotName());
			// System.out.println("Client Spot Duration - " +
			// spot.getClientSpotDuration());
			// System.out.println("Partner Spot Duration - " +
			// spot.getPartnersSpotDuration());
			// System.out.println("Spot Share - " + spot.getSpotShare());
			// System.out.println("Markup - " + markup);
			// System.out.println("weightedMarkup on this step - " +
			// weightedMarkup);
			// System.out.println();
			// i++;

		}

		if (sumOfShares != 100) {
			throw new SumOfSharesException();
		}

		return weightedMarkup;
	}
}

@SuppressWarnings("serial")
class SumOfSharesException extends Exception {

}

package com.hazard;

import java.util.ArrayList;

import javax.swing.JFrame;

public class Calculator {
    CalcInterface calcInterface;
    CalcLogic calcLogic;
    private static volatile Calculator instance;

    public static Calculator getInstance() {
	Calculator localInstance = instance;
	if (localInstance == null) {
	    synchronized (Calculator.class) {
		localInstance = instance;
		if (localInstance == null) {
		    instance = localInstance = new Calculator();
		}
	    }
	}
	return localInstance;
    }

    private Calculator() {
	calcInterface = new CalcInterface("TV calculator by Nikolay Petrusenko");
	calcInterface.setCalculator(this);
	calcInterface.setSize(1300, 800);
	calcInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	calcInterface.setVisible(true);
    }

    public void execute() {

	try {
	    calcInterface.clearMarkup();
	    ArrayList<Spot> spotArray = calcInterface.getData();
	    calcLogic = new CalcLogic(spotArray);
	    calcInterface.outline(calcLogic.getWeightedMarkup());
	}

	catch (EmptyFieldsException e) {
	    calcInterface.showWarning(0, e.lineNumber);
	    // System.out.println("EmptyFieldsException on " + e.lineNumber +
	    // " line");
	}

	catch (SpotDurationException e) {
	    calcInterface.showWarning(1, e.lineNumber);
	    // System.out.println("SpotDurationException on " + e.lineNumber +
	    // " line");
	}

	catch (NumberFormatException e) {
	    calcInterface.showWarning(2, 0);
	    // System.out.println("NumberFormatException");

	} catch (SumOfSharesException e) {
	    calcInterface.showWarning(3, 0);
	    // System.out.println("SumOfSharesException");
	}
    }
}

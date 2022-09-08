package com.example.demos;

import java.math.BigDecimal;

public class Calculadora {
	public static final int CONSTANTE = 1;

	public int suma(int a, int b) {
		return a + b;
	}
	public double suma(double a, double b) {
		return precision(a + b);
	}
	
	private double precision(double value) {
		return (new BigDecimal(value)).setScale(15, CONSTANTE).doubleValue();
	}
}

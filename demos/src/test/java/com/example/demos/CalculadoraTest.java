package com.example.demos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculadoraTest {

	@Test
	void test() {
		Calculadora calculadora = new Calculadora();
		var rslt = calculadora.suma(0.1, 0.2);
		assertEquals(0.3, rslt);
	}

}

package com.example.demos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;

public class CalculadoraStepDefinitions {
	Calculadora calc;
	int op1, op2, rslt;
	
	@Dado("que estoy en la aplicación")
	public void que_estoy_en_la_aplicacion() {
	    calc = new Calculadora();
	}
	@Cuando("pongo los números {int} y {int}")
	public void pongo_los_numeros_y(Integer a, Integer b) {
	    op1 = a;
	    op2 = b;
	}
	@Cuando("solicito el resultado del cálculo")
	public void solicito_el_resultado_del_calculo() {
	    rslt = calc.suma(op1, op2);
	}
	@Entonces("el resultado debe ser {int}")
	public void el_resultado_debe_ser(Integer r) {
	    assertEquals(r, rslt);
	}
}

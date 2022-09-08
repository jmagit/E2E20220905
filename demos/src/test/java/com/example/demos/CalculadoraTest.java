package com.example.demos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CalculadoraTest {
	Calculadora calc;

	@BeforeEach
	void setUp() throws Exception {
		calc = new Calculadora();
	}


	@Nested
	class Suma {
		@Nested
		class OK {
			@Test
			@Tag("smoke")
			void testSuma_Dos_Positivos() {
				var rslt = calc.suma(2, 2);
				
				assertEquals(4, rslt);
				
				assertEquals(4, calc.suma(2, 2));
			}
			@Test
			void testSuma_Positivos_Negativo() {
				assertEquals(2, calc.suma(-1, 3));
			}

			@Test
			@DisplayName("Suma dos numeros reales")
			void testSumaDoubleDouble() {
				assertEquals(0.3, calc.suma(0.1, 0.2));
			}
			
			@ParameterizedTest(name = "{displayName} => {0} + {1} = {2}")
			@CsvSource(value = {"2,2,4","-1,3,2","5,-1,4","0,0,0","-1,-1,-2","0.1,0.2,0.3"})
			void Validas(double operando1, double operando2, double resultado) {
				assertEquals(resultado, calc.suma(operando1, operando2));
			}
			
			@Test
			@DisplayName("Resta dos numeros reales")
			@Disabled
			void testSumaDoubleDouble2() {
				assertEquals(0.1, calc.suma(1, -0.9));
			}

		}
	}
}

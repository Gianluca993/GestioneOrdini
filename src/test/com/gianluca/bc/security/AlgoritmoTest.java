package test.com.gianluca.bc.security;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.gianluca.bc.security.Algoritmo;

class AlgoritmoTest {

	@Test
	void testAlgoritmo() {
		try {
			String pass = Algoritmo.generaMD5("Pass01");
			System.out.println(pass);
		} catch (Exception e) {
			fail("Motivo: " + e.getMessage());
		}
	}
}
